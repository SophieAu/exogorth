package exogorth.level.controller;

import java.awt.Graphics;
import java.util.ArrayList;

import exogorth.level.Level;
import exogorth.level.model.BULLETTYPE;
import exogorth.level.model.Bullet;
import exogorth.level.model.Enemy;

public class CollisionController {
	public static ArrayList<Bullet> existingBullets;
	private Bullet tempBullet;

	public static ArrayList<Enemy> existingEnemies;
	private Enemy tempEnemy;

	public CollisionController() {
		existingBullets = new ArrayList<>();
		existingEnemies = new ArrayList<>();
	}

	public synchronized void update() {
		for (int i = 0; i < existingBullets.size();) {
			tempBullet = existingBullets.get(i);
			if (tempBullet.outOfBounds() || playerBulletCollision(tempBullet) || bossBulletCollision(tempBullet)) {
				existingBullets.remove(tempBullet);
				if (Level.boss.lives == 0)
					Level.boss.death();
			} else {
				tempBullet.update();
				i++;
			}
		}

		for (int i = 0; i < existingEnemies.size();) {
			tempEnemy = existingEnemies.get(i);
			if (enemyCollision(tempEnemy)) {
				existingEnemies.remove(tempEnemy);
			} else {
				tempEnemy.update();
				i++;
			}
		}
	}

	private boolean enemyCollision(Enemy enemy) {
		// enemyEnemyCollision(i, existingEnemies);
		enemyBulletCollision(enemy, existingBullets);
		return (enemy.outOfBounds() || playerEnemyCollision(enemy) || enemy.lives <= 0);
	}

	public void add(Bullet bullet) {
		existingBullets.add(bullet);
	}

	public void add(Enemy enemy) {
		existingEnemies.add(enemy);
	}

	public boolean playerBulletCollision(Bullet bullet) {
		if (!Level.player.collisionBox.intersects(bullet.collisionBox))
			return false;
		if (bullet.Owner == BULLETTYPE.CIRCLEBULLET)
			Level.player.hit();
		return true;
	}

	public boolean playerEnemyCollision(Enemy enemy) {
		if (!Level.player.collisionBox.intersects(enemy.collisionBox))
			return false;

		Level.player.hit();
		return true;
	}

	public void enemyBulletCollision(Enemy enemy, ArrayList<Bullet> bullets) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet tempBullet = bullets.get(i);

			if (enemy.collisionBox.intersects(tempBullet.collisionBox) && tempBullet.Owner == BULLETTYPE.PLAYER) {
				enemy.lives--;
				bullets.remove(i);
				return;
			}
			// very in-elegant solution but it works (for now)
			if (enemy.collisionBox.intersects(tempBullet.collisionBox)
					&& (tempBullet.xPosition + tempBullet.image.getWidth()) > (enemy.xPosition + enemy.image.getWidth())) {
				enemy.lives--;
				bullets.remove(i);
				return;
			}
		}
	}

	public void enemyEnemyCollision(int index, ArrayList<Enemy> enemies) {
		Enemy enemy = enemies.get(index);
		for (int i = 0; i < enemies.size(); i++) {
			Enemy tempEnemy = enemies.get(i);
			if (enemy.collisionBox.intersects(tempEnemy.collisionBox) && index != i && enemy.xPosition < 100) {
				enemy.ySign *= -1;
				tempEnemy.ySign *= -1;
				System.out.println("collision avoided");
				return;
			}
		}
	}

	public boolean bossBulletCollision(Bullet bullet) {
		if (Level.boss.collisionBox.intersects(bullet.collisionBox) && bullet.Owner == BULLETTYPE.PLAYER && Level.boss.xPosition < 800) {
			Level.boss.lives--;
			System.out.println("Boss Lives: " + Level.boss.lives);
			// if (Level.boss.lives == 0)
			// Level.initLevelTwo();
			return true;
		}
		return false;
	}

	public synchronized void render(Graphics g) {
		for (Bullet tempBullet : existingBullets)
			tempBullet.render(g);

		for (Enemy tempEnemy : existingEnemies)
			tempEnemy.render(g);
	}

}
package exogorth.level;

import java.awt.Graphics;
import java.util.ArrayList;

import exogorth.STATE;
import exogorth.TheMain;
import exogorth.level.characters.Enemy;
import exogorth.level.flyingobject.Bullet;
import exogorth.level.flyingobject.TYPE;

public class CollisionController {
	public static ArrayList<Bullet> existingBullets = new ArrayList<>();
	private Bullet tempBullet;

	public static ArrayList<Enemy> existingEnemies = new ArrayList<>();
	private Enemy tempEnemy;

	public synchronized void update() {
		for (int i = 0; i < existingBullets.size();) {
			tempBullet = existingBullets.get(i);
			if (tempBullet.outOfBounds() || playerBulletCollision(tempBullet) || bossBulletCollision(tempBullet)) {
				existingBullets.remove(i);
				continue;
			}
			tempBullet.update();
			i++;
		}

		for (int i = 0; i < existingEnemies.size();) {
			tempEnemy = existingEnemies.get(i);
			// enemyEnemyCollision(i, existingEnemies);
			enemyBulletCollision(tempEnemy, existingBullets);
			if (tempEnemy.outOfBounds() || playerEnemyCollision(tempEnemy) || tempEnemy.lives == 0) {
				existingEnemies.remove(i);
				continue;
			}
			tempEnemy.update();
			i++;
		}
	}

	public synchronized void render(Graphics g) {
		for (Bullet tempBullet : existingBullets)
			tempBullet.render(g);

		for (Enemy tempEnemy : existingEnemies)
			tempEnemy.render(g);
	}

	public void addBullet(Bullet bullet) {
		existingBullets.add(bullet);
	}

	public void addEnemy(Enemy enemy) {
		existingEnemies.add(enemy);
	}

	public boolean playerBulletCollision(Bullet bullet) {
		if (!Level.player.collisionBox.intersects(bullet.collisionBox))
			return false;
		if (bullet.Owner == TYPE.CIRCLEBULLET)
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

			if (enemy.collisionBox.intersects(tempBullet.collisionBox) && tempBullet.Owner == TYPE.PLAYER) {
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
		if (Level.boss.collisionBox.intersects(bullet.collisionBox) && bullet.Owner == TYPE.PLAYER){
		Level.boss.lives--;
		System.out.println("Boss Lives: " + Level.boss.lives);
		if(Level.boss.lives==0)
			TheMain.State=STATE.MAINMENU;
		return true;
		}
		return false;
	}
}
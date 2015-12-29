package exogorth.level;

import java.awt.Graphics;
import java.util.ArrayList;

import exogorth.level.characters.Enemy;
import exogorth.level.flyingobject.Bullet;
import exogorth.level.flyingobject.TYPE;

public class Controller {
	public static ArrayList<Bullet> existingBullets = new ArrayList<>();
	private Bullet tempBullet;

	public static ArrayList<Enemy> existingEnemies = new ArrayList<>();
	private Enemy tempEnemy;

	public void update() {
		for (int i = 0; i < existingBullets.size();) {
			tempBullet = existingBullets.get(i);
			if (tempBullet.outOfBounds() || playerBulletCollision(tempBullet)) {
				removeBullet(tempBullet);
				continue;
			}
			tempBullet.update();
			i++;
		}

		for (int i = 0; i < existingEnemies.size();) {
			tempEnemy = existingEnemies.get(i);
			enemyBulletCollision(tempEnemy, existingBullets);
			if (tempEnemy.outOfBounds() || playerEnemyCollision(tempEnemy) || tempEnemy.lives == 0) {
				removeEnemy(tempEnemy);
				continue;
			}
			tempEnemy.update();
			i++;
		}
	}

	public void render(Graphics g) {
		for (Bullet tempBullet : existingBullets)
			tempBullet.render(g);

		for (Enemy tempEnemy : existingEnemies)
			tempEnemy.render(g);
	}

	public void addBullet(Bullet bullet) {
		existingBullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		existingBullets.remove(bullet);
	}

	public void addEnemy(Enemy enemy) {
		existingEnemies.add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		existingEnemies.remove(enemy);
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
		;
		return true;
	}

	public void enemyBulletCollision(Enemy enemy, ArrayList<Bullet> bullets) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet tempBullet = bullets.get(i);

			if (enemy.collisionBox.intersects(tempBullet.collisionBox) && tempBullet.Owner == TYPE.PLAYER) {
				enemy.lives--;
				bullets.remove(i);
			}

//			if (enemy.collisionBox.intersects(tempBullet.collisionBox) && !(tempBullet.xPosition + tempBullet.image.getWidth() < enemy.xPosition)) {
//				enemy.lives--;
//				bullets.remove(i);
//			}
		}
	}

	public void enemyEnemyCollision() {

	}

}
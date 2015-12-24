package exogorth.level;

import java.awt.Graphics;
import java.util.ArrayList;
import exogorth.level.characters.Enemy;
import exogorth.level.flyingobject.Bullet;

public class Controller {
	private static ArrayList<Bullet> existingBullets = new ArrayList<>();
	private Bullet tempBullet;

	private static ArrayList<Enemy> existingEnemies = new ArrayList<>();
	public static ArrayList<Enemy> getExistingEnemies() {
		return existingEnemies;
	}

	private Enemy tempEnemy;

	// runs through the Bullet list and calls tick for each of the bullets
	public void update() {
		for (int i = 0; i < existingBullets.size(); i++) {
			tempBullet = existingBullets.get(i);

			if (tempBullet.outOfBounds())
				removeBullet(tempBullet);

			tempBullet.update();
		}
		for (int i = 0; i < existingEnemies.size(); i++) {
			tempEnemy = existingEnemies.get(i);

			if (tempEnemy.outOfBounds())
				removeEnemy(tempEnemy);
			
			tempEnemy.update();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < existingBullets.size(); i++) {
			tempBullet = existingBullets.get(i);

			tempBullet.render(g);
		}
		for (int i = 0; i < existingEnemies.size(); i++) {
			tempEnemy = existingEnemies.get(i);

			tempEnemy.render(g);
		}
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

}
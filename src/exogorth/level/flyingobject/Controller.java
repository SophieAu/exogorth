package exogorth.level.flyingobject;

import java.awt.Graphics;
import java.util.ArrayList;

public class Controller {
	private static ArrayList<Bullet> existingBullets = new ArrayList<>();
	private Bullet tempBullet;

	// runs through the Bullet list and calls tick for each of the bullets
	public void update() {
		for (int i = 0; i < existingBullets.size(); i++) {
			tempBullet = existingBullets.get(i);

			if (tempBullet.outOfBounds())
				removeBullet(tempBullet);

			tempBullet.update();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < existingBullets.size(); i++) {
			tempBullet = existingBullets.get(i);

			tempBullet.render(g);
		}
	}

	public void addBullet(Bullet bullet) {
		existingBullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		existingBullets.remove(bullet);
	}
}
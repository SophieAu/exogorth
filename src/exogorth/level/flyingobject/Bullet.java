package exogorth.level.flyingobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import exogorth.ImageLoader;
import exogorth.Window;
import exogorth.level.flyingobject.TYPE;

public class Bullet {
	private int xPosition, yPosition;
	private int bulletSpeed;
	private TYPE Owner;

	private BufferedImage image;
	private ImageLoader loader = ImageLoader.getInstance();

	public Bullet(int xPosition, int yPosition, int bulletSpeed, TYPE Owner) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.Owner = Owner;

		if (this.Owner == TYPE.PLAYER) {
			this.bulletSpeed = bulletSpeed;
			image = loader.load("Game/bulletPlayer");
		} else if (this.Owner == TYPE.CIRCLEBULLET) {
			this.bulletSpeed = -bulletSpeed;
			image = loader.load("Game/bulletEnemy");
		}
	}

	public void update() {
		xPosition += bulletSpeed;
	}

	public void render(Graphics g) {
		g.drawImage(image, xPosition, yPosition, null);
	}

	public boolean outOfBounds() {
		return (((xPosition + image.getWidth()) < 0) || (xPosition > Window.WIDTH + image.getWidth()));
	}
}
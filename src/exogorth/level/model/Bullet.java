package exogorth.level.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import exogorth.ImageLoader;
import exogorth.Settings;
import exogorth.level.controller.CollisionController;

public class Bullet {
	public int xPosition;
	private int yPosition;
	private int bulletSpeed;
	public BULLETTYPE Owner;
	public Rectangle collisionBox;

	public BufferedImage image;
	private ImageLoader loader = ImageLoader.getInstance();

	public Bullet(int xPosition, int yPosition, int bulletSpeed, BULLETTYPE Owner) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.Owner = Owner;

		if (this.Owner == BULLETTYPE.PLAYER) {
			this.bulletSpeed = bulletSpeed;
			image = loader.load("Game/bulletPlayer");
		} else if (this.Owner == BULLETTYPE.CIRCLEBULLET) {
			this.bulletSpeed = -bulletSpeed;
			image = loader.load("Game/bulletEnemy");
		}
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());
	}

	public synchronized void update() {
		xPosition += bulletSpeed;
		collisionBox.x = xPosition;
	}

	public synchronized void render(Graphics g) {
		g.drawImage(image, xPosition, yPosition, null);
	}

	public boolean outOfBounds() {
		return (((xPosition + image.getWidth()) < 0) || (xPosition > Settings.WIDTH + image.getWidth()));
	}

	public boolean collision() {
		ArrayList<Enemy> existingEnemies = CollisionController.existingEnemies;
		for(int i = 0; i<existingEnemies.size();){
			if(this.collisionBox.intersects(existingEnemies.get(i).collisionBox)){
				existingEnemies.remove(i);
				return true;
			}
		}
		return false;
	}
}
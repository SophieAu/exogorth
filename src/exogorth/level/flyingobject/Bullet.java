package exogorth.level.flyingobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import exogorth.ImageLoader;
import exogorth.Window;
import exogorth.level.CollisionController;
import exogorth.level.characters.Enemy;
import exogorth.level.flyingobject.TYPE;

public class Bullet {
	public int xPosition;
	private int yPosition;
	private int bulletSpeed;
	public TYPE Owner;
	public Rectangle collisionBox;

	public BufferedImage image;
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
		return (((xPosition + image.getWidth()) < 0) || (xPosition > Window.WIDTH + image.getWidth()));
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
package exogorth.level;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import exogorth.ImageLoader;
import exogorth.Window;

public class GameCharacter {
	protected ImageLoader loader = ImageLoader.getInstance();
	protected BufferedImage image;
	public Rectangle collisionBox;
	protected CollisionController bulletList;
	protected int xPosition, yPosition;
	public int xSpeed, ySpeed;
	protected int movedDistance;
	protected int reload = 0;
	protected int reloadTime;
	protected int bulletSpeed;
	protected int lives;

	public GameCharacter(int xPosition, int yPosition, int xSpeed) {
		this(xSpeed);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public GameCharacter(int xSpeed) {
		bulletList = new CollisionController();
		this.xSpeed = xSpeed;
		ySpeed = xSpeed - 2;
	}
	
	public GameCharacter() {
		bulletList = new CollisionController();
	}


	public synchronized void update() {
		shooting();
		movement();
	}

	protected void shooting() {
	}

	protected synchronized void render(Graphics g) {
		if (xPosition < Window.WIDTH)
			g.drawImage(image, xPosition, yPosition, null);
	}

	protected void movement() {
	}

	protected boolean reloading() {
		if (reload < reloadTime) {
			reload++;
			return true;
		}
		return false;
	}
	
	public static void getDamage() {
	}
}

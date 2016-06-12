package exogorth.level.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import exogorth.ImageLoader;
import exogorth.Settings;
import exogorth.level.Level;
import exogorth.level.controller.CollisionController;

public class GameCharacter {
	protected ImageLoader loader = ImageLoader.getInstance();
	public BufferedImage image;
	public Rectangle collisionBox;
	protected CollisionController bulletList;
	public int xPosition;
	protected int yPosition;
	public int xSpeed, ySpeed;
	protected int reload = 0;
	protected int reloadTime;
	protected int bulletSpeed;
	public int lives;

	public GameCharacter(int xPosition, int yPosition, int xSpeed) {
		this(xSpeed);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public GameCharacter(int xSpeed) {
		this();
		this.xSpeed = xSpeed;
		ySpeed = xSpeed - 2;
	}

	public GameCharacter() {
		bulletList = Level.bulletsAndEnemies;
	}

	public synchronized void update() {
		shooting();
		movement();
	}

	protected void shooting() {
	}

	public synchronized void render(Graphics g) {
		if (xPosition < Settings.WIDTH)
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

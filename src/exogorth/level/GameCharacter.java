package exogorth.level;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import exogorth.ImageLoader;
import exogorth.level.flyingobject.Controller;

public class GameCharacter {
	protected ImageLoader loader = ImageLoader.getInstance();
	protected BufferedImage image;
	protected Rectangle collisionBox;
	protected Controller bulletList;
	protected int xPosition, yPosition;
	protected int xSpeed, ySpeed;
	protected int movedDistance;
	protected int reload = 0;
	protected int reloadTime;
	protected int bulletSpeed;
	protected int lives;

	public GameCharacter(int xPosition, int yPosition, int xSpeed) {
		bulletList = new Controller();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.xSpeed = xSpeed;
		ySpeed = xSpeed - 2;
	}

	public void update() {
		shooting();
		movement();
	}

	protected void shooting() {
	}

	protected void render(Graphics g){
		g.drawImage(image, xPosition, yPosition, null);
	}

	protected void movement(){
	}
	
	protected boolean reloading(){
		if (reload < reloadTime) {
			reload++;
			return true;
		}
		return false;
	}
}

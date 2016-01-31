package exogorth.level.flyingobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import exogorth.ImageLoader;
import exogorth.Window;

public class Walls {
	public static int width = 800, height = 100;
	public Rectangle collisionBox;
	private Random random = new Random();

	public BufferedImage image;
	private ImageLoader loader = ImageLoader.getInstance();
	public int yPosition;
	public int xPosition;
	private int scrollingSpeed;

	public Walls(int xSpeed, int xPosition) {
		this.scrollingSpeed = xSpeed;
		boolean up = random.nextInt(2) == 0;

		if (up) {
			image = loader.load("Game/rockTop");
			yPosition = 0;
		}
		if (!up) {
			image = loader.load("Game/rockBottom");
			yPosition = Window.HEIGHT - image.getHeight();
		}
		
		height = image.getHeight();
		width = image.getWidth();
		this.xPosition = xPosition;
		collisionBox = new Rectangle(xPosition, yPosition, width, height);
	}

	public synchronized void update() {
		xPosition -= scrollingSpeed;
		collisionBox.x = xPosition;
	}

	public synchronized void render(Graphics g) {
		g.drawImage(image, xPosition, yPosition, null);
	}
}

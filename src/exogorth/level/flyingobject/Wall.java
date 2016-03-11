package exogorth.level.flyingobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import exogorth.ImageLoader;
import exogorth.Window;

public class Wall {
	public static int width = 800, height = 100;
	public Rectangle collisionBox;
	private Random random = new Random();

	public BufferedImage image;
	private ImageLoader loader = ImageLoader.getInstance();
	public int topOrBottom;
	public int xPosition;
	private int scrollingSpeed;

	public Wall(int xSpeed, int xPosition) {
		this.scrollingSpeed = xSpeed;
		boolean up = random.nextInt(2) == 0;

		if (up) {
			image = loader.load("Game/rockTop");
			topOrBottom = 0;
		}
		if (!up) {
			image = loader.load("Game/rockBottom");
			topOrBottom = Window.HEIGHT - image.getHeight();
		}
		
		height = image.getHeight();
		width = image.getWidth();
		this.xPosition = xPosition;
		collisionBox = new Rectangle(xPosition, topOrBottom, width, height);
	}

	public synchronized void update() {
		xPosition -= scrollingSpeed;
		collisionBox.x = xPosition;
	}

	public synchronized void render(Graphics g) {
		g.drawImage(image, xPosition, topOrBottom, null);
	}
}

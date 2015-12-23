package exogorth.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import exogorth.ImageLoader;

public class LevelBackground {
	private int xCoordinate = 0;
	private int scrollingSpeed;
	private ImageLoader loader = ImageLoader.getInstance();
	private BufferedImage image;

	public LevelBackground(int scrollingSpeed) {
		this.scrollingSpeed = scrollingSpeed;
		image = loader.load("background");
	}

	public void update() {
		xCoordinate -= scrollingSpeed;
		if (xCoordinate < -image.getWidth())
			xCoordinate = 0;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public void render(Graphics g) {
		g.drawImage(image, xCoordinate, 0, null);
		g.drawImage(image, xCoordinate + image.getWidth(), 0, null);
		
	}
}
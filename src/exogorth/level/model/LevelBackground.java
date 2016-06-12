package exogorth.level.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import exogorth.ImageLoader;
import exogorth.Settings;

public class LevelBackground {
	private int xPosition = 0;
	private ImageLoader loader = ImageLoader.getInstance();
	private BufferedImage image;

	public LevelBackground() {
		image = loader.load("background");
	}

	public synchronized void update() {
		xPosition -= Settings.SCROLLSPEED;
		if (xPosition < -image.getWidth())
			xPosition = 0;
	}

	public synchronized void render(Graphics g) {
		g.drawImage(image, xPosition, 0, null);
		g.drawImage(image, xPosition + image.getWidth(), 0, null);
	}
}
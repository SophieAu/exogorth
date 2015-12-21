package exogorth.gamelogic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LevelBackground {
	private int xCoordinate = 0;
	private int scrollingSpeed;
	private BufferedImage image;

	public LevelBackground(int scrollingSpeed){
		this.scrollingSpeed = scrollingSpeed;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Images/background.jpg "));
		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Level-Hintergrundbild wurde nicht gefunden.");
			e.printStackTrace();
		}
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
}

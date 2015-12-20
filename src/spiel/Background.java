package spiel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {

	private int xCoordinate = 0;
	private int backgroundSpeed;
	private BufferedImage backgroundImage;

	public Background(int backgroundSpeed) throws IOException {
		this.backgroundSpeed = backgroundSpeed;
		backgroundImage = ImageIO.read(getClass().getResourceAsStream("/Images/background1.jpg "));
	}

	public void update() {
		xCoordinate -= backgroundSpeed;
		if (xCoordinate < -backgroundImage.getWidth())
			xCoordinate = 0;
	}

	public BufferedImage getSpielerbild() {
		return backgroundImage;
	}

	public int getBackgroundxCoordinate() {
		return xCoordinate;
	}
}

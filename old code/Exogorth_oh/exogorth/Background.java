package exogorth;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {

	private float xCord = 0;
	private int speed;
	private BufferedImage bgImage;

	public Background(int geschwindigkeit) throws IOException {
		this.speed = geschwindigkeit;
		bgImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream(
				"images/background1.jpg "));

	}

	public void update() {
		xCord -= speed;
		if (xCord < -bgImage.getWidth())
			xCord = 0;

	}

	public BufferedImage getSpielerbild() {
		return bgImage;

	}

	public float getHGX() {
		return xCord;
	}
}

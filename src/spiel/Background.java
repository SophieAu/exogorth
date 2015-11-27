package spiel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {

	private int xCoord = 0;
	private int speed;
	private BufferedImage bgImage;

	public Background(int speed) throws IOException {
		this.speed = speed;
		bgImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream(
				"bilddateien/background1.jpg "));
	}

	public void update() {
		xCoord -= speed;
			if (xCoord < -bgImage.getWidth())
				xCoord = 0;
	}

	public BufferedImage getPlayerImage() {
		return bgImage;

	}

	public int getXCoord() {
		return xCoord;
	}
}

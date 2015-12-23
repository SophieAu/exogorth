package exogorth;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	// Singleton Pattern because I can------------------------------------
	private static ImageLoader loader;

	private ImageLoader() {
	}

	public static synchronized ImageLoader getInstance() {
		if (ImageLoader.loader == null) {
			ImageLoader.loader = new ImageLoader();
		}
		return ImageLoader.loader;
	}
	// End of Singleton Pattern-------------------------------------------

	private BufferedImage image;

	public BufferedImage load(String path) {
		try {
			image = ImageIO.read(getClass().getResource("/Images/" + path + ".png"));
		} catch (IOException e) {
			System.out.println("Image file not found.");
			e.printStackTrace();
		}
		return image;
	}
}

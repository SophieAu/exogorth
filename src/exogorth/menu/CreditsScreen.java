package exogorth.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class CreditsScreen extends GenericScreen {
	private BufferedImage creditsHeaderIMG, creditsIMG; // Infobilder

	public CreditsScreen() {
		super("Exogorth - Credits");

		try {
			creditsHeaderIMG = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/Credits_Header.png"));
			creditsIMG = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/Credits_Body.png"));
		} catch (IOException e) {
			System.out.println("Mindestens eine Bilddatei wurde nicht gefunden.");
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(creditsHeaderIMG, 100, 60, null);
		g.drawImage(creditsIMG, 200, 200, null);
	}
}

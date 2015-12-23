package exogorth.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class CreditsScreen extends MenuScreen {
	private BufferedImage creditsHeader, credits; // Infobilder

	public CreditsScreen() {
		super("Exogorth - Credits");

		creditsHeader = loader.load("Menu/creditsHeader");
		credits = loader.load("Menu/creditsBody");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(creditsHeader, 100, 20, null);
		g.drawImage(credits, 200, 170, null);
	}
}

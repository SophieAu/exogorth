package exogorth.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import exogorth.Settings;

@SuppressWarnings("serial")
public class MainMenu extends MenuScreen {
	private BufferedImage header;
	private BufferedImage playButton, helpButton, highscoreButton, creditsButton;
	static int playBoxY, helpBoxY, highscoreBoxY, creditsBoxY;

	public MainMenu() {
		super("Exogorth - Hauptmenü");

		header = loader.load("Menu/mainHeader");
		playButton = loader.load("Menu/buttonPlay");
		helpButton = loader.load("Menu/buttonHelp");
		highscoreButton = loader.load("Menu/buttonHighscore");
		creditsButton = loader.load("Menu/buttonCredits");
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		creditsBoxY = exitBoxY - Settings.BOXYSPACE;
		Rectangle creditsBox = new Rectangle(Settings.BOXX, creditsBoxY, Settings.BOXWIDTH, Settings.BOXHEIGHT);
		int creditsButtonY = (int) (creditsBoxY + ((Settings.BOXHEIGHT - creditsButton.getHeight()) / 2)) + 1;

		highscoreBoxY = creditsBoxY - Settings.BOXYSPACE;
		Rectangle highscoreBox = new Rectangle(Settings.BOXX, highscoreBoxY, Settings.BOXWIDTH, Settings.BOXHEIGHT);
		int highscoreButtonY = (int) (highscoreBoxY + ((Settings.BOXHEIGHT - highscoreButton.getHeight()) / 2)) + 1;

		helpBoxY = highscoreBoxY - Settings.BOXYSPACE;
		Rectangle helpBox = new Rectangle(Settings.BOXX, helpBoxY, Settings.BOXWIDTH, Settings.BOXHEIGHT);
		int helpButtonY = (int) (helpBoxY + ((Settings.BOXHEIGHT - helpButton.getHeight()) / 2)) + 1;

		playBoxY = helpBoxY - Settings.BOXYSPACE;
		Rectangle playBox = new Rectangle(Settings.BOXX, playBoxY, Settings.BOXWIDTH, Settings.BOXHEIGHT);
		int playButtonY = (int) (playBoxY + ((Settings.BOXHEIGHT - playButton.getHeight()) / 2)) + 1;

		super.paint(g);
		g.drawImage(header, (Settings.WIDTH - header.getWidth()) / 2, 40, null);

		g2d.draw(playBox);
		g.drawImage(playButton, (Settings.WIDTH - playButton.getWidth()) / 2, playButtonY, null);
		g2d.draw(helpBox);
		g.drawImage(helpButton, (Settings.WIDTH - helpButton.getWidth()) / 2, helpButtonY, null);
		g2d.draw(highscoreBox);
		g.drawImage(highscoreButton, (Settings.WIDTH - highscoreButton.getWidth()) / 2, highscoreButtonY, null);
		g2d.draw(creditsBox);
		g.drawImage(creditsButton, (Settings.WIDTH - creditsButton.getWidth()) / 2, creditsButtonY, null);
	}
}

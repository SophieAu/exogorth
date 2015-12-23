package exogorth.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import exogorth.Window;

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

		creditsBoxY = exitBoxY - Window.BOXYSPACE;
		Rectangle creditsBox = new Rectangle(Window.BOXX, creditsBoxY, Window.BOXWIDTH, Window.BOXHEIGHT);
		int creditsButtonY = (int) (creditsBoxY + ((Window.BOXHEIGHT - creditsButton.getHeight()) / 2)) + 1;

		highscoreBoxY = creditsBoxY - Window.BOXYSPACE;
		Rectangle highscoreBox = new Rectangle(Window.BOXX, highscoreBoxY, Window.BOXWIDTH, Window.BOXHEIGHT);
		int highscoreButtonY = (int) (highscoreBoxY + ((Window.BOXHEIGHT - highscoreButton.getHeight()) / 2)) + 1;

		helpBoxY = highscoreBoxY - Window.BOXYSPACE;
		Rectangle helpBox = new Rectangle(Window.BOXX, helpBoxY, Window.BOXWIDTH, Window.BOXHEIGHT);
		int helpButtonY = (int) (helpBoxY + ((Window.BOXHEIGHT - helpButton.getHeight()) / 2)) + 1;

		playBoxY = helpBoxY - Window.BOXYSPACE;
		Rectangle playBox = new Rectangle(Window.BOXX, playBoxY, Window.BOXWIDTH, Window.BOXHEIGHT);
		int playButtonY = (int) (playBoxY + ((Window.BOXHEIGHT - playButton.getHeight()) / 2)) + 1;

		super.paint(g);
		g.drawImage(header, (Window.WIDTH - header.getWidth()) / 2, 40, null);

		g2d.draw(playBox);
		g.drawImage(playButton, (Window.WIDTH - playButton.getWidth()) / 2, playButtonY, null);
		g2d.draw(helpBox);
		g.drawImage(helpButton, (Window.WIDTH - helpButton.getWidth()) / 2, helpButtonY, null);
		g2d.draw(highscoreBox);
		g.drawImage(highscoreButton, (Window.WIDTH - highscoreButton.getWidth()) / 2, highscoreButtonY, null);
		g2d.draw(creditsBox);
		g.drawImage(creditsButton, (Window.WIDTH - creditsButton.getWidth()) / 2, creditsButtonY, null);
	}
}

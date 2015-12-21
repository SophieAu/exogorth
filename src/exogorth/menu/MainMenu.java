package exogorth.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import exogorth.Window;

@SuppressWarnings("serial")
public class MainMenu extends GenericScreen {
	private BufferedImage headerIMG;
	private BufferedImage playButton, helpButton, highscoreButton, creditsButton;
	static int playBoxY, helpBoxY, highscoreBoxY, creditsBoxY;

	public MainMenu() {
		super("Exogorth - Hauptmenü");

		try {
			headerIMG = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/exogorth_header.png"));
			playButton = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/buttonPlay.png"));
			helpButton = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/buttonHelp.png"));
			highscoreButton = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/buttonHighscore.png"));
			creditsButton = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/buttonCredits.png"));
		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Mindestens eine Bilddatei wurde nicht gefunden.");
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		creditsBoxY = exitBoxY - Window.boxYSpace;
		Rectangle creditsBox = new Rectangle(Window.boxX, creditsBoxY, Window.boxWidth, Window.boxHeight);
		int creditsButtonY = (int) (creditsBoxY + ((Window.boxHeight - creditsButton.getHeight()) / 2)) + 1;

		highscoreBoxY = creditsBoxY - Window.boxYSpace;
		Rectangle highscoreBox = new Rectangle(Window.boxX, highscoreBoxY, Window.boxWidth, Window.boxHeight);
		int highscoreButtonY = (int) (highscoreBoxY + ((Window.boxHeight - highscoreButton.getHeight()) / 2)) + 1;

		helpBoxY = highscoreBoxY - Window.boxYSpace;
		Rectangle helpBox = new Rectangle(Window.boxX, helpBoxY, Window.boxWidth, Window.boxHeight);
		int helpButtonY = (int) (helpBoxY + ((Window.boxHeight - helpButton.getHeight()) / 2)) + 1;

		playBoxY = helpBoxY - Window.boxYSpace;
		Rectangle playBox = new Rectangle(Window.boxX, playBoxY, Window.boxWidth, Window.boxHeight);
		int playButtonY = (int) (playBoxY + ((Window.boxHeight - playButton.getHeight()) / 2)) + 1;

		super.paint(g);
		g.drawImage(headerIMG, (Window.width - headerIMG.getWidth()) / 2, 50, null);

		g2d.draw(playBox);
		g.drawImage(playButton, (Window.width - playButton.getWidth()) / 2, playButtonY, null);
		g2d.draw(helpBox);
		g.drawImage(helpButton, (Window.width - helpButton.getWidth()) / 2, helpButtonY, null);
		g2d.draw(highscoreBox);
		g.drawImage(highscoreButton, (Window.width - highscoreButton.getWidth()) / 2, highscoreButtonY, null);
		g2d.draw(creditsBox);
		g.drawImage(creditsButton, (Window.width - creditsButton.getWidth()) / 2, creditsButtonY, null);
	}
}

package exogorth;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CreditsScreen extends JFrame {

	private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600; // Höhe
																		// und
																		// Breite
																		// des
																		// Fensters
	private BufferedImage creditsHeaderIMG, creditsIMG; // Infobilder

	/**
	 * Erzeugt ein neues Fenster mit den Credits zum Spiel. Die Schaltfläche
	 * "Zurück" schließt das Hilfe-Menü und öffnet wieder das Hauptmenü.
	 */
	public CreditsScreen() {
		DefaultScreenOptions def = new DefaultScreenOptions(SCREEN_WIDTH,
				SCREEN_HEIGHT);

		setTitle("Exogorth - Highscore");
		setLayout(null);
		setBounds(def.getX(), def.getY(), SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		add(new DefaultBackground());

		JPanel background = def.createDefaultScreen();
		getContentPane().add(background);
		JButton backButton = def.backToMenu((JFrame) this);
		getContentPane().add(backButton);

		try {
			creditsHeaderIMG = ImageIO.read(new File(
					"Images/Credits_Header.png"));
			creditsIMG = ImageIO.read(new File("Images/Credits.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Überschreibt die paint Methode, erstellt ein Graphics2D Objekt, sodass
	// die Bilder gezeichnet werden können
	// und zeichnet die Bilder auf das Panel.
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(creditsHeaderIMG, 100, 60, null);
		g.drawImage(creditsIMG, 200, 200, null);
	}

}

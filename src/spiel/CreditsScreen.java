package spiel;

import java.awt.Graphics;
//import java.awt.EventQueue;
//import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CreditsScreen extends JFrame {

	private BufferedImage creditsHeaderIMG, creditsIMG; // Infobilder

	/**
	 * Erzeugt ein neues Fenster mit den Credits zum Spiel. Die Schaltfl�che
	 * "Zur�ck" schlie�t das Hilfe-Men� und �ffnet wieder das Hauptmen�.
	 */
	public CreditsScreen() {
		DefaultScreenOptions def = new DefaultScreenOptions(ScreenDimensions.WIDTH, ScreenDimensions.HEIGHT);

		setTitle("Exogorth - Highscore");
		setLayout(null);
		setBounds(def.getX(), def.getY(), ScreenDimensions.WIDTH, ScreenDimensions.HEIGHT);
		setResizable(false);
		add(new DefaultBackground());

		JPanel background = def.createDefaultScreen();
		getContentPane().add(background);
		JButton backButton = def.backToMenu((JFrame) this);
		getContentPane().add(backButton);

		try {
			creditsHeaderIMG = ImageIO.read(new File("Images/Credits_Header.png"));
			creditsIMG = ImageIO.read(new File("Images/Credits.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �berschreibt die paint Methode, erstellt ein Graphics2D Objekt, sodass
	// die Bilder gezeichnet werden k�nnen
	// und zeichnet die Bilder auf das Panel.
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(creditsHeaderIMG, 100, 60, null);
		g.drawImage(creditsIMG, 200, 200, null);
	}

}

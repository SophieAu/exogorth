package spiel;

//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class HighscoreScreen extends JFrame {

	/**
	 * Erzeugt ein neues Fenster mit den 10 höchsten Punktzahlen. Die
	 * Schaltfläche "Zurück" schließt das Hilfe-Menü und öffnet wieder das
	 * Hauptmenü.
	 */
	public HighscoreScreen() {
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
	}

	/* Fehlender Code zum Laden und angeben der Highcores */
}

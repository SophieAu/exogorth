package exogorth;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener {
	private Toolkit t; // Toolkit
	private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600; // Höhe und Breite des Fensters
	private int xScreen = 0, yScreen = 0; // Positionsdaten des Programms auf Bildschirm
	private BufferedImage backgroundIMG; // Spielename
	private JButton startButton, helpButton, highscoreButton, creditsButton,
			exitButton; // Menü-Schaltflächen

	/**
	 * Hauptmenü zu dem Spiel (Exogorth). Von hier aus gelangt man in sämtliche
	 * Bereiche des Programms: Spiel starten, Hilfe, Highscore, Credits und
	 * Spiel beenden. Auflösung der Fenster ist immer 800x600 und es befindet
	 * sich stets in der mitte des Bildschirms.
	 */
	public MainMenu() {
		t = t.getDefaultToolkit();
		// Erstellt Hauptmenü mit passender Auflösung, Positionierung und den
		// Schaltfälchen.
		setTitle("Exogorth - Hauptmenü");
		setLayout(null);
		calcScreenSize();
		setBounds(xScreen, yScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			backgroundIMG = ImageIO.read(new File("Images/background1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Erstellt alle fünf Menüpunkt von denen man in die anderen Bereiche
		// des Spiels gelangt.
		startButton = new JButton("Spiel Starten");
		startButton.setBounds((SCREEN_WIDTH - 300) / 2,
				(SCREEN_HEIGHT - 200) / 2, 300, 40);
		startButton.addActionListener(this);
		add(startButton);
		helpButton = new JButton("Hilfe");
		helpButton.setBounds((SCREEN_WIDTH - 300) / 2,
				(SCREEN_HEIGHT - 100) / 2, 300, 40);
		helpButton.addActionListener(this);
		add(helpButton);
		highscoreButton = new JButton("Highscore");
		highscoreButton.setBounds((SCREEN_WIDTH - 300) / 2,
				(SCREEN_HEIGHT) / 2, 300, 40);
		highscoreButton.addActionListener(this);
		add(highscoreButton);
		creditsButton = new JButton("Credits");
		creditsButton.setBounds((SCREEN_WIDTH - 300) / 2,
				(SCREEN_HEIGHT + 100) / 2, 300, 40);
		creditsButton.addActionListener(this);
		add(creditsButton);
		exitButton = new JButton("Spiel Beenden");
		exitButton.setBounds((SCREEN_WIDTH - 300) / 2,
				(SCREEN_HEIGHT + 200) / 2, 300, 40);
		exitButton.addActionListener(this);
		add(exitButton);
	}

	// Wenn eine der Schaltflächen gedrückt wird, schließt sich das Hauptmenü
	// und das zur Schaltfläche
	// passende Fenster wird geöffnet (Spielbildschirm, Hilfe, Highscore,
	// Credits, oder Spiel wird beendet)
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == startButton) {
			TheMain.mode = 2;
			TheMain.ablauf.setVisible(true);
			setVisible(false);
			TheMain.highscoreCalledAlready = false;
		} else if (ev.getSource() == helpButton) {
			JFrame helpScreen = new HelpScreen();
			helpScreen.setVisible(true);
			setVisible(false);
		} else if (ev.getSource() == highscoreButton) {
			JFrame highscoreScreen = new HighscoreScreen();
			highscoreScreen.setVisible(true);
			setVisible(false);
		} else if (ev.getSource() == creditsButton) {
			JFrame creditsScreen = new CreditsScreen();
			creditsScreen.setVisible(true);
			setVisible(false);
		} else if (ev.getSource() == exitButton) {
			System.exit(0); // Beendet das Spiel !
		}
	}

	/**
	 * Berechnet die Koordination für zentrale Platzierung des Fensters.
	 */
	private void calcScreenSize() {
		Dimension d = t.getScreenSize();
		xScreen = (d.width - SCREEN_WIDTH) / 2;
		yScreen = (d.height - SCREEN_HEIGHT) / 2;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundIMG, 0, 0, null);
	}

}

package spiel;
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
	private Toolkit t;													// Toolkit
	private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;	// Hï¿½he und Breite des Fensters
	private int xScreen = 0, yScreen = 0;								// Positionsdaten des Programms auf Bildschirm
	private BufferedImage headlineIMG;									// Spielename
	private JButton startButton, helpButton, highscoreButton, creditsButton, exitButton;	// Menï¿½-Schaltflï¿½chen

	/**
	 * Hauptmenï¿½ zu dem Spiel (Exogorth). Von hier aus gelangt man in sï¿½mtliche Bereiche des Programms: Spiel starten,
	 * Hilfe, Highscore, Credits und Spiel beenden. Auflï¿½sung der Fenster ist immer 800x600 und es befindet sich
	 * stets in der mitte des Bildschirms.
	 */
	@SuppressWarnings("static-access")
	public MainMenu() {
		t = t.getDefaultToolkit();
		// Erstellt Hauptmenï¿½ mit passender Auflï¿½sung, Positionierung und den Schaltfï¿½lchen.
		setTitle("Exogorth - Hauptmenü");
		setLayout(null);
		calcScreenSize();
		setBounds(xScreen, yScreen, SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		setVisible(true);
		add(new DefaultBackground());

		try {
			headlineIMG = ImageIO.read(new File("Images/Exogorth.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Erstellt alle fï¿½nf Menï¿½punkt von denen man in die anderen Bereiche des Spiels gelangt.
		startButton = new JButton("Spiel Starten");
		startButton.setBounds((SCREEN_WIDTH - 300) / 2, (SCREEN_HEIGHT - 200) / 2, 300, 40);
		startButton.addActionListener(this);
		add(startButton);
		helpButton = new JButton("Hilfe");
		helpButton.setBounds((SCREEN_WIDTH - 300) / 2, (SCREEN_HEIGHT - 100) / 2, 300, 40);
		helpButton.addActionListener(this);
		add(helpButton);
		highscoreButton = new JButton("Highscore");
		highscoreButton.setBounds((SCREEN_WIDTH - 300) / 2, (SCREEN_HEIGHT) / 2, 300, 40);
		highscoreButton.addActionListener(this);
		add(highscoreButton);
		creditsButton = new JButton("Credits");
		creditsButton.setBounds((SCREEN_WIDTH - 300) / 2, (SCREEN_HEIGHT + 100) / 2, 300, 40);
		creditsButton.addActionListener(this);
		add(creditsButton);
		exitButton = new JButton("Spiel Beenden");
		exitButton.setBounds((SCREEN_WIDTH - 300) / 2, (SCREEN_HEIGHT + 200) / 2, 300, 40);
		exitButton.addActionListener(this);
		add(exitButton);
	}

	// Wenn eine der Schaltflï¿½chen gedrï¿½ckt wird, schlieï¿½t sich das Hauptmenï¿½ und das zur Schaltflï¿½che
	// passende Fenster wird geï¿½ffnet (Spielbildschirm, Hilfe, Highscore, Credits, oder Spiel wird beendet)
	@Override public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == startButton) {
			setVisible(false);
			TheMain.mode=2;
		} else if(ev.getSource() == helpButton) {
			JFrame helpScreen = new HelpScreen();
			helpScreen.setVisible(true);
			setVisible(false);
		} else if(ev.getSource() == highscoreButton) {
			JFrame highscoreScreen = new HighscoreScreen();
			highscoreScreen.setVisible(true);
			setVisible(false);
		} else if(ev.getSource() == creditsButton) {
			JFrame creditsScreen = new CreditsScreen();
			creditsScreen.setVisible(true);
			setVisible(false);
		} else if(ev.getSource() == exitButton) {
			System.exit(0);		// Beendet das Spiel !
		}
	}

	/**
	 * Berechnet die Koordination fï¿½r zentrale Platzierung des Fensters.
	 */
	private void calcScreenSize() {
		Dimension d = t.getScreenSize();
		xScreen = (d.width - SCREEN_WIDTH) / 2;
		yScreen = (d.height - SCREEN_HEIGHT) / 2;
	}

	@Override public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(headlineIMG, 100, 75, null);
	}

}

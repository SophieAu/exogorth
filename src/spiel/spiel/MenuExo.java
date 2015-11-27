package spiel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuExo extends JFrame{
	
	private Toolkit t;
	private int width = 800, height = 600, xScreen = 0, yScreen = 0;
	
	/**
	 * Hauptmen� zu dem Spiel (Exogorth). Von hier aus gelangt man in s�mtliche Bereiche des Programms: Spiel starten,
	 * Hilfe, Highscore, Credits und Spiel beenden. Aufl�sung der Fenster ist immer 800x600 und es befindet sich 
	 * stets in der mitte des Bildschirms.
	 */
	public MenuExo() {
		t = Toolkit.getDefaultToolkit();
		
		// Erstellt Hauptmen� mit passender Aufl�sung, Positionierung und den Schaltflchen.
		setTitle("Exogorth - Hauptmen�");
		setLayout(null);
		calcScreenSize();
		setBounds(xScreen, yScreen, width, height);
		setResizable(false);
		setVisible(true);
		
		// Erstellt das Hintergrundpanel. Kein Layout.
		JPanel background = new JPanel();
		background.setOpaque(true);
		background.setBackground(Color.DARK_GRAY);
		background.setLayout(null);
		background.setSize(width, height);
		getContentPane().add(background);
		
		// Deklaration und Definition der Schaltfl�chen.
		initMenu(background);
	}
	
	/**
	 * Erstellt alle f�nf Men�punkt von denen man in die anderen Bereiche des Spiels gelangt. Die Methode
	 * bekommt ein JPanel �bergeben, damit sie wei�, wo die Schaltfl�chen platziert werden sollen. Es
	 * werden die Schaltfl�chen "Spiel starten", "Hilfe", "Highscore", "Credits" und "Spiel beenden" er-
	 * zeugt.
	 * @param background - JPanel : Hintergrund f�r die Schaltfl�chen
	 */
	public void initMenu(JPanel background) {
		JButton startButton = new JButton("Spiel Starten");
		startButton.setBounds((width - 300) / 2, (height - 200) / 2, 300, 40);
		startButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent ev) {
				JFrame levelScreen = new LevelScreen();
				levelScreen.setVisible(true);
				setVisible(false);
			}
		});
		background.add(startButton);
		JButton helpButton = new JButton("Hilfe");
		helpButton.setBounds((width - 300) / 2, (height - 100) / 2, 300, 40);
		helpButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent ev) {
				JFrame helpScreen = new HelpScreen();
				helpScreen.setVisible(true);
				setVisible(false);
			}
		});
		background.add(helpButton);
		JButton highscoreButton = new JButton("Highscore");
		highscoreButton.setBounds((width - 300) / 2, (height) / 2, 300, 40);
		highscoreButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent ev) {
				JFrame highscoreScreen = new HighscoreScreen();
				highscoreScreen.setVisible(true);
				setVisible(false);
			}
		});
		background.add(highscoreButton);
		JButton creditsButton = new JButton("Credits");
		creditsButton.setBounds((width - 300) / 2, (height + 100) / 2, 300, 40);
		creditsButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent ev) {
				JFrame creditsScreen = new CreditsScreen();
				creditsScreen.setVisible(true);
				setVisible(false);
			}
		});
		background.add(creditsButton);
		JButton exitButton = new JButton("Spiel Beenden");
		exitButton.setBounds((width - 300) / 2, (height + 200) / 2, 300, 40);
		exitButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent ev) {
				System.exit(1);		// Beendet das Spiel !
			}
		});
		background.add(exitButton);
	}
	
	/**
	 * Berechnet die Koordination f�r zentrale Platzierung des Fensters.
	 */
	private void calcScreenSize() {
		Dimension d = t.getScreenSize();
		xScreen = (d.width - width) / 2;
		yScreen = (d.height - height) / 2;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override public void run() {
				new MenuExo();
			}
		});
	}

}

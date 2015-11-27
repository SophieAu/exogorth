package spiel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class HighscoreScreen extends JFrame {

	private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;		// Höhe und Breite des Fensters
	
	/**
	 * Erzeugt ein neues Fenster mit den 10 höchsten Punktzahlen. Die Schaltfläche "Zurück" schließt
	 * das Hilfe-Menü und öffnet wieder das Hauptmenü.
	 */
	public HighscoreScreen() {
		DefaultScreenOptions def = new DefaultScreenOptions(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		setTitle("Exogorth - Highscore");
		setLayout(null);
		setBounds(def.getX(), def.getY(), SCREEN_WIDTH, SCREEN_HEIGHT);
		setResizable(false);
		add(new DefaultBackground());
		
		JPanel background = def.createDefaultScreen();
		getContentPane().add(background);
		JButton backButton = def.backToMenu((JFrame)this);
		getContentPane().add(backButton);
	}
	
	/* Fehlender Code zum Laden und angeben der Highcores*/
}

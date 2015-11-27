package spiel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HighscoreScreen extends JFrame {

	private int width = 800, height = 600;
	
	/**
	 * Erzeugt ein neues Fenster mit den 10 h�chsten Punktzahlen. Die Schaltfl�che "Zur�ck" schlie�t
	 * das Hilfe-Men� und �ffnet wieder das Hauptmen�.
	 */
	public HighscoreScreen() {
		DefaultScreenOptions def = new DefaultScreenOptions(width, height);
		
		setTitle("Exogorth - Highscore");
		setLayout(null);
		setBounds(def.getX(), def.getY(), width, height);
		setResizable(false);
		
		JPanel background = def.createDefaultScreen();
		getContentPane().add(background);
		JButton backButton = def.backToMenu((JFrame)this);
		getContentPane().add(backButton);		
	}
	
	/* Fehlender Code zum Laden und angeben der Highcores*/
}

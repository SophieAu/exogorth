package spiel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class HelpScreen extends JFrame {

	private int width = 800, height = 600;
	
	/**
	 * Erzeugt ein neues Fenster mit der Spielsteuerung. Die Schaltfläche "Zurück" schließt
	 * das Hilfe-Menü und öffnet wieder das Hauptmenü.
	 */
	public HelpScreen() {
		DefaultScreenOptions def = new DefaultScreenOptions(width, height);
		
		setTitle("Exogorth - Hilfe");
		setLayout(null);
		setBounds(def.getX(), def.getY(), width, height);
		setResizable(false);
		
		JPanel background = def.createDefaultScreen();
		getContentPane().add(background);
		JButton backButton = def.backToMenu((JFrame)this);
		getContentPane().add(backButton);
	}
	
	/* Fehlender Code zum Anzeigen der Spielsteuerung*/
		
}

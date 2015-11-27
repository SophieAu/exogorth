package spiel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class LevelScreen extends JFrame {

		private int width = 800, height = 600;
		
		/**
		 * Erzeugt ein neues Fenster in dem das Spiel abläuft. Mittels der ESCAPE-Taste gelangt man zurück
		 * in das Hauptmenü und mit der H-Taste kann man das Hilfe-Fenster öffnen.
		 */
		public LevelScreen() {
			DefaultScreenOptions def = new DefaultScreenOptions(width, height);
			
			setTitle("Exogorth");
			setLayout(null);
			setBounds(def.getX(), def.getY(), width, height);
			setResizable(false);
			
			JPanel background = def.createDefaultScreen();
			// Schließt das Fenster durch Drücken der ESCAPE-Taste (Lösung aus Internet)
			KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
			AbstractAction escapeAction = new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						JFrame menu = new MenuExo();
						menu.setVisible(true);
						setVisible(false);
					}
				};
			getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
			getRootPane().getActionMap().put("ESCAPE", escapeAction);
			// Schließt das Fenster durch Drücken der ESCAPE-Taste (Meine Lösung, funkt nicht)
			/*
			background.setFocusable(true);
			background.addKeyListener(new KeyAdapter() {
				@Override public void keyTyped(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						JFrame menu = new MenuExo();
						menu.setVisible(true);
						setVisible(false);
					}
				}
			});
			*/
			getContentPane().add(background);
			
			/* Fehlender Code für Spielalblauf oder so... Weiß nicht genau */
		}
}

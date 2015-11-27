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
		 * Erzeugt ein neues Fenster in dem das Spiel abl�uft. Mittels der ESCAPE-Taste gelangt man zur�ck
		 * in das Hauptmen� und mit der H-Taste kann man das Hilfe-Fenster �ffnen.
		 */
		public LevelScreen() {
			DefaultScreenOptions def = new DefaultScreenOptions(width, height);
			
			setTitle("Exogorth");
			setLayout(null);
			setBounds(def.getX(), def.getY(), width, height);
			setResizable(false);
			
			JPanel background = def.createDefaultScreen();
			// Schlie�t das Fenster durch Dr�cken der ESCAPE-Taste (L�sung aus Internet)
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
			// Schlie�t das Fenster durch Dr�cken der ESCAPE-Taste (Meine L�sung, funkt nicht)
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
			
			/* Fehlender Code f�r Spielalblauf oder so... Wei� nicht genau */
		}
}

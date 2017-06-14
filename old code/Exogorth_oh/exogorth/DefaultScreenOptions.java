package exogorth;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DefaultScreenOptions {

	private Toolkit t;
	private int width = 800, height = 600, xScreen, yScreen;

	public DefaultScreenOptions(int width, int height) {
		this.width = width;
		this.height = height;
		xScreen = 0;
		yScreen = 0;
		t = Toolkit.getDefaultToolkit();
		calcScreenPos();
	}

	/**
	 * Berechnet die Koordinaten zur mittigen Positionierung des Fensters
	 */
	private void calcScreenPos() {
		Dimension d = t.getScreenSize();
		xScreen = (d.width - width) / 2;
		yScreen = (d.height - height) / 2;
	}

	/**
	 * Erstellt ein JPanel mit schwarzem Hintergrund und der Auflösung 800x600.
	 * JPanel verfügt über kein Layout. Dies ermöglicht freie Positionierung von
	 * Komponenten.
	 * 
	 * @return Gibt ein fertigen Hintergrundbildschirm zurück.
	 */
	public JPanel createDefaultScreen() {
		JPanel background = new JPanel();
		background.setOpaque(true);
		background.setLayout(null);
		background.setSize(width, height);
		return background;
	}

	/**
	 * Erstellt einen Button der zurück in das Hauptmenü führt. Der Button
	 * verfügt dabei schon über die richtige Größe, sowie passende Position.
	 * 
	 * @param window
	 *            - JFrame : zu schließendes Fenster
	 * @return JButton der ins Hauptmenü führt.
	 */
	public JButton backToMenu(JFrame window) {
		JButton back = new JButton("Zurück");
		back.setBounds((width - 300) / 2, (height + 400) / 2, 300, 40);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JFrame menu = new MainMenu();
				menu.setVisible(true);
				window.setVisible(false);
			}
		});
		return back;
	}

	/**
	 * Liefert mittlere x-Koordinate des Bildschirms.
	 * 
	 * @return mittlere x-Koordinate des Bildschirms
	 */
	public int getX() {
		return xScreen;
	}

	/**
	 * Liefert mittlere y-Koordinate des Bildschirms.
	 * 
	 * @return y-Koordinate des Bildschirms
	 */
	public int getY() {
		return yScreen;
	}
}

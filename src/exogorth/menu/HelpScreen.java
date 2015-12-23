package exogorth.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class HelpScreen extends MenuScreen {
	private BufferedImage w, a, s, d, spaceBar;

	public HelpScreen() {
		super("Exogorth - Hilfe");

		w = loader.load("Menu/helpW");
		a = loader.load("Menu/helpA");
		s = loader.load("Menu/helpS");
		d = loader.load("Menu/helpD");
		spaceBar = loader.load("Menu/helpSpaceBar");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(w, 75, 60, null);
		g.drawImage(a, 75, 150, null);
		g.drawImage(s, 75, 240, null);
		g.drawImage(d, 75, 330, null);
		g.drawImage(spaceBar, 425, 60, null);
	}
}

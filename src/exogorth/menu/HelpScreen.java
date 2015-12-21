package exogorth.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class HelpScreen extends GenericScreen {
	private BufferedImage w, a, s, d, spaceBar;

	public HelpScreen() {
		super("Exogorth - Hilfe");

		try {
			w = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/help_W.png"));
			a = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/help_A.png"));
			s = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/help_S.png"));
			d = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/help_D.png"));
			spaceBar = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/help_SpaceBar.png"));
		} catch (IOException e) {
			System.out.println("Mindestens eine Bilddatei wurde nicht gefunden.");
			e.printStackTrace();
		}
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

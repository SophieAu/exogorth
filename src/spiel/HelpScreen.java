package spiel;

import java.awt.Graphics;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.Graphics2D;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HelpScreen extends JFrame {
	private BufferedImage wIMG, aIMG, sIMG, dIMG, spaceIMG; // Tastenbelegung-Bilder

	/**
	 * Erzeugt ein neues Fenster mit der Spielsteuerung. Die Schaltfl�che
	 * "Zur�ck" schlie�t das Hilfe-Men� und �ffnet wieder das Hauptmen�.
	 */
	public HelpScreen() {
		DefaultScreenOptions def = new DefaultScreenOptions(ScreenDimensions.WIDTH, ScreenDimensions.HEIGHT);

		setTitle("Exogorth - Hilfe");
		setLayout(null);
		setBounds(def.getX(), def.getY(), ScreenDimensions.WIDTH, ScreenDimensions.HEIGHT);
		setResizable(false);
		add(new DefaultBackground());

		JPanel background = def.createDefaultScreen();
		getContentPane().add(background);
		JButton backButton = def.backToMenu((JFrame) this);
		getContentPane().add(backButton);
		try {
			wIMG = ImageIO.read(new File("Images/w.png"));
			aIMG = ImageIO.read(new File("Images/a.png"));
			sIMG = ImageIO.read(new File("Images/s.png"));
			dIMG = ImageIO.read(new File("Images/d.png"));
			spaceIMG = ImageIO.read(new File("Images/space.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �berschreibt die paint Methode, erstellt ein Graphics2D Objekt, sodass
	// die Bilder gezeichnet werden k�nnen
	// und zeichnet die Bilder auf das Panel.
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(wIMG, 75, 60, null);
		g.drawImage(aIMG, 75, 150, null);
		g.drawImage(sIMG, 75, 240, null);
		g.drawImage(dIMG, 75, 330, null);
		g.drawImage(spaceIMG, 425, 60, null);
	}

}

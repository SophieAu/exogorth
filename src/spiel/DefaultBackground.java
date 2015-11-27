package spiel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DefaultBackground extends JPanel {
	private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;	// Höhe und Breite des Fensters
	private BufferedImage backgroundIMG;								// Hintergrundbild & Spielename
	
	public DefaultBackground() {
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		try {
			backgroundIMG = ImageIO.read(new File("Images/background1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backgroundIMG, 0, 0, null);
	}
}

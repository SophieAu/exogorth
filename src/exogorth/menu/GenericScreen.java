package exogorth.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import exogorth.STATE;
import exogorth.Window;
import exogorth.toberefactored.TheMain;

@SuppressWarnings("serial")
public class GenericScreen extends JFrame {
	private BufferedImage backgroundIMG, exitButton;
	static int exitBoxY = 520;

	public GenericScreen(String title) {
		setTitle(title);
		setLayout(null);
		setSize(Window.width, Window.height);
		setLocationRelativeTo(null); // Position in the Middle of the Screen
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			backgroundIMG = ImageIO.read(getClass().getResourceAsStream("/Images/background.jpg"));
			if (TheMain.State == STATE.MAINMENU)
				exitButton = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/buttonExit.png"));
			else
				exitButton = ImageIO.read(getClass().getResourceAsStream("/Images/Menu/buttonReturn.png"));
		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Mindestens eine Bilddatei wurde nicht gefunden.");
			e.printStackTrace();
		}
		this.addMouseListener(new MouseInput());
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		super.paint(g);

		Rectangle exitBox = new Rectangle(Window.boxX, exitBoxY, Window.boxWidth, Window.boxHeight);
		int exitButtonY = (int) (exitBoxY + ((Window.boxHeight - exitButton.getHeight()) / 2)) + 1;

		g.drawImage(backgroundIMG, 0, 0, null);
		g2d.draw(exitBox);
		g.drawImage(exitButton, (Window.width - exitButton.getWidth()) / 2, exitButtonY, null);
	}
}

package exogorth.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import exogorth.ImageLoader;
import exogorth.STATE;
import exogorth.Window;
import exogorth.TheMain;

@SuppressWarnings("serial")
public class MenuScreen extends JFrame {
	private BufferedImage background, exitButton;
	protected ImageLoader loader = ImageLoader.getInstance();
	protected static int buttonXLeft = (Window.WIDTH - Window.BOXWIDTH) / 2, buttonXRight = (Window.WIDTH + Window.BOXWIDTH) / 2;
	protected static int exitBoxY = 500;

	public MenuScreen(String title) {
		background = loader.load("Background");
		exitButton = TheMain.State == STATE.MAINMENU ? loader.load("Menu/buttonExit") : loader.load("Menu/buttonReturn");
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.white);
		super.paint(g);

		Rectangle exitBox = new Rectangle(Window.BOXX, exitBoxY, Window.BOXWIDTH, Window.BOXHEIGHT);
		int exitButtonY = (int) (exitBoxY + ((Window.BOXHEIGHT - exitButton.getHeight()) / 2)) + 1;

		g.drawImage(background, 0, 0, null);
		g2d.draw(exitBox);
		g.drawImage(exitButton, (Window.WIDTH - exitButton.getWidth()) / 2, exitButtonY, null);
	}
}

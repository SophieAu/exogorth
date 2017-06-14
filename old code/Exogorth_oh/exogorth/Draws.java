package exogorth;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Draws extends JFrame {
	private final Player player;
	final private Background bg;
	private static final long serialVersionUID = 1;
	private BufferStrategy strat;
	private ArrayList<Bullets> bullet;
	private ArrayList<Npc> npc;

	public Draws(Player player, Background bg, ArrayList<Bullets> bullet, ArrayList<Npc> npc) {
		super("Exogorth");
		this.npc = npc;
		this.player = player;
		this.bg = bg;
		this.bullet = bullet;
		addKeyListener(new KeyBoard());

	}

	public void buffer() {
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}

	public void drawAgain() {
		Graphics g = strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();

	}

	public void drawHelp() throws IOException {
		Graphics g = strat.getDrawGraphics();
		BufferedImage IG_Hilfe = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/IG_Hilfe.png"));

		g.drawImage(IG_Hilfe, 0, 0, null);
		g.dispose();
		strat.show();

	}

	private void draw(Graphics g) {
		g.drawImage(bg.getSpielerbild(), (int) bg.getHGX(), 0, null);
		g.drawImage(bg.getSpielerbild(), (int) bg.getHGX() + bg.getSpielerbild().getWidth(), 0, null);

		for (int i = 1; i < bullet.size(); i++)
			g.drawImage((bullet.get(i)).getPicture(), (bullet.get(i)).getBounding().x, (bullet.get(i)).getBounding().y, null);

		// exogorth
		g.drawImage((bullet.get(0)).getPicture(), (bullet.get(0)).getBounding().x, (bullet.get(0)).getBounding().y, null);

		for (int i = 0; i < npc.size(); i++)
			g.drawImage((npc.get(i)).getPicture(), (npc.get(i)).getBounding().x, (npc.get(i)).getBounding().y, null);

		g.drawImage(player.getPlayerPicture(), player.getBounding().x, player.getBounding().y, null);

		for (int i = 1; i <= player.getHealth(); i++)
			g.drawImage(player.getPlayerPicture(), i * 100 - 75, 50, null);

		drawHighscore(g);
	}

	private void drawHighscore(Graphics g) {
		try {
			Image hunderter = number((Highscore.currentScore / 100) % 10);
			Image zehner = number((Highscore.currentScore / 10) % 10);
			Image einer = number((Highscore.currentScore / 1) % 10);

			g.drawImage(hunderter, 790 - 3 * 27, 30, null);
			g.drawImage(zehner, 790 - 2 * 27, 30, null);
			g.drawImage(einer, 790 - 26, 30, null);

		} catch (IOException e) {
			System.out.println("Bild nicht gefunde");
			e.printStackTrace();
		}
	}

	private Image number(int i) throws IOException {
		return ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/highscore_" + i + ".png"));
	}

}
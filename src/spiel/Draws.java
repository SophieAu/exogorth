package spiel;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Draws extends JFrame {
	private final Player player;
	final private Background background;
	private static final long serialVersionUID = 1;
	private BufferStrategy strat;
	private ArrayList<Bullets> bullet;
	private ArrayList<Npc> npc;

	public Draws(Player player, Background background, ArrayList<Bullets> bullet, ArrayList<Npc> npc) {
		super("Exogorth");
		this.npc = npc;
		this.player = player;
		this.background = background;
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

	private void draw(Graphics g) {

		g.drawImage(background.getSpielerbild(), background.getBackgroundxCoordinate(), 0, null);
		g.drawImage(background.getSpielerbild(),
				background.getBackgroundxCoordinate() + background.getSpielerbild().getWidth(), 0, null);

		for (int i = 0; i < npc.size(); i++)
			g.drawImage((npc.get(i)).getPicture(), (npc.get(i)).getBounding().x, (npc.get(i)).getBounding().y, null);

		for (int i = 0; i < bullet.size(); i++)
			g.drawImage((bullet.get(i)).getPicture(), (bullet.get(i)).getBounding().x, (bullet.get(i)).getBounding().y,
					null);

		g.drawImage(player.getPlayerPicture(), player.getBounding().x, player.getBounding().y, null);
	}

}
package exogorth.toberefactored;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

import exogorth.gamelogic.Keyboard;
import exogorth.gamelogic.LevelBackground;

@SuppressWarnings("serial")
public class Draws extends JFrame {
	private final Player player;
	final private LevelBackground background;
	private BufferStrategy strat;
	private ArrayList<Bullets> bullet;
	private ArrayList<Npc> npc;

	public Draws(Player player, LevelBackground background, ArrayList<Bullets> bullet, ArrayList<Npc> npc) {
		super("Exogorth");
		this.npc = npc;
		this.player = player;
		this.background = background;
		this.bullet = bullet;
		addKeyListener(new Keyboard());
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

		g.drawImage(background.getImage(), background.getXCoordinate(), 0, null);
		g.drawImage(background.getImage(), background.getXCoordinate() + background.getImage().getWidth(), 0, null);

		for (int i = 0; i < npc.size(); i++)
			g.drawImage((npc.get(i)).getPicture(), (npc.get(i)).getBounding().x, (npc.get(i)).getBounding().y, null);

		for (int i = 0; i < bullet.size(); i++)
			g.drawImage((bullet.get(i)).getImage(), (bullet.get(i)).getCollisionBox().x, (bullet.get(i)).getCollisionBox().y,
					null);

		g.drawImage(player.getPlayerPicture(), player.getBounding().x, player.getBounding().y, null);
	}

}
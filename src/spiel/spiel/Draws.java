package spiel;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Draws extends JFrame {
	private final Player player;
	private ArrayList<? super Objekte> object;
	final private Background bg;
	private static final long serialVersionUID = 1;
	private BufferStrategy strat;
	private ArrayList<Bullets> bullet;

	public Draws(Player player,ArrayList<? super spiel.Objekte> objekte,Background bg, ArrayList<Bullets> bullet) {
		super("Draws");
		this.player=player;
		this.bg=bg;
		this.object=objekte;
		this.bullet=bullet;
	
		addKeyListener(new KeyBoard());
		
	}
	
	public void buffer(){
		createBufferStrategy(2);
		strat = getBufferStrategy();
	}
	public void drawAgain(){
		Graphics g=strat.getDrawGraphics();
		draw(g);
		g.dispose();
		strat.show();
		
	}
	private void draw(Graphics g){
		g.drawImage(bg.getSpielerbild(),(int) bg.getHGX(), 0, null);
		g.drawImage(bg.getSpielerbild(),(int) bg.getHGX()+bg.getSpielerbild().getWidth(), 0, null);		
		for(int i =0; i <=object.size()-1;i++)
			g.drawImage(((Objekte) object.get(i)).getPicture(), ((Objekte) object.get(i)).getBounding().x, ((Objekte) object.get(i)).getBounding().y, null);
		for(int i =0; i <bullet.size();i++)
			g.drawImage(( bullet.get(i)).getPicture(), ( bullet.get(i)).getBounding().x, ( bullet.get(i)).getBounding().y, null);
		g.drawImage(player.getPlayerPicture(), player.getBounding().x, player.getBounding().y, null);
	}
		
}
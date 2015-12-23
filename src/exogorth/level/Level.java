package exogorth.level;

import java.awt.Graphics;

import javax.swing.JFrame;

import exogorth.level.characters.ENEMYTYPE;
import exogorth.level.characters.Enemy;
import exogorth.level.characters.Player;
import exogorth.level.flyingobject.Controller;

@SuppressWarnings("serial")
public class Level extends JFrame{
	private Player player;
	private LevelBackground background;
	private Controller flyingObjects;
	private Enemy testEnemy;
	
	public Level(){
		player = new Player(200, 300, 4);
		background = new LevelBackground(4);
		flyingObjects = new Controller();
		testEnemy = new Enemy(800, 300, 2, ENEMYTYPE.CIRCLE);
	}

	public void update(){
		background.update();
		player.update();
		flyingObjects.update();
		testEnemy.update();
	}
	
	public void render(Graphics g){
		background.render(g);
		flyingObjects.render(g);
		player.render(g);
		testEnemy.render(g);
	}
}
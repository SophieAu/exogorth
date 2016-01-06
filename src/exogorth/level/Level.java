package exogorth.level;

import java.awt.Graphics;
import javax.swing.JFrame;

import exogorth.level.characters.Boss;
import exogorth.level.characters.Enemy;
import exogorth.level.characters.Player;
import exogorth.level.characters.WallController;

@SuppressWarnings("serial")
public class Level extends JFrame {
	public static final int LENGTH = 30000;
	
	public static Player player;
	public static Boss boss;
	public static WallController wall;
	private LevelBackground background;
	public static Controller bulletsAndEnemies;
	
	public static int enemyCounter = 50;
	public static int circleCounter = enemyCounter / 2, triangleCounter = enemyCounter / 2;
	
	private int playerXSpeed = 4;
	private int enemyXSpeed = 2;
	private int xPositionFactor = (int) (Level.LENGTH * ((double) enemyXSpeed / playerXSpeed));

	private Enemy testEnemy;

	public Level() {
		player = new Player(200, 300, playerXSpeed);
		background = new LevelBackground(playerXSpeed);
		bulletsAndEnemies = new Controller();
		boss = new Boss(playerXSpeed);
		wall = new WallController(playerXSpeed);
		while (enemyCounter != 0)
			testEnemy = new Enemy(enemyXSpeed, xPositionFactor);
	}

	public void update() {
		if (player.movedDistance < Level.LENGTH)
			background.update();
		player.update();
		bulletsAndEnemies.update();
		boss.update();
		testEnemy.update();
		wall.update();
	}

	public void render(Graphics g) {
		background.render(g);
		bulletsAndEnemies.render(g);
		player.render(g);
		boss.render(g);
		testEnemy.render(g);
		wall.render(g);
	}
}
package exogorth.level;

import java.awt.Graphics;
import javax.swing.JFrame;

import exogorth.level.characters.Boss;
import exogorth.level.characters.Enemy;
import exogorth.level.characters.Player;

@SuppressWarnings("serial")
public class Level extends JFrame {
	public static final int LENGTH = 30000;
	public static int progress;

	private LevelBackground background;
	public static WallController wall;
	public static Player player;
	public static Boss boss;
	private Enemy enemy;
	public static Controller bulletsAndEnemies;

	public static int enemyCounter = 50;
	private int playerXSpeed = 4;
	private int enemyXSpeed = 2;

	public Level() {
		player = new Player(200, 300, playerXSpeed);
		background = new LevelBackground(playerXSpeed);
		bulletsAndEnemies = new Controller();
		boss = new Boss(playerXSpeed);
		wall = new WallController(playerXSpeed);
		while (enemyCounter != 0)
			enemy = new Enemy(enemyXSpeed, playerXSpeed);
	}

	public void update() {
		if (progress < LENGTH)
			background.update();
		wall.update();
		player.update();
		bulletsAndEnemies.update();
		boss.update();
		if (player.movedDistance <= LENGTH)
			enemy.update();
	}

	public void render(Graphics g) {
		background.render(g);
		wall.render(g);
		bulletsAndEnemies.render(g);
		player.render(g);
		boss.render(g);
		if (progress <= LENGTH)
			enemy.render(g);

	}
}
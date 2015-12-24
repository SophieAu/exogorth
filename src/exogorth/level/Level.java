package exogorth.level;

import java.awt.Graphics;
import javax.swing.JFrame;
import exogorth.level.characters.Enemy;
import exogorth.level.characters.Player;

@SuppressWarnings("serial")
public class Level extends JFrame {
	public static final int LENGTH = 30000;
	private Player player;
	private LevelBackground background;
	public static Controller bulletsAndEnemies;
	public static int enemyCounter = 50;
	public static int circleCounter = enemyCounter / 2, triangleCounter = enemyCounter / 2;

	private Enemy testEnemy;

	public Level() {
		player = new Player(200, 300, 4);
		background = new LevelBackground(4);
		bulletsAndEnemies = new Controller();
		while (enemyCounter != 0) {
			testEnemy = new Enemy(2);
			System.out.println("enemyCounter: " + enemyCounter);
			System.out.println("triangleCounter: " + triangleCounter);
			System.out.println("circleCounter: " + circleCounter);
			System.out.println("Anzahl Gegner: " + bulletsAndEnemies.getExistingEnemies().size());
		}
	}

	public void update() {
		background.update();
		player.update();
		bulletsAndEnemies.update();
		testEnemy.update();
	}

	public void render(Graphics g) {
		background.render(g);
		bulletsAndEnemies.render(g);
		player.render(g);
		testEnemy.render(g);
	}
}
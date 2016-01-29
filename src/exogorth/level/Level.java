package exogorth.level;

import java.awt.Graphics;
import javax.swing.JFrame;

import exogorth.STATE;
import exogorth.TheMain;
import exogorth.level.characters.Boss;
import exogorth.level.characters.Enemy;
import exogorth.level.characters.Player;
import exogorth.menu.MainMenu;

@SuppressWarnings("serial")
public class Level extends JFrame {
	public static final int LENGTH = 3000;
	public static int progress;

	private static LevelBackground background;
	public static WallController wall;
	public static Player player;
	public static Boss boss;
	public static CollisionController bulletsAndEnemies;

	public static int enemyCounter = 50;
	public static int circleCounter = Level.enemyCounter / 2, triangleCounter = Level.enemyCounter / 2;
	private static int playerXSpeed = 4;
	private static int enemyXSpeed = 2;

	public Level() {
		enemyCounter = 50;
		circleCounter = Level.enemyCounter / 2;
		triangleCounter = Level.enemyCounter / 2;
		System.out.println("Reset");
		player = new Player(200, 300, playerXSpeed);
		System.out.println("Progress: " + progress);
		background = new LevelBackground(playerXSpeed);
		bulletsAndEnemies = new CollisionController();
		System.out.println("Enemy-Zahl vorher: " + CollisionController.existingEnemies.size());
		boss = new Boss(playerXSpeed);
		wall = new WallController(playerXSpeed);
		while (enemyCounter != 0)
			bulletsAndEnemies.addEnemy(new Enemy(enemyXSpeed, playerXSpeed));
		System.out.println("Enemy-Zahl: " + CollisionController.existingEnemies.size());
	}

	public synchronized void update() {
		if (progress < LENGTH)
			background.update();
		wall.update();
		player.update();
		bulletsAndEnemies.update();
		if (progress > LENGTH)
			boss.update();
	}

	public synchronized void render(Graphics g) {
		background.render(g);
		wall.render(g);
		bulletsAndEnemies.render(g);
		player.render(g);
		if (progress > LENGTH)
			boss.render(g);
	}

	public static synchronized void backToMenu() {
		TheMain.State = STATE.MAINMENU;
		TheMain.currentScreen = new MainMenu();
	}
}
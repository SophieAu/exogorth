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
	static int level;
	static int levelNumber;

	private static LevelBackground background;
	public static WallController wall;
	public static Player player;
	public static Boss boss;
	public static CollisionController bulletsAndEnemies;

	public static int enemyCounter, circleCounter, triangleCounter;
	private static int playerXSpeed = 4;
	private static int enemyXSpeed = 2;

	public Level(int levelNumber) {
		Level.levelNumber = levelNumber;
		reset();
	}

	private static void reset() {
		enemyCounter = 50;
		circleCounter = Level.enemyCounter / 2;
		triangleCounter = Level.enemyCounter / 2;
		bulletsAndEnemies = new CollisionController();
		background = new LevelBackground(playerXSpeed);
		wall = new WallController(playerXSpeed);

		System.out.println("Reset");
		player = new Player(200, 300, playerXSpeed);
		boss = new Boss(levelNumber, playerXSpeed);
		while (enemyCounter != 0)
			bulletsAndEnemies.add(new Enemy(enemyXSpeed, playerXSpeed));
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
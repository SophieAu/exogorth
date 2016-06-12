package exogorth.level;

import java.awt.Graphics;
import javax.swing.JFrame;

import exogorth.STATE;
import exogorth.TheMain;
import exogorth.Settings;
import exogorth.level.controller.CollisionController;
import exogorth.level.controller.WallController;
import exogorth.level.model.Boss;
import exogorth.level.model.Enemy;
import exogorth.level.model.LevelBackground;
import exogorth.level.model.Player;
import exogorth.menu.MainMenu;

@SuppressWarnings("serial")
public class Level extends JFrame {
	public static int progress;
	static int levelNumber;

	private static LevelBackground background;
	public static WallController wall;
	public static Player player;
	public static Boss boss;
	public static CollisionController bulletsAndEnemies;

	public static int enemyCounter, circleCounter, triangleCounter;

	public Level(int levelNumber) {
		Level.levelNumber = levelNumber;
		reset();
	}

	private static void reset() {
		enemyCounter = 50;
		circleCounter = Level.enemyCounter / 2;
		triangleCounter = Level.enemyCounter / 2;
		bulletsAndEnemies = new CollisionController();
		background = new LevelBackground(Settings.SCROLLSPEED);
		wall = new WallController(Settings.SCROLLSPEED);

		System.out.println("Reset");
		player = new Player(200, 300, Settings.SCROLLSPEED);
		boss = new Boss(levelNumber, Settings.SCROLLSPEED);
		while (enemyCounter != 0)
			bulletsAndEnemies.add(new Enemy(Settings.ENEMYSCROLLSPEED, Settings.SCROLLSPEED));
	}

	public synchronized void update() {
		if (progress < Settings.LEVELLENGTH)
			background.update();
		wall.update();
		player.update();
		bulletsAndEnemies.update();
		if (progress > Settings.LEVELLENGTH)
			boss.update();
	}

	public synchronized void render(Graphics g) {
		background.render(g);
		wall.render(g);
		bulletsAndEnemies.render(g);
		player.render(g);
		if (progress > Settings.LEVELLENGTH)
			boss.render(g);
	}

	public static synchronized void backToMenu() {
		TheMain.State = STATE.MAINMENU;
		TheMain.currentScreen = new MainMenu();
	}
}
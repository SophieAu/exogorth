package exogorth.level;

import java.awt.Graphics;
import javax.swing.JFrame;

import exogorth.STATE;
import exogorth.TheMain;
import exogorth.Settings;
import exogorth.level.controller.CollisionController;
import exogorth.level.controller.WallController;
import exogorth.level.model.Boss;
import exogorth.level.model.ENEMYTYPE;
import exogorth.level.model.Enemy;
import exogorth.level.model.LevelBackground;
import exogorth.level.model.Player;
import exogorth.menu.MainMenu;

@SuppressWarnings("serial")
public class Level extends JFrame {
	public static int progress;
	int levelNumber;

	private static LevelBackground background;
	public static WallController wall;
	public static Player player;
	public static Boss boss;
	public static CollisionController bulletsAndEnemies;
	
	public Level(int levelNumber) {
		this.levelNumber = levelNumber;
		reset();
	}

	// bulletsAndEnemies list needs to be initialized before calling the player and boss or it will
	// crash the game because no bullets can be added.
	private void reset() {
		System.out.println("Reset"); // DEBUG--------------------------------------

		int enemyCounter = 0;
		if (levelNumber == 1)
			enemyCounter = Settings.LEVELONEENEMIES;
		if (levelNumber == 2)
			enemyCounter = Settings.LEVELTWOENEMIES;
		bulletsAndEnemies = new CollisionController(enemyCounter);

		background = new LevelBackground();
		wall = new WallController();
		player = new Player(200, 300);
		boss = new Boss(levelNumber);

		for (int i = 0; i < enemyCounter; i += 2) {
			bulletsAndEnemies.add(new Enemy(ENEMYTYPE.CIRCLE));
			bulletsAndEnemies.add(new Enemy(ENEMYTYPE.TRIANGLE));
		}
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
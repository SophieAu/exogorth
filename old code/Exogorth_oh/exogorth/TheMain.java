package exogorth;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class TheMain {
	public static int mode = 1;// 1:Men�; 2:Spiel; 3: Highscoreint
								// mode=1;//1:Men�; 2:Spiel; 3: Highscore
	public static MainMenu app;
	public static Draws ablauf = null;
	public static int worldY = 800;
	public static int worldX = 600;
	private static boolean bossDefeated = true;
	private static int hWasPressed = 1;
	private static ArrayList<Bullets> bullet = new ArrayList<Bullets>();
	private static ArrayList<Bullets> rainB = new ArrayList<Bullets>();
	private static ArrayList<Npc> npc = new ArrayList<Npc>();
	private static Highscore highscore;
	static boolean highscoreCalledAlready;
	public static boolean justStartedTheLevel = true;;

	public static void main(String[] args) throws InterruptedException, IOException {
		boolean firstTimeStarted = true;
		int speedPlayer = 4;
		Player player = new Player(speedPlayer, 200, 300, worldY, worldX, bullet, rainB);
		Background hg = new Background(speedPlayer);
		highscore = new Highscore();
		highscoreCalledAlready = false;

		Thread drawLevel = new Thread() {
			public void run() {
				resetLevel(player);
				levelOne(player);
				
				while (!(player.getMovedDistance() == 40000 && player.getHealth() >= 1 && bossDefeated)) {
					try {
						sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				resetLevel(player);
				levelTwo(player);
			}

			private void resetLevel(Player player) {		
				player.setMovedDistance(200);
				player.setPlayerX(200);
				player.setPlayerY(300);
				bullet.removeAll(bullet);
				npc.removeAll(npc);
			}

			private void levelOne(Player player) {
				try {

					bullet.add(new Bullets(-2, 100, 500, 13, bullet)); // Level1_Font
					bullet.add(new Bullets(-5, 500, 3200, 8, bullet)); // Wand(unten)
					npc.add(new Npc(1, 3600, 300, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3900, 450, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 4000, 200, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 4500, 250, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-3, 300, 3200, 3, bullet)); // PowerUp(2x Schaden)
					while (player.getMovedDistance() != 4000) { // 1
						sleep(3);
					}
					bullet.add(new Bullets(-5, 20, 2400, 9, bullet)); // Wand(oben)
					npc.add(new Npc(1, 1100, 300, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1500, 400, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 2000, 150, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2400, 230, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2800, 300, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3200, 450, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(2, 3600, 200, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 4000, 120, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-3, 120, 3000, 2, bullet)); // PowerUp(Heilung)
					while (player.getMovedDistance() != 8000) { // 2
						sleep(3);
					}
					bullet.add(new Bullets(-5, 500, 1600, 6, bullet)); // Wand(unten)
					bullet.add(new Bullets(-5, 20, 4400, 10, bullet)); // Wand(oben)
					npc.add(new Npc(1, 1100, 470, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1500, 320, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 270, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 2400, 300, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2600, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3400, 350, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3600, 150, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 4000, 120, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-3, 150, 3200, 2, bullet)); // PowerUp(Heilung)
					while (player.getMovedDistance() != 12000) { // 3
						sleep(3);
					}
					bullet.add(new Bullets(-5, 500, 1600, 6, bullet)); // Wand(unten)
					npc.add(new Npc(2, 1400, 300, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 1800, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2300, 420, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 2500, 220, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 3200, 300, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3600, 180, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 4300, 220, bullet, npc)); // Gegner(Kreis)
					bullet.add(new Bullets(-3, 420, 2000, 2, bullet)); // PowerUp(Heilung)
					while (player.getMovedDistance() != 16000) { // 4
						sleep(3);
					}
					bullet.add(new Bullets(-5, 20, 4000, 11, bullet)); // Wand(oben)
					npc.add(new Npc(1, 1100, 470, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 1500, 320, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2000, 270, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2600, 150, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3200, 400, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(2, 3600, 120, bullet, npc)); // Gegner(Kreis)
					while (player.getMovedDistance() != 20000) { // 5
						sleep(3);
					}
					bullet.add(new Bullets(-5, 500, 3200, 6, bullet)); // Wand(unten)
					npc.add(new Npc(1, 1000, 120, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1000, 220, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1000, 320, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 150, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 350, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 450, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3000, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3000, 300, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3000, 400, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-3, 420, 1000, 2, bullet)); // PowerUp(Heilung)
					bullet.add(new Bullets(-3, 250, 2000, 3, bullet)); // PowerUp(2x Schaden)
					while (player.getMovedDistance() != 24000) { // 6
						sleep(3);
					}
					bullet.add(new Bullets(-5, 20, 2400, 9, bullet)); // Wand(oben)
					npc.add(new Npc(1, 1400, 220, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 1900, 420, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2400, 150, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2600, 450, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3100, 300, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 3400, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 4100, 150, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 4200, 350, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 4600, 120, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 4800, 220, bullet, npc)); // Gegner(Kreis)
					bullet.add(new Bullets(-3, 350, 1400, 3, bullet)); // PowerUp(2x Schaden)
					while (player.getMovedDistance() != 28000) { // 7
						sleep(3);
					}
					bullet.add(new Bullets(-5, 500, 1200, 7, bullet)); // Wand(unten)
					bullet.add(new Bullets(-5, 20, 2400, 9, bullet)); // Wand(oben)
					npc.add(new Npc(1, 1000, 320, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1200, 420, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 1400, 220, bullet, npc)); // Gegner(Kreis)
					bullet.add(new Bullets(-3, 220, 3000, 2, bullet)); // PowerUp(Heilung)
					bullet.add(new Bullets(-3, 420, 3000, 3, bullet)); // PowerUp(2x Schaden)
					Npc bossOne = new Npc(3, 1000, 200, bullet, npc);
					while (player.getMovedDistance() != 33000) { // 8
						sleep(3);
					}
					npc.add(bossOne);
					while (bossOne.getX() != 550) {
						sleep(3);
					}
					bossOne.setSpeed(0);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			private void levelTwo(Player player) {
				try {
					Bullets ground = new Bullets(-5, 500, 3200, 16, bullet);
					Bullets worm = new Bullets(1, 100, -600, 12, bullet);
					bullet.add(new Bullets(-2, 100, 500, 14, bullet)); // Level2_Font
					bullet.add(ground); // Boden
					while (ground.getBulletX() != 0) {
						sleep(3);
					}
					ground.setSpeed(0);
					bullet.add(worm);
					while (worm.getBulletX() != -500) {
						sleep(3);
					}
					worm.setSpeed(0);

					npc.add(new Npc(1, 4000, 375, bullet, npc)); // Dreieck
					npc.add(new Npc(1, 4100, 200, bullet, npc)); // Dreieck
					bullet.add(new Bullets(-5, 50, 3400, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 100, 3420, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 200, 3390, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 250, 3410, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 400, 3390, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 450, 3410, 5, bullet)); // Asteroid

					while (player.getMovedDistance() != 4000) {
						sleep(3);
					}

					npc.add(new Npc(1, 1100, 300, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1500, 400, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 2000, 150, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2400, 230, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2800, 30, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3200, 450, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(2, 3600, 200, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 4000, 120, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-4, 40, 2840, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-3, 400, 3700, 5, bullet)); // Asteroid

					bullet.add(new Bullets(-3, 350, 3000, 3, bullet)); // PowerUp(2x Schaden)

					while (player.getMovedDistance() != 8000) { // 2
						sleep(3);
					}

					npc.add(new Npc(1, 1100, 470, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1500, 320, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 270, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 2400, 30, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2600, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3400, 350, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3600, 150, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 4000, 50, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-5, 50, 3000, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 175, 3200, 5, bullet)); // Asteroid

					while (player.getMovedDistance() != 12000) { // 3
						sleep(3);
					}

					npc.add(new Npc(2, 1400, 310, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 1820, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2300, 420, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 2510, 230, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 3200, 320, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3630, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 4300, 220, bullet, npc)); // Gegner(Kreis)
					bullet.add(new Bullets(-5, 50, 3500, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 375, 3600, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-3, 220, 3000, 2, bullet)); // PowerUp(Heilung)

					while (player.getMovedDistance() != 16000) { // 4
						sleep(3);
					}

					npc.add(new Npc(1, 1100, 70, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 1500, 320, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2000, 40, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2600, 150, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3200, 400, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(2, 3600, 120, bullet, npc)); // Gegner(Kreis)
					bullet.add(new Bullets(-5, 50, 4000, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 200, 3850, 5, bullet)); // Asteroid

					while (player.getMovedDistance() != 20000) { // 5
						sleep(3);
					}

					npc.add(new Npc(1, 1000, 50, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1000, 220, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 1000, 320, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 150, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 350, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2000, 450, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3000, 200, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3000, 300, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 3000, 400, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-5, 60, 3000, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 200, 3200, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-3, 420, 4000, 2, bullet)); // PowerUp(Heilung)

					while (player.getMovedDistance() != 24000) { // 6
						sleep(3);
					}

					npc.add(new Npc(1, 1400, 220, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 1900, 420, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 2400, 40, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(1, 2600, 450, bullet, npc)); // Gegner(Dreieck)
					npc.add(new Npc(2, 3100, 300, bullet, npc)); // Gegner(Kreis)
					npc.add(new Npc(1, 3400, 200, bullet, npc)); // Gegner(Dreieck)
					bullet.add(new Bullets(-5, 50, 3000, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 200, 3250, 5, bullet)); // Asteroid

					while (player.getMovedDistance() != 28000) { // 7
						sleep(3);
					}
					bullet.add(new Bullets(-5, 50, 2000, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 175, 2220, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 230, 2390, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 250, 2110, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 400, 2090, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 450, 2410, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 460, 2000, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 50, 2500, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 100, 2620, 5, bullet)); // Asteroid
					bullet.add(new Bullets(-5, 200, 2300, 5, bullet)); // Asteroid

					bullet.add(new Bullets(-3, 220, 3000, 2, bullet)); // PowerUp(Heilung)
					bullet.add(new Bullets(-3, 420, 3000, 3, bullet)); // PowerUp(2x Schaden)
					Npc bossTwo = new Npc(4, 1000, 200, bullet, npc);

					while (player.getMovedDistance() != 33000) { // 8
						sleep(3);
					}
					npc.add(bossTwo);

					while (bossTwo.getX() != 550) {
						sleep(3);
					}
					bossTwo.setSpeed(0);

					bullet.removeAll(bullet);
					npc.removeAll(npc);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		drawLevel.start();

		// Thread f�r Spieler
		Thread spielerThread = new Thread() {
			@Override
			public void run() {
				try {
					player.update();
					justStartedTheLevel = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		spielerThread.start();

		if (firstTimeStarted) {
			drawLevel(player, hg);
			firstTimeStarted = false;
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainMenu app = new MainMenu();
				app.setVisible(true);
			}
		});

		while (true) {
			while (player.getHealth() > 0) {
				double zeit = System.currentTimeMillis();
				if (KeyBoard.knopfgedrueckt(KeyEvent.VK_H)) {
					hWasPressed *= -1;
					Thread.sleep(200);
				}
				if (hWasPressed == 1) {
					if (mode == 2) {
						spielerThread.run();
						hg.update();
						ablauf.drawAgain();
						Npc currentNPC;

						for (int i = 0; i < bullet.size(); i++) {
							bullet.get(i).update();
						}
						for (int i = 0; i < rainB.size(); i++) {
							rainB.get(i).update();
						}

						for (int i = 0; i < npc.size(); i++) {
							currentNPC = npc.get(i);
							currentNPC.update();
							if (currentNPC.npcX < -currentNPC.getPicture().getWidth() || currentNPC.life <= 0) {
								if (currentNPC.life <= 0)
									getPoints(currentNPC);
								npc.remove(currentNPC);
								--i;
							}
						}
					}
				} else {
					ablauf.drawHelp();
				}
				while (zeit + 10 > System.currentTimeMillis())
					Thread.sleep(1);
			}

			// Hier das Highscorefenster einbauen
			// Die unteren beiden Zeilen l�schen
			
			if(!firstTimeStarted && !highscoreCalledAlready){
				Highscore.finalScore(player.getHealth());
				highscoreCalledAlready = true;
				main(args);
				player.setPlayerY(200);
				player.setPlayerX(300);
				System.exit(0);
			}
			
			

		}
	}

	private static void getPoints(Npc current) {
		if (current.typ == 1 || current.typ == 2)
			Highscore.currentScore += 5;
		if (current.typ == 3)
			Highscore.currentScore += 30;
		if (current.typ == 4)
			Highscore.currentScore += 50;
	}

	private static void drawLevel(Player player, Background hg) {
		ablauf = new Draws(player, hg, bullet, npc);
		ablauf.setVisible(true);
		ablauf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ablauf.setSize(800, 600);
		ablauf.setLocationRelativeTo(null);
		ablauf.buffer();
		ablauf.setResizable(false);
		ablauf.setVisible(false);
		highscoreCalledAlready = true;
	}

}

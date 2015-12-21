package exogorth.toberefactored;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player {
	// geiles Leben
	private int life = 3;
	// schaden erhöht?
	private boolean dmgI;
	// Zeitbuffer falls man von mehreren Kugeln gleichzeitig getroffen wird
	private int immortalityCounter = 3;
	// boundingbox
	private Rectangle bounding;
	private int playerY, playerX;
	private int worldY, worldX, speed;
	private BufferedImage[] playerPicture = new BufferedImage[4];
	// Das Spieler-Bild wird alle 30 Millisekunden getauscht
	private final static float animatedTime = 3;
	// Geht alle im Array gespeicherte Bilder durch
	private int timeCounterForAnimation = 0;
	private int zeitani = 1;
	// Kugeln und co
	private int shootingTimer = 20;
	private int reload = 0;
	private int speedBullet = 7;
	private ArrayList<Bullets> bullet;
	// Wahrheitswert dafür das der spieler bei 2/3 der Karte angekommen ist
	boolean endOfWorld;
	private int movedDistance;

	public int getPlayerY() {
		return playerY;
	}

	public float getSpeed() {
		return speed;
	}

	public BufferedImage getPlayerPicture() {
		if (playerPicture.length == 0)
			return null;
		return (playerPicture[timeCounterForAnimation]);
	}

	public void setPlayerY(int spielerY) {
		this.playerY = spielerY;
	}

	public float getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int spielerX) {
		this.playerX = spielerX;
	}

	public Player(int geschwindigkeit, int startX, int startY, int WeltX, int WeltY, ArrayList<Bullets> bullet)
			throws IOException {
		this.bullet = bullet;

		playerPicture[0] = ImageIO.read(getClass().getResourceAsStream("/Images/player.png"));
		playerPicture[1] = ImageIO.read(getClass().getResourceAsStream("/Images/player.png"));
		playerPicture[2] = ImageIO.read(getClass().getResourceAsStream("/Images/player.png"));
		playerPicture[3] = playerPicture[1];
		bounding = new Rectangle(startX, startY, playerPicture[timeCounterForAnimation].getWidth(),
				playerPicture[timeCounterForAnimation].getHeight());
		playerY = startY;
		playerX = startX;
		this.worldY = WeltY;
		this.worldX = WeltX;
		this.speed = geschwindigkeit;
		movedDistance = startX;
	}

	public void update() throws IOException {
		// unverwundbarkeit hochzählen
		immortalityCounter++;
		if (immortalityCounter > 3)
			immortalityCounter = 3;
		// prüft ob spieler objekt berührt
		for (int i = 0; i < bullet.size(); i++) {
			Bullets tester = bullet.get(i);
			if (this.bounding.intersects(tester.getCollisionBox())) {
				switch (tester.getType()) {
				case 2:
					bullet.remove(i);
					if (life < 3)
						life++;
					i--;
					break;
				case 3:
					bullet.remove(i);
					dmgI = true;
					i--;
					break;
				case 4: // enemy bullet
					damageTaken();
					bullet.remove(i);
					break;
				case 5:// Balken oben
					damageTaken();
					break;
				case 6:// Balken unten
					damageTaken();
					break;

				default:
					break;

				}
			}
		}
		// beschränkung auf der Karte - maximale Bewegung 2/3
		if (playerX > ((worldX * 2) / 3))
			endOfWorld = true;
		else
			endOfWorld = false;
		// Regenbogen
		// bullet.add(new Bullets(-speed, playerY
		// + (playerPicture[timeCounterForAnimation].getHeight() / 2) - 9,
		// playerX, 1, bullet));
		// Bewegungsreaktion
		if (zeitani < 1) {
			zeitani = 100;
			if (timeCounterForAnimation >= animatedTime)
				timeCounterForAnimation = 0;
			else
				timeCounterForAnimation++;
		} else
			zeitani--;
		// schießen
		if (reload < shootingTimer) {
			reload++;
		}
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_SPACE) && reload == shootingTimer) {
			reload = 0;
			bullet.add(new Bullets(speedBullet, playerY + (playerPicture[timeCounterForAnimation].getHeight() / 2) - 3,
					playerX, 0, bullet));
		}
		// Bewegung
		// links
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_A) || KeyBoard.knopfgedrueckt(KeyEvent.VK_LEFT)) {
			for (int i = 0; i < speed; i++) {
				collision(-1, 0);
			}
		}

		// rechts
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_D) || KeyBoard.knopfgedrueckt(KeyEvent.VK_RIGHT)) {
			if (!endOfWorld) {
				for (int i = 0; i < speed; i++) {
					collision(1, 0);

				}
			}
		}
		// Oben
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_W) || KeyBoard.knopfgedrueckt(KeyEvent.VK_UP)) {
			for (int i = 0; i < speed - 2; i++) {
				collision(0, -1);
			}

		}
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_S) || KeyBoard.knopfgedrueckt(KeyEvent.VK_DOWN)) {
			playerY += speed - 2;
		}
		// Reaktion bei Weltende
		if (playerY + bounding.height > worldY)
			playerY = worldY - bounding.height;
		if (playerX + bounding.width > worldX)
			playerX = worldX - bounding.width;
		// Balken des Fensters zählt mit
		// Dass das Spielerbild die richtige Höhe hat ist reiner Zufall, aber
		// check
		if (playerY < playerPicture[timeCounterForAnimation].getHeight())
			playerY = playerPicture[timeCounterForAnimation].getHeight();
		if (playerX < 0)
			playerX = 0;
		bounding.x = playerX;
		bounding.y = playerY;
		movedDistance += speed;
		System.out.println(movedDistance);
	}

	private void damageTaken() {
		if (life <= 1) {
		}
		// death();
		else if (immortalityCounter >= 3) {
			life--;
		}
		immortalityCounter = 0;
	}

	public Rectangle getBounding() {
		return bounding;
	}

	public void setBounding(int x, int y) {
		bounding.x = x;
		bounding.y = y;
	}

	// Zu Nutzen mit den X und Y Werten 1 und 0 -> Schleife
	private void collision(int x, int y) {
		bounding.x += x;
		bounding.y += y;
		playerX = bounding.x;
		playerY = bounding.y;
	}

	public boolean getDmgI() {
		return dmgI;
	}

	public int getMovedDistance() {
		return movedDistance;
	}

	public int getHealth() {
		return life;
	}

	public void setHealth(int health) {
		life = health;
	}

	public void setMovedDistance(int x) {
		movedDistance = x;
	}
}

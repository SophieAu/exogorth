package exogorth.toberefactored;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import exogorth.Window;

public class Player {
	private BufferedImage playerPicture;
	private Rectangle collisionBox;
	private int playerX = 300, playerY = 200;
	private int movedDistance; // used for deciding when the new NPCs should show up

	// Player Attributes
	private int playerSpeed;
	private int lives = 3;
	private int damageGracePeriod = 3;
	@SuppressWarnings("unused")
	private boolean doubleDamage;

	// Kugeln und co
	private int shootingTimer = 20;
	private int reload = 0;
	private int bulletSpeed = 7;
	// private ArrayList<? extends FlyingObject> damagingObjects;
	private ArrayList<Bullets> bullet;

	// CONSTRUCTOR
	public Player(int playerSpeed, ArrayList<Bullets> bullet) throws IOException {
		try {
			playerPicture = ImageIO.read(getClass().getResourceAsStream("/Images/Game/Player.png"));
		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Spielerbild wurde nicht gefunden.");
			e.printStackTrace();
		}
		collisionBox = new Rectangle(playerX, playerY, playerPicture.getWidth(), playerPicture.getHeight());

		this.playerSpeed = playerSpeed;
		this.bullet = bullet;
		movedDistance = playerX;
	}

	public void update() throws IOException {
		// unverwundbarkeit hochzählen
		damageGracePeriod++;
		if (damageGracePeriod > 3)
			damageGracePeriod = 3;
		// prüft ob spieler objekt berührt
		for (int i = 0; i < bullet.size(); i++) {
			Bullets tester = bullet.get(i);
			if (this.collisionBox.intersects(tester.getCollisionBox())) {
				switch (tester.getType()) {
				case 2:
					bullet.remove(i);
					if (lives < 3)
						lives++;
					i--;
					break;
				case 3: // DoubleDamage
					bullet.remove(i);
					// doubleDamage = true;
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
		// schießen
		if (reload < shootingTimer) {
			reload++;
		}

		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_SPACE) && reload == shootingTimer) {
			reload = 0;
			bullet.add(new Bullets(bulletSpeed, playerY + (playerPicture.getHeight() / 2) - 3, playerX, 0, bullet));
		}

		// Bewegung
		// links
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_A) || KeyBoard.knopfgedrueckt(KeyEvent.VK_LEFT)) {
			playerX -= playerSpeed;
		}
		// rechts
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_D) || KeyBoard.knopfgedrueckt(KeyEvent.VK_RIGHT)) {
			if (playerX <= ((Window.width * 2) / 3))
				playerX += playerSpeed;
		}
		// Oben
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_W) || KeyBoard.knopfgedrueckt(KeyEvent.VK_UP)) {
			playerY -= playerSpeed - 2;
		}
		// UNTEN
		if (KeyBoard.knopfgedrueckt(KeyEvent.VK_S) || KeyBoard.knopfgedrueckt(KeyEvent.VK_DOWN)) {
			playerY += playerSpeed - 2;
		}

		// Reaktion bei Weltende
		if (playerY + collisionBox.height > Window.height)
			playerY = Window.height - collisionBox.height;
		if (playerX + collisionBox.width > Window.width)
			playerX = Window.width - collisionBox.width;
		// Balken des Fensters zählt mit
		// Dass das Spielerbild die richtige Höhe hat ist reiner Zufall, aber check
		if (playerY < playerPicture.getHeight())
			playerY = playerPicture.getHeight();
		if (playerX < 0)
			playerX = 0;
		collisionBox.x = playerX;
		collisionBox.y = playerY;
		movedDistance += playerSpeed;
		System.out.println(movedDistance);
	}

	private void damageTaken() {
		if (lives <= 1) {
		}
		// death();
		else if (damageGracePeriod >= 3) {
			lives--;
		}
		damageGracePeriod = 0;
	}

	// RANDOM GETTERS AND SETTERS
	public Rectangle getBounding() {
		return collisionBox;
	}

	public int getMovedDistance() {
		return movedDistance;
	}

	public int getHealth() {
		return lives;
	}

	public void setMovedDistance(int x) {
		movedDistance = x;
	}

	public BufferedImage getPlayerPicture() {
		return playerPicture;
	}

	public void setPlayerY(int spielerY) {
		this.playerY = spielerY;
	}

	public void setPlayerX(int spielerX) {
		this.playerX = spielerX;
	}
}

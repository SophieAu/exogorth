package exogorth.toberefactored;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import exogorth.Window;
import exogorth.gamelogic.Keyboard;

public class Player {
	private BufferedImage playerPicture;
	private Rectangle collisionBox;
	private int playerX = 300, playerY = 200;
	private int movedDistance = playerX; // used for deciding when the new NPCs should show up

	private int horizontalSpeed, verticalSpeed;
	private int reloadTime = 20;
	private int reload = 0;
	private int bulletSpeed = 7;
	private int damageGracePeriod = 3;
	private int lives = 3;
	// private boolean doubleDamage;

	public Player(int horizontalSpeed, ArrayList<Bullets> bullet) throws IOException {
		try {
			playerPicture = ImageIO.read(getClass().getResourceAsStream("/Images/Game/Player.png"));
		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Spielerbild wurde nicht gefunden.");
			e.printStackTrace();
		}
		collisionBox = new Rectangle(playerX, playerY, playerPicture.getWidth(), playerPicture.getHeight());

		this.horizontalSpeed = horizontalSpeed;
		verticalSpeed = horizontalSpeed - 2;
		this.bullet = bullet;
	}

	public void update() {
		if (damageGracePeriod == 3)
			collision();
		else
			damageGracePeriod++;

		shooting();
		movement();
	}

	public void movement() {
		if (Keyboard.pressedKey(KeyEvent.VK_LEFT) || Keyboard.pressedKey(KeyEvent.VK_A)) {
			playerX -= horizontalSpeed;
			if (playerX < Window.borderWidth)
				playerX = Window.borderWidth;
		}

		else if ((Keyboard.pressedKey(KeyEvent.VK_RIGHT) || Keyboard.pressedKey(KeyEvent.VK_D)) & playerX <= ((Window.width * 2) / 3)) {
			playerX += horizontalSpeed;
		}

		else if (Keyboard.pressedKey(KeyEvent.VK_UP) || Keyboard.pressedKey(KeyEvent.VK_W)) {
			playerY -= verticalSpeed;
			if (playerY < Window.titleBarHeight)
				playerY = Window.titleBarHeight;
		}

		else if (Keyboard.pressedKey(KeyEvent.VK_DOWN) || Keyboard.pressedKey(KeyEvent.VK_S)) {
			playerY += verticalSpeed;
			if (playerY + collisionBox.height > Window.height - Window.borderWidth)
				playerY = Window.height - collisionBox.height - Window.borderWidth;
		}

		collisionBox.x = playerX;
		collisionBox.y = playerY;
		movedDistance += horizontalSpeed;

		// ONLY HERE FOR TESTING PURPOSES
		if (movedDistance % 100 == 0)
			System.out.println(movedDistance);
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// NOT REFACTORED YET

	// private ArrayList<? extends FlyingObject> damagingObjects;
	private ArrayList<Bullets> bullet;

	public void collision() {
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
	}

	public void shooting() {
		if (reload < reloadTime) {
			reload++;
			return;
		}

		if (Keyboard.pressedKey(KeyEvent.VK_SPACE)) {
			reload = 0;
			bullet.add(new Bullets(bulletSpeed, playerY + (playerPicture.getHeight() / 2) - 3, playerX + playerPicture.getWidth(), 0, bullet));
		}
	}

	private void damageTaken() {
		if (lives == 1)
			lives = 0;
		// death();
		else if (damageGracePeriod >= 3)
			lives--;

		damageGracePeriod = 0;
	}

	//
	//
	//
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
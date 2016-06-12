package exogorth.level.model;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import exogorth.Settings;
import exogorth.level.Keyboard;
import exogorth.level.Level;
import exogorth.level.controller.WallController;

public class Player extends GameCharacter {
	private int damageGracePeriod = 120;
	public int gracePeriodCounter;
	// private boolean doubleDamage;

	public Player(int xPosition, int yPosition) {
		super(Settings.SCROLLSPEED);

		Level.progress = xPosition;
		bulletSpeed = 7;
		reloadTime = 20;
		lives = Settings.PLAYERLIVES;
		ySpeed = Settings.SCROLLSPEED;
		gracePeriodCounter = damageGracePeriod;

		image = loader.load("Game/player");

		this.xPosition = xPosition;
		this.yPosition = yPosition;
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());
	}

	@Override
	protected void shooting() {
		if (!reloading() && Keyboard.pressedKey(KeyEvent.VK_SPACE)) {
			reload = 0;
			bulletList.add(new Bullet(xPosition + image.getWidth(), yPosition + (image.getHeight() / 2), bulletSpeed, BULLETTYPE.PLAYER));
		}
	}

	// extract the "check for hitting the border" if statements and put them into a method
	// blahblah(int position);
	@Override
	protected void movement() {
		if (Keyboard.pressedKey(KeyEvent.VK_LEFT) || Keyboard.pressedKey(KeyEvent.VK_A)) {
			xPosition -= xSpeed;
			if (xPosition < 0)
				xPosition = 0;
		}

		if ((Keyboard.pressedKey(KeyEvent.VK_RIGHT) || Keyboard.pressedKey(KeyEvent.VK_D)) & xPosition <= ((Settings.WIDTH * 2) / 3))
			xPosition += xSpeed;

		if (Keyboard.pressedKey(KeyEvent.VK_UP) || Keyboard.pressedKey(KeyEvent.VK_W)) {
			yPosition -= ySpeed;
			if (yPosition < 0)
				yPosition = 0;
		}

		if (Keyboard.pressedKey(KeyEvent.VK_DOWN) || Keyboard.pressedKey(KeyEvent.VK_S)) {
			yPosition += ySpeed;
			if (yPosition + collisionBox.height > Settings.HEIGHT)
				yPosition = Settings.HEIGHT - collisionBox.height;
		}

		collisionBox.x = xPosition;
		collisionBox.y = yPosition;

		if (wallCollision())
			yPosition = yPosition <= 400 ? Wall.height : Settings.HEIGHT - Wall.height - image.getHeight();

		collisionBox.y = yPosition;

		Level.progress += xSpeed;

		// ONLY HERE FOR TESTING PURPOSES
		if (Level.progress % 100 == 0)
			System.out.println("Player Position: " + Level.progress);
	}

	private boolean wallCollision() {
		if (collisionBox.intersects(WallController.currentFirst.collisionBox) || collisionBox.intersects(WallController.currentSecond.collisionBox)) {
			hit();
			return true;
		}
		return false;
	}

	public void damage() {
		lives--;
		// if (lives == 0)
		// death();
		System.out.println("Lives left: " + lives);
	}

	@Override
	public synchronized void update() {
		super.update();
		if (gracePeriodCounter < damageGracePeriod)
			gracePeriodCounter++;
	}

	// turned off for testing purposes (see lines 91, 92)
	@SuppressWarnings("unused")
	private void death() {
		System.out.println("YOU JUST DIED");
		Level.backToMenu();
	}

	public void hit() {
		if (gracePeriodCounter < damageGracePeriod)
			return;
		damage();
		gracePeriodCounter = 0;
	}
}
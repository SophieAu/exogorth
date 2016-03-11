package exogorth.level.characters;

import java.awt.Rectangle;
import java.util.Random;

import exogorth.TheMain;
import exogorth.Window;
import exogorth.level.GameCharacter;
import exogorth.level.Level;
import exogorth.level.flyingobject.Bullet;
import exogorth.level.flyingobject.TYPE;

public class Boss extends GameCharacter {
	int startPosition;
	private Random random = new Random();
	public int yDirection;
	private int directionChangeCountdown;
	private int levelNumber;

	public Boss(int levelNumber, int xSpeed) {
		super(-xSpeed);
		this.levelNumber = levelNumber;
		ySpeed = 0;
		reloadTime = 25;
		if (levelNumber == 1) {
			lives = 20;
			image = loader.load("Game/bossOne");
		} else if (levelNumber == 2) {
			lives = 30;
			image = loader.load("Game/bossTwo");
		}
		xPosition = Window.WIDTH;
		yPosition = random.nextInt(Window.HEIGHT - image.getHeight());
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());
	}

	protected void movement() {
		if (xPosition <= Window.WIDTH)
			yMovementPattern();

		if (xPosition > (Window.WIDTH - image.getWidth() / 2))
			xPosition += xSpeed;

		collisionBox.x = xPosition;
		collisionBox.y = yPosition;

		// TEST------------------------------------------------------------------------------------------------------------------------------
		if (xPosition % 100 == 0 && xPosition != 700)
			System.out.println("Boss Position: " + xPosition);
	}

	private void yMovementPattern() {
		if (directionChangeCountdown == 0) {
			yPosition++;
			yDirection = -1 * (random.nextInt(3) - 1);
			directionChangeCountdown = random.nextInt(100);
			return;
		}

		yPosition += 2 * yDirection;
		if (yPosition < 0 || yPosition + collisionBox.height > Window.HEIGHT) {
			yPosition = yPosition <= 0 ? 0 : (Window.HEIGHT - collisionBox.height);
			yDirection *= -1;
		}
		directionChangeCountdown--;
	}

	protected void shooting() {
		if (reloading())
			return;

		// FOR NOW ONLY THE CIRCLEBULLET PATTERN
		reload = random.nextInt(10) - random.nextInt(10);
		bulletList.add(new Bullet(xPosition, yPosition + (image.getHeight() / 2), 7, TYPE.CIRCLEBULLET));
	}

	public void death() {
		if (levelNumber == 1)
			TheMain.level = new Level(2);
		else if (levelNumber == 2)
			Level.backToMenu();
	}
}

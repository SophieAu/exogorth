package exogorth.level.characters;

import java.awt.Rectangle;
import java.util.Random;

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

	public Boss(int xSpeed) {
		super(-xSpeed);
		ySpeed = 0;

		image = loader.load("Game/bossOne");
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());

		reloadTime = 25;
		lives = 20;
		xPosition = Level.LENGTH;
		yPosition = random.nextInt(Window.REALHEIGHT - image.getHeight());
	}

	public void movement() {
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
		if (yPosition < 0 || yPosition + collisionBox.height > Window.REALHEIGHT) {
			yPosition = yPosition <= 0 ? 0 : (Window.REALHEIGHT - collisionBox.height);
			yDirection *= -1;
		}
		directionChangeCountdown--;
	}

	protected void shooting() {
		if (reloading())
			return;

		// FOR NOW ONLY THE CIRCLEBULLET PATTERN
		reload = random.nextInt(10) - random.nextInt(10);
		bulletList.addBullet(new Bullet(xPosition, yPosition + (image.getHeight() / 2), 7, TYPE.CIRCLEBULLET));
	}
}

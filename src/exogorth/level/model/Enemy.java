package exogorth.level.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import exogorth.Settings;
import exogorth.level.Level;
import exogorth.level.controller.WallController;

public class Enemy extends GameCharacter {
	private ENEMYTYPE EnemyType;
	private Random random = new Random();
	public int ySign;
	private int directionChangeCountdown;
	private int randomType;
	private int xPositionFactor;

	public Enemy() {
		super(Settings.ENEMYSPEED);
		randomType = random.nextInt(2);

		if ((randomType == 0 || Level.triangleCounter == 0) && Level.circleCounter != 0) {
			EnemyType = ENEMYTYPE.CIRCLE;
			image = loader.load("Game/enemyCircle");
			Level.circleCounter--;
		} else if ((randomType == 1 || Level.circleCounter == 0) && Level.triangleCounter != 0) {
			EnemyType = ENEMYTYPE.TRIANGLE;
			image = loader.load("Game/enemyTriangle");
			Level.triangleCounter--;
		}
		Level.enemyCounter--;

		reloadTime = 25;
		bulletSpeed = 7;
		lives = 2;

		xPositionFactor = (int) (Settings.LEVELLENGTH * ((double) Settings.ENEMYSPEED / Settings.SCROLLSPEED));
		xPosition = (random.nextInt(xPositionFactor - image.getWidth() - 1000) + 1000);
		yPosition = (random.nextInt(Settings.HEIGHT - image.getHeight() - 2 * Wall.height) + Wall.height);
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());
	}

	protected void shooting() {
		if (reloading())
			return;

		if (this.EnemyType == ENEMYTYPE.CIRCLE) {
			reload = random.nextInt(10) - random.nextInt(10);
			bulletList.add(new Bullet(xPosition, yPosition + (image.getHeight() / 2), 7, BULLETTYPE.CIRCLE));
		}
		
		if (this.EnemyType == ENEMYTYPE.TRIANGLE) {
			// TODO: IMPLEMENT TRIANGLE PATTERN HERE
		}
	}

	@Override
	protected void movement() {
		xPosition -= xSpeed;

		if (xPosition <= Settings.WIDTH)
			yMovementPatter();

		collisionBox.x = xPosition;
		collisionBox.y = yPosition;
	}

	private void yMovementPatter() {
		if (directionChangeCountdown == 0) {
			yPosition++; // TODO: CHECK IF I CAN DELETE THIS
			ySign = -1 * (random.nextInt(3) - 1);
			directionChangeCountdown = random.nextInt(100);
			return;
		}

		yPosition += 2 * ySign;
		if (yPosition < 0 || yPosition + collisionBox.height > Settings.HEIGHT) {
			yPosition = yPosition <= 0 ? 0 : (Settings.HEIGHT - collisionBox.height);
			ySign *= -1;
		}
		if (wallCollision())
			ySign = yPosition <= Settings.HEIGHT/2 ? 1 : -1;

		directionChangeCountdown--;
	}

	private boolean wallCollision() {
		return (collisionBox.intersects(WallController.currentFirst.collisionBox)
				|| collisionBox.intersects(WallController.currentSecond.collisionBox));
	}

	public boolean outOfBounds() {
		return ((xPosition + image.getWidth()) < 0);
	}

	@Override
	public void render(Graphics g) {
		if (xPosition <= Settings.WIDTH)
			super.render(g);
	}
}

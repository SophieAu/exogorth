package exogorth.level.characters;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import exogorth.Window;
import exogorth.level.GameCharacter;
import exogorth.level.Level;
import exogorth.level.WallController;
import exogorth.level.flyingobject.Bullet;
import exogorth.level.flyingobject.TYPE;

public class Enemy extends GameCharacter {
	private ENEMYTYPE EnemyType;
	private Random random = new Random();
	public int ySign;
	private int directionChangeCountdown;
	private int randomType;
	private int xPositionFactor;

	public static int circleCounter = Level.enemyCounter / 2, triangleCounter = Level.enemyCounter / 2;

	public Enemy(int xSpeed, int playerXSpeed) {
		super(xSpeed);
		randomType = random.nextInt(2);

		if ((randomType == 0 || triangleCounter == 0) && circleCounter != 0) {
			EnemyType = ENEMYTYPE.CIRCLE;
			image = loader.load("Game/enemyCircle");
			circleCounter--;
		} else if ((randomType == 1 || circleCounter == 0) && triangleCounter != 0) {
			EnemyType = ENEMYTYPE.TRIANGLE;
			image = loader.load("Game/enemyTriangle");
			triangleCounter--;
		}
		Level.enemyCounter--;

		reloadTime = 25;
		bulletSpeed = 7;
		lives = 2;

		xPositionFactor = (int) (Level.LENGTH * ((double) xSpeed / playerXSpeed));
		xPosition = (random.nextInt(xPositionFactor - image.getWidth() - 1000) + 1000);
		yPosition = random.nextInt(Window.REALHEIGHT - image.getHeight() - 10); // 10 is an error
																				// margin
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());
		Level.bulletsAndEnemies.addEnemy(this);
	}

	protected void shooting() {
		if (reloading())
			return;

		if (this.EnemyType == ENEMYTYPE.CIRCLE) {
			reload = random.nextInt(10) - random.nextInt(10);
			bulletList.addBullet(new Bullet(xPosition, yPosition + (image.getHeight() / 2), 7, TYPE.CIRCLEBULLET));
		}
		// IMPLEMENT TRIANGLE PATTERN HERE
	}

	@Override
	public void movement() {
		xPosition -= xSpeed;

		if (xPosition <= Window.WIDTH)
			yMovementPatter();

		collisionBox.x = xPosition;
		collisionBox.y = yPosition;
	}

	private void yMovementPatter() {
		if (directionChangeCountdown == 0) {
			yPosition++;
			ySign = -1 * (random.nextInt(3) - 1);
			directionChangeCountdown = random.nextInt(100);
			return;
		}

		yPosition += 2 * ySign;
		if (yPosition < 0 || yPosition + collisionBox.height > Window.REALHEIGHT) {
			yPosition = yPosition <= 0 ? 0 : (Window.REALHEIGHT - collisionBox.height);
			ySign *= -1;
		}
		if (wallCollision()){
			ySign = yPosition <= 400 ? 1 : -1;
		}
		
		directionChangeCountdown--;
	}

	private boolean wallCollision() {
		return (collisionBox.intersects(WallController.currentFirst.collisionBox) || collisionBox.intersects(WallController.currentSecond.collisionBox));
	}

	public boolean outOfBounds() {
		return ((xPosition + image.getWidth()) < 0);
	}

	@Override
	public void render(Graphics g) {
		if (xPosition <= Window.WIDTH)
			super.render(g);
	}
}

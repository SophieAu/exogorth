package exogorth.level.characters;

import java.awt.Rectangle;
import java.util.Random;

import exogorth.Window;
import exogorth.level.GameCharacter;
import exogorth.level.Level;
import exogorth.level.flyingobject.Bullet;
import exogorth.level.flyingobject.TYPE;

public class Enemy extends GameCharacter {
	private ENEMYTYPE EnemyType;
	private Random random = new Random();
	private int ySign;
	private int directionChangeCountdown;
	private int randomType;

	public Enemy(int xPosition, int yPosition, int xSpeed, ENEMYTYPE EnemyType) {
		super(xPosition, yPosition, xSpeed);
		this.EnemyType = EnemyType;
		lives = 2;
		yPosition = Window.HEIGHT / 2;

		if (this.EnemyType == ENEMYTYPE.CIRCLE) {
			image = loader.load("Game/enemyCircle");
			reloadTime = 15;
			bulletSpeed = 7;
		}
		if (this.EnemyType == ENEMYTYPE.TRIANGLE) {
			image = loader.load("Game/enemyTriangle");
			reloadTime = 15;
		}

		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());

	}

	public Enemy(int xSpeed) {
		super(xSpeed);
		if (Level.enemyCounter == 0)
			return;

		randomType = random.nextInt(2);
		lives = 2;
		if (randomType == 0)
			EnemyType = ENEMYTYPE.CIRCLE;
		if (randomType == 1)
			EnemyType = ENEMYTYPE.TRIANGLE;

		if ((EnemyType == ENEMYTYPE.CIRCLE || Level.triangleCounter == 0) && Level.circleCounter != 0) {
			EnemyType = ENEMYTYPE.CIRCLE;
			image = loader.load("Game/enemyCircle");
			xPosition = random.nextInt(Level.LENGTH - image.getHeight() - 300) + 300;
			yPosition = random.nextInt(Window.REALHEIGHT - image.getHeight());
			reloadTime = 15;
			bulletSpeed = 7;
		}

		if ((EnemyType == ENEMYTYPE.TRIANGLE || Level.circleCounter == 0) && Level.triangleCounter != 0) {
			EnemyType = ENEMYTYPE.TRIANGLE;
			image = loader.load("Game/enemyTriangle");
			xPosition = random.nextInt(Level.LENGTH - image.getHeight() - 300) + 300;
			yPosition = random.nextInt(Window.REALHEIGHT - image.getHeight());
			reloadTime = 15;
		}
		
		if (EnemyType == ENEMYTYPE.TRIANGLE)
			Level.triangleCounter--;
		if (EnemyType == ENEMYTYPE.CIRCLE)
			Level.circleCounter--;
		Level.enemyCounter--;

		
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());
		Level.bulletsAndEnemies.addEnemy(this);
	}

	public void update() {
		super.update();
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

	public void movement() {
		xPosition -= xSpeed;

		if (directionChangeCountdown != 0) {
			yPosition += 2 * ySign;
			directionChangeCountdown--;
			if (yPosition < 0)
				yPosition = 0;
			if (yPosition + image.getHeight() > Window.REALHEIGHT)
				yPosition = Window.REALHEIGHT - image.getHeight();
		} else {
			yPosition++;
			ySign = -1 * (random.nextInt(3) - 1);
			directionChangeCountdown = random.nextInt(100);
		}

		// collisionBox.x = playerX;
		// collisionBox.y = playerY;
		movedDistance += xSpeed;
	}

	public boolean outOfBounds() {
		return ((xPosition + image.getWidth()) < 0);
	}
}

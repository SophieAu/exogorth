package exogorth.level.characters;

import java.awt.Rectangle;
import java.util.Random;

import exogorth.Window;
import exogorth.level.GameCharacter;
import exogorth.level.flyingobject.Bullet;
import exogorth.level.flyingobject.TYPE;

public class Enemy extends GameCharacter {
	private ENEMYTYPE EnemyType;
	private Random numberGenerator = new Random();
	int ySign;
	int directionChangeCountdown;

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

	public void update() {
		super.update();
	}

	protected void shooting() {
		if (reloading())
			return;

		if (this.EnemyType == ENEMYTYPE.CIRCLE) {
			reload = numberGenerator.nextInt(10) - numberGenerator.nextInt(10);
			bulletList.addBullet(new Bullet(xPosition, yPosition + (image.getHeight() / 2), 7, TYPE.CIRCLEBULLET));
		}
		// IMPLEMENT TRIANGLE PATTERN HERE
	}

	public void movement() {
		xPosition -= xSpeed;

		if (directionChangeCountdown != 0) {
			yPosition += 2*ySign;
			directionChangeCountdown--;
			if (yPosition < 0)
				yPosition = 0;
			if (yPosition +image.getHeight() > Window.REALHEIGHT)
				yPosition = Window.REALHEIGHT- image.getHeight();
		} else {
			yPosition++;
			ySign = -1 * (numberGenerator.nextInt(3) - 1);
			directionChangeCountdown = numberGenerator.nextInt(100);
		}	
		
		// collisionBox.x = playerX;
		// collisionBox.y = playerY;
		movedDistance += xSpeed;
	}
}

package exogorth.level.characters;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import exogorth.Window;
import exogorth.level.GameCharacter;
import exogorth.level.Keyboard;
import exogorth.level.Level;
import exogorth.level.flyingobject.Bullet;
import exogorth.level.flyingobject.TYPE;

public class Player extends GameCharacter {

	public int damageGracePeriod = 3*60;
//	private boolean doubleDamage;

	public Player(int xPosition, int yPosition, int xSpeed) {
		super(xPosition, yPosition, xSpeed);
		
		movedDistance = xPosition;
		bulletSpeed = 7;
		reloadTime = 20;
		lives = 5;
		ySpeed = xSpeed;
		
		image = loader.load("Game/player");
		collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());
	}

	@Override
	protected void shooting() {
		if (!reloading() && Keyboard.pressedKey(KeyEvent.VK_SPACE)) {
			reload = 0;
			bulletList.addBullet(new Bullet(xPosition + image.getWidth(), yPosition + (image.getHeight() / 2), bulletSpeed, TYPE.PLAYER));
		}
	}

	public void movement() {
		if (Keyboard.pressedKey(KeyEvent.VK_LEFT) || Keyboard.pressedKey(KeyEvent.VK_A)) {
			xPosition -= this.xSpeed;
			if (xPosition < 0)
				xPosition = 0;
		}

		if ((Keyboard.pressedKey(KeyEvent.VK_RIGHT) || Keyboard.pressedKey(KeyEvent.VK_D)) & xPosition <= ((Window.WIDTH * 2) / 3))
			xPosition += xSpeed;

		if (Keyboard.pressedKey(KeyEvent.VK_UP) || Keyboard.pressedKey(KeyEvent.VK_W)) {
			yPosition -= ySpeed;
			if (yPosition < 0)
				yPosition = 0;
		}

		if (Keyboard.pressedKey(KeyEvent.VK_DOWN) || Keyboard.pressedKey(KeyEvent.VK_S)) {
			yPosition += ySpeed;
			if (yPosition + collisionBox.height > Window.REALHEIGHT)
				yPosition = Window.REALHEIGHT - collisionBox.height;
		}

		collisionBox.x = xPosition;
		collisionBox.y = yPosition;
		movedDistance += xSpeed;

		// ONLY HERE FOR TESTING PURPOSES
		if (movedDistance % 100 == 0)
			System.out.println(movedDistance);
	}

	public void damage() {
		lives--;
		if(lives == 0)
			death();
		System.out.println("Lives left: " + lives);
	}

	@Override
	public void update(){
		super.update();
		if(damageGracePeriod < 180)
			damageGracePeriod++;
	}
	
	private void death() {
		System.out.println("YOU JUST DIED");
		
	}

	public void hit() {
		if(Level.player.damageGracePeriod < 180)
			return;
		Level.player.damage();
		Level.player.damageGracePeriod = 0;
	}
}
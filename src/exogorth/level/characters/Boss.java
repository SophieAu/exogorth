package exogorth.level.characters;

import exogorth.level.GameCharacter;

public class Boss extends GameCharacter {

	public Boss(int xSpeed) {
		super(xSpeed);
		lives = 20;
	}

	
	//set startposition
	
	//if boss is scrolled in just far anough, don't let him move any further
	
	//ySpeed pattern:
//	if (directionChangeCountdown != 0) {
//		yPosition += 2 * ySign;
//		directionChangeCountdown--;
//		if (yPosition < 0 || yPosition + image.getHeight() > Window.REALHEIGHT)
//			ySign *= -1;
//	} else {
//		yPosition++;
//		ySign = -1 * (random.nextInt(3) - 1);
//		directionChangeCountdown = random.nextInt(100);
//	}
//
//	collisionBox.x = xPosition;
//	collisionBox.y = yPosition;
	
	
	//at first do everything for if BOSSONE
	
	
}

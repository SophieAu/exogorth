package exogorth.level;

import java.awt.Graphics;
import java.util.ArrayList;

import exogorth.level.flyingobject.Walls;

public class WallController {
	ArrayList<Walls> wallArray = new ArrayList<>(37);
	static Walls currentFirst;
	static Walls currentSecond;

	public WallController(int playerXSpeed) {
		//36 because (30000-800)/800=36.5 and I won't use half walls (too much of a bother)
		for (int i = 0; i < 36; i++)
			wallArray.add(new Walls(playerXSpeed, 800));
		
		currentFirst = wallArray.get(0);
		currentSecond = wallArray.get(1);
	}

	public void update() {
		if (!wallArray.isEmpty()) {
			currentFirst.update();

			if (currentFirst.xPosition <= 0 && wallArray.size() >= 2)
				currentSecond.update();

			if (currentFirst.xPosition + currentFirst.image.getWidth() <= 0 && wallArray.size() > 2){
				wallArray.remove(currentFirst);
				currentFirst = currentSecond;
				currentSecond = wallArray.get(1);
			}
		}
	}

	public void render(Graphics g) {
		if (!wallArray.isEmpty()) {
			currentFirst.render(g);

			if (currentFirst.xPosition <= 0 && wallArray.size() >= 2)
				currentSecond.render(g);
		}
	}

}

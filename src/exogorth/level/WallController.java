package exogorth.level;

import java.awt.Graphics;
import java.util.ArrayList;

import exogorth.level.flyingobject.Walls;

public class WallController {
	ArrayList<Walls> wallArray = new ArrayList<>(36);
	static Walls currentFirst;
	static Walls currentSecond;
	int wallArraySize;

	public WallController(int playerXSpeed) {
		for (int i = 0; i < Level.LENGTH/Walls.width; i++)
			wallArray.add(new Walls(playerXSpeed, 800));
		
		wallArraySize = wallArray.size();
		currentFirst = wallArray.get(0);
		currentSecond = wallArray.get(1);
	}

	public synchronized void update() {
		if (wallArraySize > 0) {
			currentFirst.update();

			if (currentFirst.xPosition <= 0 && wallArraySize >= 2)
				currentSecond.update();

			if (currentFirst.xPosition + Walls.width <= 0 && wallArraySize > 2){
				wallArray.remove(currentFirst);
				currentFirst = currentSecond;
				currentSecond = wallArray.get(1);
				wallArraySize--;
			}
		}
	}

	public synchronized void render(Graphics g) {
		if (wallArraySize > 0) {
			currentFirst.render(g);

			if (currentFirst.xPosition <= 0 && wallArraySize >= 2)
				currentSecond.render(g);
		}
	}

}

package exogorth.level.characters;

import java.awt.Graphics;
import java.util.ArrayList;

import exogorth.level.flyingobject.Walls;

public class WallController {
	ArrayList<Walls> wallArray = new ArrayList<>(37);

	public WallController(int playerXSpeed) {
		for (int i = 0; i < 37; i++)
			wallArray.add(new Walls(playerXSpeed, 800));
	}

	public void update() {
		Walls toUpdate = wallArray.get(0);

		toUpdate.update();

		if (toUpdate.xPosition <= 0)
			wallArray.get(1).update();

		if (toUpdate.xPosition + toUpdate.image.getWidth() <= 0)
			wallArray.remove(toUpdate);
	}

	public void render(Graphics g) {
		Walls toUpdate = wallArray.get(0);

		toUpdate.render(g);

		if (toUpdate.xPosition <= 0)
			wallArray.get(1).render(g);
	}

}

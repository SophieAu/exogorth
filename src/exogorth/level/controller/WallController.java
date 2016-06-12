package exogorth.level.controller;

import java.awt.Graphics;
import java.util.ArrayList;

import exogorth.Settings;
import exogorth.level.model.Wall;

public class WallController {
	ArrayList<Wall> wallArray = new ArrayList<>(36);
	public static Wall currentFirst;
	public static Wall currentSecond;
	int wallArraySize;

	public WallController(int playerXSpeed) {
		for (int i = 0; i < Settings.LENGTH/Wall.width; i++)
			wallArray.add(new Wall(playerXSpeed, 800));
		
		wallArraySize = wallArray.size();
		currentFirst = wallArray.get(0);
		currentSecond = wallArray.get(1);
	}

	public synchronized void update() {
		if (wallArraySize > 0) {
			currentFirst.update();

			if (currentFirst.xPosition <= 0 && wallArraySize >= 2)
				currentSecond.update();

			if (currentFirst.xPosition + Wall.width <= 0 && wallArraySize > 2){
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

			if (currentFirst.xPosition <= 0 && wallArraySize > 1)
				currentSecond.render(g);
		}
	}

}



//package exogorth.level;
//
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//
//import exogorth.ImageLoader;
//
//public class LevelBackground {
//	private int xPosition = 0;
//	private int scrollingSpeed;
//	private int wallCounter;
//	private ImageLoader loader = ImageLoader.getInstance();
//	private BufferedImage image;
//
//	public LevelBackground(int scrollingSpeed) {
//		this.scrollingSpeed = scrollingSpeed;
//		image = loader.load("background");
//		wallCounter = 2;
//	}
//
//	public synchronized void update() {
//		xPosition -= scrollingSpeed;
//		if (xPosition < -image.getWidth()){
//			xPosition = 0;
//			topOrBottom1 = topOrBottom2
//			image1 = image2;
//			topOrBottom2 = random.nextInt(2) == 0 ? 0 : Window.HEIGHT - image.getHeight();
//			image2 = topOrBottom==0 ? imageTop : imageBottom
//			wallCounter++;
//		}
//	}
//
//	public synchronized void render(Graphics g) {
//		while(wallCounter <= Level.length / image.getWidth()){
//			g.drawImage(image1, xPosition, topOrBottom1, null);
//			g.drawImage(image2, xPosition + image.getWidth(), topOrBottom2, null);
//		}
//		
//	}
//}



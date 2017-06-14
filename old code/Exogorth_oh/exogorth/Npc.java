package exogorth;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Npc {
	Random rand = new Random();
	// Art des Npcs 1=Dreieck
	int typ;
	// Leben
	int life;
	// boundingbox
	private Rectangle bounding;
	int npcY, npcX, speed;
	private BufferedImage npcPicture;
	// Kugeln und co
	private int shoot = 0;
	private int shootingTimer = 60;
	private int reload = 0;
	private int speedBullet = -7;
	private ArrayList<Bullets> bullet;
	private ArrayList<Npc> npc;
	// zufallsbewegung
	private int direction = 0;
	private int hor = 0;

	public Npc(int typ, int startX, int startY, ArrayList<Bullets> bullet,
			ArrayList<Npc> npc) throws IOException {
		this.typ = typ;
		this.npc = npc;
		this.bullet = bullet;
		switch (this.typ) {
		// Normaler Gegner: Dreieck
		case 1:
			npcPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Images/enemy_triangle.png"));
			life = 2;
			speed = 3;
			break;
		// Normaler Gegner: Kreis
		case 2:
			npcPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Images/enemy_circle.png"));
			life = 2;
			speed = 3;
			shootingTimer = 100;
			reload = 90;
			break;
		// 1. Boss
		case 3:
			npcPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Images/1_Boss.png"));
			life = 20;
			speed = 2;
			break;
		// 2. Boss
		case 4:
			npcPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Images/2_Boss.png"));
			life = 30;
			speed = 2;
			break;
		// Wurm
		case 5:
			npcPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Images/exogorth_long.png"));
			life = 10000;
			speed = 0;
			break;
		default:
			break;
		}
		bounding = new Rectangle(startX, startY, npcPicture.getWidth(),
				npcPicture.getHeight());
		npcY = startY;
		npcX = startX;
	}

	public void update() throws IOException {
		npcX -= speed;
		// prüft ob npc objekt berührt
		for (int i = 0; i < bullet.size(); i++) {
			Bullets tester = bullet.get(i);
			if (this.bounding.intersects(tester.getBounding())) {
				switch (tester.getType()) {
				case 0:
					bullet.remove(i);
					--i;
					--life;
					break;
				default:
					break;

				}
			}
		}
		// schießen
			shoot = rand.nextInt(150);
		if (reload < shootingTimer) {
			reload++;
		}
		// es ist gewollt das Gegner feuern bevor sie zu sehen sind
		if(typ!=2){
			if (shoot < 20 && reload >= shootingTimer && npcX < 700) {
				bullet.add(new Bullets(speedBullet+1, npcY
						+ (npcPicture.getHeight() / 2) - 3, npcX, 4, bullet));
				reload = 0;
			}
		}else{
			if (shoot < 20 && reload >= shootingTimer && npcX < 700) {
					bullet.add(new Bullets(speedBullet+1, npcY
							+ (npcPicture.getHeight() / 2) - 3, npcX-163, 18, bullet));
				
				reload = 0;
			}
		}
		// bewegung
		if (direction <= 0) {
			int temp1 = rand.nextInt(25) + 15;
			direction = temp1;
			int temp2 = rand.nextInt(2);
			hor = temp2;

		}
		//hor hoch oder runter
		if (hor == 0) {
			if(!(npcY>(500-bounding.y)))npcY += speed - 2;
			direction--;

		} else {
			if(!(npcY<100))npcY -= speed - 2;
			direction--;
		}
		bounding.x = npcX;
		bounding.y = npcY;
	}

	public Rectangle getBounding() {
		return bounding;
	}

	public void setBounding(int x, int y) {
		bounding.x = x;
		bounding.y = y;
	}

	public BufferedImage getPicture() {
		return npcPicture;
	}
	
	public int getX() {
		return npcX;
	}
	
	public void setSpeed(int i) {
		speed = i;
	}

}

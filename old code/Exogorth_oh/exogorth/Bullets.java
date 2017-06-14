package exogorth;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bullets {
	private Rectangle bounding;
	// Kollisionsbox
	private float bulletY, bulletX;
	// Position der Kugel
	int speed;
	// Geschwindigkeit der Kugel
	private ArrayList<Bullets> bullet;
	private int typ;
	/*
	 * 0: Spielergeschoss 
	 * 1: Regenbogen
	 * 2: Power-Up Leben
	 * 3: Power-Up Doppelter Schaden
	 * 4: Gegnerkugeln Levelbegrenzungen
	 * 5: Asteroiden
	 * 6: Wand(oben)
	 * 7: Wand(unten)
	 */
	private BufferedImage bulletPicture;

	public Bullets(int speed, int y, int x, int typ, ArrayList<Bullets> bullet)
			throws IOException {
		this.typ = typ;
		this.bullet = bullet;
		// für Spielergeschoss

		switch (typ) {
		case 0:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/geschoss.jpg"));
			break;
		case 1:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/Regenbogen.png"));
			break;
		case 2:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/Heal.png"));
			break;
		case 3:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/Dmg.png"));
			break;
		case 4:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/enemyBullet.jpg"));
			break;
		case 5:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/Asteroid.png"));
			break;
		case 6:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l1_rockBottom_short.png"));
			break;
		case 7:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l1_rockBottom_medium.png"));
			break;
		case 8:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l1_rockBottom_long.png"));
			break;
		case 9:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l1_rockTop_short.png"));
			break;
		case 10:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l1_rockTop_medium.png"));
			break;
		case 11:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l1_rockTop_long.png"));
			break;
		case 12:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/exogorth_long.png"));
			break;
		case 13:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/Level1_Font.png"));
			break;
		case 14:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/Level2_Font.png"));
			break;
			
		case 15:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l2_rockBottom_start.png"));
			break;
		case 16:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l2_rockBottom_middle.png"));
			break;
		case 17:
			bulletPicture = ImageIO.read(getClass().getClassLoader()
					.getResourceAsStream("images/l2_rockBottom_end.png"));
			break;	
		
			case 18:
				bulletPicture = ImageIO.read(getClass().getClassLoader()
						.getResourceAsStream("images/Laser.png"));
				break;
		}
		bounding = new Rectangle(x, y, bulletPicture.getWidth(),
				bulletPicture.getHeight());
		this.speed = speed;
		bulletY = y;
		bulletX = x;
	}

	public void update() {
		bulletX += speed;
		bounding.x = (int) bulletX;
		// Kugeln auflösen, wenn nicht mehr sichtbar
		if (speed < 0 && bulletX < -getPicture().getWidth())
			bullet.remove(this);
		if (speed > 0 && bulletX > 800 + getPicture().getWidth())
			bullet.remove(this);

	}

	public void setbound(Rectangle r) {
		this.bounding = r;
	}

	public float getBulletY() {
		return bulletY;
	}

	public void setBulletY(float bulletY) {
		this.bulletY = bulletY;
	}

	public float getBulletX() {
		return bulletX;
	}

	public void setBulletX(float bulletX) {
		this.bulletX = bulletX;
	}

	public int getSpeed() {
		return speed;
	}

	public BufferedImage getBulletPicture() {
		return bulletPicture;
	}

	public BufferedImage getPicture() {
		return bulletPicture;
	}

	public Rectangle getBounding() {
		return bounding;
	}

	public int getType() {
		return typ;
	}
	
	public void setSpeed(int i) {
		speed = i;
	}

}

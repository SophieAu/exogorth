package spiel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bullets {
	// Kollisionsbox
	private Rectangle bounding;
	// Position der Kugel
	private int bulletY, bulletX;
	// Geschwindigkeit der Kugel
	int bulletSpeed;
	//TODO ist das 
	private ArrayList<Bullets> bullet;
	private int typ;
	/*
	 * 0 : Spielergeschoss 1 : Regenbogen 2 : Power-Up Leben 3 : Power-Up
	 * Doppelter Schaden 4/5 : Levelbegrenzungen // noch ohne Bild
	 */
	private BufferedImage bulletPicture;

	public Bullets(int speed, int y, int x, int typ, ArrayList<Bullets> bullet) throws IOException {
		this.typ = typ;
		this.bullet = bullet;

		switch (typ) {
		case 0:
			bulletPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilddateien/geschoss.jpg"));
			break;
		case 1:
			bulletPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilddateien/Regenbogen.png"));
			break;
		case 2:
			bulletPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilddateien/Heal.png"));
			break;
		case 3:
			bulletPicture = ImageIO.read(getClass().getClassLoader().getResourceAsStream("bilddateien/Dmg.png"));
			break;
		case 4:
			bulletPicture = ImageIO
					.read(getClass().getClassLoader().getResourceAsStream("bilddateien/enemyBullet.jpg"));
			break;

		}
		bounding = new Rectangle(x, y, bulletPicture.getWidth(), bulletPicture.getHeight());
		this.bulletSpeed = speed;
		bulletY = y;
		bulletX = x;
	}

	public void update() {
		bulletX += bulletSpeed;
		bounding.x = bulletX;
		// Kugeln auflösen, wenn nicht mehr sichtbar
		if (bulletSpeed < 0 && bulletX < -getPicture().getWidth())
			bullet.remove(this);
		if (bulletSpeed > 0 && bulletX > 800 + getPicture().getWidth())
			bullet.remove(this);

	}

	public void setbound(Rectangle r) {
		this.bounding = r;
	}

	public float getBulletY() {
		return bulletY;
	}

	public void setBulletY(int bulletY) {
		this.bulletY = bulletY;
	}

	public float getBulletX() {
		return bulletX;
	}

	public void setBulletX(int bulletX) {
		this.bulletX = bulletX;
	}

	public int getBulletSpeed() {
		return bulletSpeed;
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

}

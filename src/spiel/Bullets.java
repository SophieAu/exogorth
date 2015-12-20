package spiel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bullets {
	private Rectangle bounding; // Collision Box
	private float bulletY, bulletX; // Position of the Bullet
	int bulletSpeed;
	private ArrayList<Bullets> bullet;
	private int type;
	private BufferedImage bulletPicture;

	public Bullets(int bulletSpeed, int y, int x, int type, ArrayList<Bullets> bullet) throws IOException {
		this.type = type;
		this.bullet = bullet;

		switch (type) {
		case 0:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/PlayerBullet.jpg"));
			break;
		case 1:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/Regenbogen.png"));
			break;
		case 2:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/HealPowerUp.png"));
			break;
		case 3:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/DamagePowerUp.png"));
			break;
		case 4:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/enemyBullet.jpg"));
			break;
		case 5:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/Asteroid.png"));
			break;
		case 6:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockBottom_short.png"));
			break;
		case 7:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockBottom_medium.png"));
			break;
		case 8:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockBottom_long.png"));
			break;
		case 9:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("Images/l1_rockTop_short.png"));
			break;
		case 10:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockTop_medium.png"));
			break;
		case 11:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockTop_long.png"));
			break;
		case 12:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/exogorth_long.png"));
			break;
		case 13:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/Level1_Font.png"));
			break;
		case 14:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/Level2_Font.png"));
			break;
		case 15:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l2_rockBottom_start.png"));
			break;
		case 16:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l2_rockBottom_middle.png"));
			break;
		case 17:
			bulletPicture = ImageIO.read(getClass().getResourceAsStream("/Images/l2_rockBottom_end.png"));
			break;
		default:
			break;
		}
		// TODO: AB HIER REFACTORING
		bounding = new Rectangle(x, y, bulletPicture.getWidth(), bulletPicture.getHeight());
		this.bulletSpeed = bulletSpeed;
		bulletY = y;
		bulletX = x;
	}

	public void update() {
		bulletX += bulletSpeed;
		bounding.x = (int) bulletX;
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

	public void setBulletY(float bulletY) {
		this.bulletY = bulletY;
	}

	public float getBulletX() {
		return bulletX;
	}

	public void setBulletX(float bulletX) {
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
		return type;
	}

	public void setSpeed(int i) {
		bulletSpeed = i;
	}

}

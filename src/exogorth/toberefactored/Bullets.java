package exogorth.toberefactored;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bullets {
	private Rectangle collisionBox;
	@SuppressWarnings("unused")
	private int yPosition, xPosition;
	private int speed;
	private ArrayList<Bullets> bullet;
	// private string type;
	private int type;
	private BufferedImage image;

	// TODO: consider using String type and then:
	// image = ImageIO.read(getClass().getResourceAsStream("/Images/" + type +
	// ".jpg"));
	//
	// should be no problem at all, only errors in Npc.java und Player.java,
	// that can be resolved easily

	public Bullets(int speed, int yPosition, int xPosition, int type, ArrayList<Bullets> bullet) {
		this.type = type;
		this.bullet = bullet;
		this.yPosition = yPosition;
		this.xPosition = xPosition;
		this.speed = speed;
		try {
			switch (type) {
			case 0:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/PlayerBullet.jpg"));
				break;
			case 1:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/Regenbogen.png"));
				break;
			case 2:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/HealPowerUp.png"));
				break;
			case 3:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/DamagePowerUp.png"));
				break;
			case 4:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/enemyBullet.jpg"));
				break;
			case 5:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/Asteroid.png"));
				break;
			case 6:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockBottom_short.png"));
				break;
			case 7:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockBottom_medium.png"));
				break;
			case 8:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockBottom_long.png"));
				break;
			case 9:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockTop_short.png"));
				break;
			case 10:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockTop_medium.png"));
				break;
			case 11:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l1_rockTop_long.png"));
				break;
			case 12:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/exogorth_long.png"));
				break;
			case 13:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/Level1_Font.png"));
				break;
			case 14:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/Level2_Font.png"));
				break;
			case 15:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l2_rockBottom_start.png"));
				break;
			case 16:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l2_rockBottom_middle.png"));
				break;
			case 17:
				image = ImageIO.read(getClass().getResourceAsStream("/Images/l2_rockBottom_end.png"));
				break;
			default:
				break;
			}
			collisionBox = new Rectangle(xPosition, yPosition, image.getWidth(), image.getHeight());

		} catch (IOException | IllegalArgumentException e) {
			System.out.println("Bullet image not found.");
			e.printStackTrace();
		}
	}

	/**
	 * The bullet is moved "speed" pixels with every call of this method. If the bullet moves out of
	 * frame, it gets deleted.
	 */
	public void update() {
		xPosition += speed;
		collisionBox.x = xPosition;

		if (speed < 0 && xPosition < -image.getWidth())
			bullet.remove(this);
		if (speed > 0 && xPosition > 800 + image.getWidth())
			bullet.remove(this);
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getType() {
		return type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}

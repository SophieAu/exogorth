package spiel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Stones extends Objekte{
	private Rectangle bounding;
	private BufferedImage picture;
	private ArrayList<? super Objekte> object;
	private Player player;
	
	public Stones(int x, int y,ArrayList<? super Objekte> objekte, Player player){
		super(x,y,objekte, player);
		this.player=player;
		this.object=objekte;
		try {
			//Waagerechter block
				picture = ImageIO.read(getClass().getClassLoader()
						.getResourceAsStream("bilddateien/balken.png"));
				bounding = new Rectangle(x, y, picture.getWidth(),
						picture.getHeight());		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getPicture() {
		return picture;
	}

	public Rectangle getBounding() {
			return bounding;
	}
	public void update(){
		super.update();
		bounding.x = getXpos();
		bounding.y = getYpos();
	}
	
}

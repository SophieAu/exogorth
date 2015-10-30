package spiel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public abstract class Objekte {

	private static int IDindex=0;
	private int ID = 0;
	private int Xpos=0;
	private int Ypos=0;
	BufferedImage picture =null;
	private Rectangle bounding=null;
	private Player spieler;
	private ArrayList<? super Objekte> objekte;
	
	public void setbound(Rectangle r){
		this.bounding=r;
	}
	public Objekte(int x, int y,ArrayList<? super Objekte> objekte, Player spieler) {
		this.objekte=objekte;
		IDindex++;
		ID=IDindex;
		this.setYpos(y);
		this.setXpos(x);
		this.spieler=spieler;
	
		
	}
	public int getID(){
		return ID;
	}

	public int getX(){
		return getXpos();
	}
	
	public int getY(){
		return getYpos();
	}

	public BufferedImage getPicture(){
		return picture;
	}
		

	public Rectangle getBounding(){
		return bounding;
	}
	@Override 
	public boolean equals(Object o){
		Objekte test=null;
		if(o instanceof Stones){
			test= (Objekte) o;
		}
		else return false;
		 return test.getID()==this.ID;
			
	}
	 
	@Override 
	public int hashCode() {
		return java.util.Objects.hash(ID);
	}
	public int getXpos() {
		return Xpos;
	}
	public void setXpos(int xpos) {
		Xpos = xpos;
	}
	public int getYpos() {
		return Ypos;
	}
	public void setYpos(int ypos) {
		Ypos = ypos;
	}
	public void update()
	{
		if(this.getXpos()<-999)
			objekte.remove(this);
		int ges =(int) spieler.getSpeed();
			for (int i = 0; i < ges; i++) {
				this.setXpos(this.getXpos()-1);
					
		}
	}
	
}
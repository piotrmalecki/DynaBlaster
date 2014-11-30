package pacman;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Player  {
	
	private Image pacman;
    public int locationX,locationY;

	public Player(){
		ImageIcon img = new ImageIcon("C://Users//Marek//Desktop//workspace//pacman//Images//pacman.png");
		pacman =img.getImage(); 
		locationX = 2;
		locationY=2;
	}
	public Image getPacman(){
		return pacman;
	}
	public int getlocationX(){ return locationX;}
	public int getlocationY(){ return locationY;}
	

	public void  move(int dx, int dy){
		
		locationX +=dx;
		locationY += dy;
	}
}

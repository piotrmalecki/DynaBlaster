package pacman;

import java.awt.Image;


import javax.swing.ImageIcon;

public class Ghost  {
	private Image ghost;
    public int locationX,locationY;

	public Ghost(){
		ImageIcon img = new ImageIcon("C://Users//Marek//Desktop//workspace//pacman//Images//ghost.png");
		ghost =img.getImage(); 
		locationX = 17;
		locationY=1;
		
	}
	public Image getGhost(){
		return ghost;
	}
	public int getlocationX(){ return locationX;}
	public int getlocationY(){ return locationY;}
	
	
}

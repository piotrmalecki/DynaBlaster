package db;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa odpowiadaj¹ca za stworzenie i wskazywanie co ma byæ robione
 * podczas przesuwania gracza
 * @author Piotr Ma³ecki
 *
 */
public class Player {
	//private BufferedImage bomberman;
    public int LocationX,LocationY;
    private int dx,dy;
    private BufferedImage[][] kierunek;

	public Player(){
		LocationX = 1;
		LocationY = 1 ;
		kierunek = new BufferedImage[2][2];
		kierunek[0][0]=loadImage("00");
		kierunek[0][1]=loadImage("01");
		kierunek[1][0]=loadImage("10");
		kierunek[1][1]=loadImage("11");
		this.dx=1;
		this.dy=0;
		
	}
	
	/**This method creates an image from a file 
	 * @param {@link String} name of a file
	 * @returns  {@link BufferedImage} img , or null if the path was invalid.
	 * */
	private BufferedImage loadImage(String name) {
        String imgFileName = "Images/Player/"+name+".png";
        BufferedImage img = null;
        try {
            img =  ImageIO.read(new File(imgFileName));
        } catch (IOException e) {
        	System.out.println("Nie ma obrazka");}
        return img;
    }
	
	/**The image of Bomberman*/
	public BufferedImage GetBomberman(){
		return kierunek[this.dx][this.dy];
	}
	public int GetLocationX(){ return LocationX;}
	public int GetLocationY(){ return LocationY;}
	

	public void  BombermanMove(int dx, int dy){
		LocationX +=dx;
		LocationY +=dy;
		if (dx==0 && dy==-1)	{
			this.dx=0;
			this.dy=1;	
		}
		if (dx==0 && dy==1)	{
			this.dx=1;
			this.dy=0;	
		}
		if (dx==-1 && dy==0)	{
			this.dx=0;
			this.dy=0;	
		}
		if (dx==1 && dy==0)	{
			this.dx=1;
			this.dy=1;	
		}
	}
}



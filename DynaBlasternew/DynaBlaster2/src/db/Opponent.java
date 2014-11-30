package db;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/***
 * Klasa realizuj�ca istnienie i zachowanie przeciwnik�w
 * Uruchamiania z klasy {@link GameField}. Przeciwnik�w jest trzech - sta�a liczba 
 * @author Robert Zag�rski
 */
public class Opponent {
	
	static byte counter=0;
	public int LocationX,LocationY;
    private int dx,dy;
    private BufferedImage[][] kierunek;
    byte[][] board;
    static enum direction {UP,DOWN,RIGHT,LEFT};
    boolean EXIST=true;
    
    public Opponent (int x, int y, byte[][] boardGF){
    	counter++;
    	System.out.println("Utworzono przeciwnika nr "+counter);
		LocationX = x;
		LocationY = y;
		board = new byte[boardGF.length][boardGF.length];
		board = boardGF;
		kierunek = new BufferedImage[2][2];
		kierunek[0][0]=loadImage(counter,"00");
		kierunek[0][1]=loadImage(counter,"01");
		kierunek[1][0]=loadImage(counter,"10");
		kierunek[1][1]=loadImage(counter,"11");
		this.dx=1;
		this.dy=0;
		
	}
    
    /**This method creates an image from a file 
	 * @param {@link String} name of a file
	 * @returns  {@link BufferedImage} img , or null if the path was invalid.
	 * */
    private BufferedImage loadImage(byte counter, String name) {
        String imgFileName = "Images/Opponent/Opponent"+counter+"/"+name+".png";
        BufferedImage img = null;
        try {
            img =  ImageIO.read(new File(imgFileName));
        } catch (IOException e) {
        	System.out.println("Nie ma obrazka");}
        return img;
    }
    
    /**The image of Opponent*/
	public BufferedImage GetBomberman(){
		return kierunek[this.dx][this.dy];
	}
	
	/**
	 * Zwracanie pozycji przeciwnika w celu jego narysowania 
	 * w odpowiednim miejscu na planszy
	 * @return
	 */
	public int GetLocationX(){ return LocationX;}
	public int GetLocationY(){ return LocationY;}
	
	
public class OpponentMoving extends Thread	{
	int kier;
	direction krk;
	
	//@Override
	/**G��wna metoda w�tku przeciwnika, obs�uguje poruszanie si� w dowolnym kierunku
	 */
	public void run(){
		try	{
			Thread.sleep(1000);
		}	catch	(InterruptedException e)	{}; 
		while(EXIST)	{
			System.out.println("LocationX " + LocationX + " LocationY " + LocationY);
			System.out.println("Przeciwnik "+Thread.currentThread().getName()+" si� rusza");
			//Losowe wybieranie kierunku poruszania si�
			kier = (int)Math.floor( ( Math.random() ) * 4 );
			System.out.println("Przeciwnik "+counter+" -> kier = " + kier);
			//Obs�u�enie czy wcze�niej wybrana liczba mo�e by� kierunkiem, kt�rym porusza si� przeciwnik
			switch (kier)	{
				//Obs�u�enie poruszania do g�ry
				case	0	:	{
					if ( CheckForWall(0,-1) == true)	{
						//Sprawdzenie warunku czy miejsce, do kt�rego chce poruszy� si� przeciwnik
						//jest pod�og�
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce i�� w g�r�");
						krk=direction.UP;
						//Obs�u�enie zmiany pozycji i odpowiedniego obrazka dla Bombermana
						OpponentMove();
						//Odczekanie chwili czasowej
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
				//Obs�u�enie poruszania w d�
				case	1	:	{
					if ( CheckForWall(0,1) == true)	{
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce i�� w d�");
						krk=direction.DOWN;
						OpponentMove();
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
				//Obs�u�enie poruszania w lewo
				case	2	:	{
					if ( CheckForWall(-1,0) == true)	{
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce i�� w lewo");
						krk=direction.LEFT;
						OpponentMove();
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
				//Obs�u�enie poruszania w prawo
				case	3	:	{
					if ( CheckForWall(1,0) == true)	{
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce i�� w prawo");
						krk=direction.RIGHT;
						OpponentMove();
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
				default	:	{
					if ( CheckForWall(0,1) == true)	{
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce i�� w d�");
						krk=direction.DOWN;
						OpponentMove();
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
			}		
		}
	}
	
	/**
	 * Metoda w�tku obs�uguj�cego przeciwnika
	 * Otrzymuje aktualn� pozycje jako parametry i na tej podstawie wyznacza liczby, kt�re maj�
	 * by� u�yte do okre�lenia, jak ma wygl�da� przeciwnik
	 */
	private void  OpponentMove(){
		System.out.println("Przeciwnik "+Thread.currentThread().getName()+" "+krk);
		switch (krk)	{
			case	UP	:	{
				System.out.println("Przeciwnik "+Thread.currentThread().getName()+" idzie w g�r�");
				dx = 0;
				dy =-1;
				LocationX += dx;
				LocationY += dy;
				dx = 0;
				dy = 1;
				return;
			}
			case	DOWN	:	{
				System.out.println("Przeciwnik "+Thread.currentThread().getName()+" idzie w d�");
				dx = 0;
				dy = 1;
				LocationX += dx;
				LocationY += dy;
				dx = 1;
				dy = 0;
				return;
			}
			case	LEFT	:	{
				System.out.println("Przeciwnik "+Thread.currentThread().getName()+" idzie w lewo");
				dx =-1;
				dy = 0;
				LocationX += dx;
				LocationY += dy;
				dx = 0;
				dy = 0;
				return;
			}
			case	RIGHT	:	{
				System.out.println("Przeciwnik "+Thread.currentThread().getName()+" idzie w prawo");
				dx = 1;
				dy = 0;
				LocationX += dx;
				LocationY += dy;
				dx = 1;
				dy = 1;
				return;
			}
		}
	}
	
	/**
	 * Funkcja sprawdzaj�ce czy w danym kierunku mo�e przeciwnik i��
	 * @param x parametr m�wi�cy o zmianie w kierunku horyzontalnym
	 * @param y parametr m�wi�cy o zmianie w kierunku vertykalnym
	 * @return warto�c typu {@link Boolean} czy mo�e w daym kierunku i��
	 */
	private boolean CheckForWall(int x,int y)	{
		if ( board[ LocationX + x ][ LocationY + y ] == 0)	{
			System.out.println("LocationX + x = " + (LocationX + x) + "; LocationY + y = " + (LocationY + y) );
			return true;
		}
		else return false;
	}
}

}
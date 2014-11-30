package db;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/***
 * Klasa realizuj¹ca istnienie i zachowanie przeciwników
 * Uruchamiania z klasy {@link GameField}. Przeciwników jest trzech - sta³a liczba 
 * @author Robert Zagórski
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
	/**G³ówna metoda w¹tku przeciwnika, obs³uguje poruszanie siê w dowolnym kierunku
	 */
	public void run(){
		try	{
			Thread.sleep(1000);
		}	catch	(InterruptedException e)	{}; 
		while(EXIST)	{
			System.out.println("LocationX " + LocationX + " LocationY " + LocationY);
			System.out.println("Przeciwnik "+Thread.currentThread().getName()+" siê rusza");
			//Losowe wybieranie kierunku poruszania siê
			kier = (int)Math.floor( ( Math.random() ) * 4 );
			System.out.println("Przeciwnik "+counter+" -> kier = " + kier);
			//Obs³u¿enie czy wczeœniej wybrana liczba mo¿e byæ kierunkiem, którym porusza siê przeciwnik
			switch (kier)	{
				//Obs³u¿enie poruszania do góry
				case	0	:	{
					if ( CheckForWall(0,-1) == true)	{
						//Sprawdzenie warunku czy miejsce, do którego chce poruszyæ siê przeciwnik
						//jest pod³og¹
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce iœæ w górê");
						krk=direction.UP;
						//Obs³u¿enie zmiany pozycji i odpowiedniego obrazka dla Bombermana
						OpponentMove();
						//Odczekanie chwili czasowej
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
				//Obs³u¿enie poruszania w dó³
				case	1	:	{
					if ( CheckForWall(0,1) == true)	{
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce iœæ w dó³");
						krk=direction.DOWN;
						OpponentMove();
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
				//Obs³u¿enie poruszania w lewo
				case	2	:	{
					if ( CheckForWall(-1,0) == true)	{
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce iœæ w lewo");
						krk=direction.LEFT;
						OpponentMove();
						try	{
							Thread.sleep((int)Math.floor( ( Math.random() ) * 100 ) + 400);
						}	catch	(InterruptedException e)	{}; 
					}
					continue;
				}
				//Obs³u¿enie poruszania w prawo
				case	3	:	{
					if ( CheckForWall(1,0) == true)	{
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce iœæ w prawo");
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
						System.out.println("Przeciwnik "+Thread.currentThread().getName()+" chce iœæ w dó³");
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
	 * Metoda w¹tku obs³uguj¹cego przeciwnika
	 * Otrzymuje aktualn¹ pozycje jako parametry i na tej podstawie wyznacza liczby, które maj¹
	 * byæ u¿yte do okreœlenia, jak ma wygl¹daæ przeciwnik
	 */
	private void  OpponentMove(){
		System.out.println("Przeciwnik "+Thread.currentThread().getName()+" "+krk);
		switch (krk)	{
			case	UP	:	{
				System.out.println("Przeciwnik "+Thread.currentThread().getName()+" idzie w górê");
				dx = 0;
				dy =-1;
				LocationX += dx;
				LocationY += dy;
				dx = 0;
				dy = 1;
				return;
			}
			case	DOWN	:	{
				System.out.println("Przeciwnik "+Thread.currentThread().getName()+" idzie w dó³");
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
	 * Funkcja sprawdzaj¹ce czy w danym kierunku mo¿e przeciwnik iœæ
	 * @param x parametr mówi¹cy o zmianie w kierunku horyzontalnym
	 * @param y parametr mówi¹cy o zmianie w kierunku vertykalnym
	 * @return wartoœc typu {@link Boolean} czy mo¿e w daym kierunku iœæ
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
package db;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Klasa modeluj¹ca zachowanie bomby ustawianej zarówno 
 * przez gracza jak i przeciwnika 
 * @author Piotr Ma³ecki
 */
public class Bomb {
	BufferedImage Images[]= null;
	protected byte[][] board;
	String Board_NameOfFile;
    public int LocationX,LocationY;
	short Imagepointer=0;
	byte LevelCapacity;
	/**W¹tek obs³uguj¹cy animacjê bomby po jej postawieniu*/
	Thread BAnm;
    /****
     *Konstruktor klasy Bomb, pobiera parametry planszy oraz 
     *pozycjê, w której bomba zosta³a ustawiona. Dodatkowo	
     *tworzy w¹tek zwi¹zany z bomb¹ zapisany w klasie {@link BombAnimation}
     * @param Board_Layout Uk³ad aktualnie wyœwietlanej planszy
     * @param LevelCapacity rozmiar planszy
     * @param X	Pozycja bomby X
     * @param Y	Pozycja bomby Y
     */
    Bomb(String Board_Layout, byte LevelCapacity, int X, int Y){
    	//Za³adowanie rodzajów bomb do animacji bomby
    	LoadBombs();
    	Board_NameOfFile = Board_Layout;
    	board = new byte[LevelCapacity][LevelCapacity];
    	this.LevelCapacity = LevelCapacity;
    	LocationX = X;
    	LocationY = Y;
    	BAnm = new Thread(new BombAnimation());
    }
    
    /**Funkcja pomocnicza s³u¿¹ca do ³adowania obrazka z pliku
     * @param name Nazwa obrazka w podkatalogu /Images/ 
     * @return img - Obrazek wczytany z pliku
     */
	private BufferedImage loadImage(String name) {
        String imgFileName = "Images/"+name+".png";
        BufferedImage img = null;
        try {
            img =  ImageIO.read(new File(imgFileName));
        } catch (IOException e) {
        	System.out.println("Nie ma obrazka");}
        return img;
    }
	
	/**
	 * Funkcja s³u¿¹ca do okreœlenia, które obrazy maj¹ byæ ³adowane z plików w katalogu /Images/
	 */
	public void LoadBombs(){
		Images = new BufferedImage[4];
		Images[0] = loadImage("bomba01");
		Images[1] = loadImage("bomba02");
		Images[3] = loadImage("wybuch01");
	}
	
	/**
	 * Metoda zwracaj¹ca pozycjê bomby. Wykorzystywana w klasie {@link GameField}
	 * @return obiekt klasy {@linkplain Dimension} zwracaj¹cy lokalizacjê bomby.
	 */
	public Dimension GetPosition()	{
		return new Dimension(LocationX,LocationY);
	}
	
	public /*byte[][]*/ void getBoardLayout(String NameOfFile, byte LevelCapacity)    {
		//board = new byte[LevelCapacity][LevelCapacity];
		Scanner s = null;
		//Œcie¿ka do pliku z uk³adem planszy
		String filePath= "src/db/"+NameOfFile+".txt";
		//Zmienna przechowuj¹ca tymczasow¹ wartoœæ
		byte temp;
		try	{
			//Wczytywanie skanera
			s = new Scanner(new BufferedReader(new FileReader(filePath)));
			for (byte i = 0; i< LevelCapacity; i++)
				for (byte j = 0; j< LevelCapacity; j++)	{
						if(s.hasNext())	{
							temp=(byte)s.nextInt();
							if (temp>=0 && temp<=3)
								board[i][j] = temp;
						}
				}
		} catch (FileNotFoundException e) {}
		finally {
			if (s != null) {
            s.close();
			}
		}
		//return board;
	}
	
	public /*byte[][]*/ void writeBoardLayout(String NameOfFile, byte LevelCapacity)    {
		//board = new byte[LevelCapacity][LevelCapacity];
		PrintWriter p = null;
		//Œcie¿ka do pliku z uk³adem planszy
		String filePath= "src/db/"+NameOfFile+".txt";
		//Zmienna przechowuj¹ca tymczasow¹ wartoœæ
		try	{
			//Wczytywanie skanera
			p = new PrintWriter(new FileWriter(filePath));
			//System.out.println(new FileWriter(filePath).getEncoding());
			for (byte i = 0; i< LevelCapacity; i++)
				for (byte j = 0; j< LevelCapacity; j++)	{
						p.print(board[i][j]);
						p.print(" ");
						System.out.print(board[i][j]+" ");
				}
			p.println();
		} catch (FileNotFoundException e) {}
		  catch (IOException e) {}
		finally {
			if (p != null) {
            p.close();
			}
		}
	}
	
	/**
	 * Funkcja wskazuj¹ca który obraz bomby ma byæ aktualnie wyœwietlany.
	 * Wykorzystywana w metodzie {@linkplain GameField.DrawImages}.
	 * @return obiekt klasy {@linkplain BufferedImage} zrwacaj¹cy obraz bomby, który ma
	 * byæ aktualnie wyœwietlony
	 */
	public BufferedImage GetBomb(){
		//Wyœwietlany obraz zale¿y od wskaŸnika Imagepointer, który jest definiowany w w¹tku
		//BombAnimation.
    	switch (Imagepointer)	{
			case	0	:	{
				return Images[0];
			}
			case	1	:	{
				return Images[1];
			}
			case	3	:	{
				return Images[3];
			}
		}
		return null;
	}
	
	private void Plansza()	{
		getBoardLayout(Board_NameOfFile,LevelCapacity);
		if (board[LocationX+1][LocationY]==1)	{
			board[LocationX+1][LocationY]=10;
		}
		else if (board[LocationX+1][LocationY]==0)	{
			
		}
		
		writeBoardLayout(Board_NameOfFile,LevelCapacity);
	}
	

	/**W¹tek obs³uguj¹cy animacjê bomby. Dziedziczy po klasie {@link Thread}
	 * Zawiera jedn¹ metodê {@link run} obs³uguj¹c¹ w¹tek bomby
	 */
public class BombAnimation extends Thread	{
	//Sta³a czasowa pokazuj¹ca po jakim czasie bomba wybucha
	private static final long FourSeconds = 4000;
	//Zmienna pomocnicza pokazuj¹ca ile czasu minê³o od postawienia bomby
	int Time;
	
	//@Override
	/**
	 * Jedyna metoda klasy BombAnimation. Jej dzia³anie polega na odmierzaniu czasu
	 * jaki min¹³ od postawienia bomby i na tej podstawie ustala wartoœæ wskaŸnika
	 * Imagepointer, który wskazuje na obraz jaki aktualnie ma byæ wyœwietlany.
	 */
	public void run() {
		// TODO Auto-generated method stub
		long previousTime = System.currentTimeMillis();
		while((System.currentTimeMillis() - previousTime)<(FourSeconds))	{
			Time = (int)(System.currentTimeMillis()-previousTime)/500;
			try	{
				switch(Time%2)	{
					case	0	:	{
						Imagepointer=0;
						Thread.sleep(250);
						continue;
					}
					case	1	:	{
						Imagepointer=1; 
						Thread.sleep(250);
						continue;
					}
				}
			} catch (InterruptedException e) {};
		}
		try	{
		Imagepointer=3;
		//System.out.println(System.currentTimeMillis());
		//Plansza();
		//System.out.println(System.currentTimeMillis());
		Thread.sleep(1000);
		} catch (InterruptedException e) {};
		
		
	}
		
}		
}
	



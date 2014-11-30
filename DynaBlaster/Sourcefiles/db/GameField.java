package db;

import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameField extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//Zmienne przekazane do konstruktora z klasy GUI
	private int LayerWidth;
	private int LayerHeight;
	//tablica wartoœci odpowiadaj¹cych za rodzaj œcian na planszy
	private byte[][] board;
	
	public GameField()	{
		super();
		setLayout(new FlowLayout());
	}
	
	/** Overriden paint method to draw images*/
	public void paint(Graphics g)	{
		LayerWidth = super.getSize().width;
		LayerHeight = super.getSize().height;
		Graphics2D g2 = (Graphics2D) g;
		try {
			DrawImages(g2);
		} catch (IOException e) {};
	}
	
	/**This method creates an image 
	 * @param String name of a file
	 * @Returns an Image, or null if the path was invalid.
	 * */
	private BufferedImage loadImage(String name) {
        String imgFileName = "Images/"+name+".png";
        BufferedImage img = null;
        try {
            img =  ImageIO.read(new File(imgFileName));
        } catch (Exception e) {
        	System.out.println("Nie ma obrazka");}
        return img;
    }
	
	/**Method passImages passes images*/
	private BufferedImage[] passImages()	{
		BufferedImage[] Images = new BufferedImage[3];
		Images[0] = loadImage("czarny");
		Images[1] = loadImage("czerwony");
		Images[2] = loadImage("niebieski");
		return Images;
	}
	
	/**Very important method passing dimensions used in paint method
	 * to set the size of graphics element
	 * @return Dimension Of Dimension type: JPanel dimensions 
	 */
	public Dimension getPreferredSize()	{
		return new Dimension( LayerWidth , LayerHeight );
	}
	
	/**This Method gets an array from a file named by 
	 * NameOfFile and writes it to the array board 
	 * @param NameOfFile a source of an array of numbers representing a board
	 * @param LevelCapacity expected width and height of an array 
	 * @return board an array with the numbers
	 * */
	private byte[][] getBoardLayout(String NameOfFile, byte LevelCapacity) throws IOException {
		board = new byte[LevelCapacity][LevelCapacity];
		Scanner s = null;
		//Œcie¿ka do pliku z uk³adem planszy
		String filePath= "Sourcefiles/db/"+NameOfFile+".txt";
		//Zmienna przechowuj¹ca tymczasow¹ wartoœæ
		byte temp;
		try	{
			//Wczytywanie skanera
			s = new Scanner(new BufferedReader(new FileReader(filePath)));
			for (byte i = 0; i< LevelCapacity; i++)
				for (byte j = 0; j< LevelCapacity; j++)	{
						if(s.hasNext())	{
							temp=(byte)s.nextInt();
							if (temp>=0 && temp<=2)
								board[i][j] = temp;
						}
				}
		}
		finally {
			if (s != null) {
            s.close();
			}
		}
		return board;
	}
	
	/**This method draws images on Graphics g2 image
	 * Uses passImages method
	 * Is invoked in paint method
	 * @param g2 An Rectangle to draw on
	 * @param LevelCapacity expected board size*/
	private void DrawImages(Graphics g2) throws IOException {
		int LevelCapacity = 10;
		Point ControllingPoint = new Point(0,0);
		//Wczytywanie obrazków z wygl¹dem œcian
		BufferedImage[] Images = passImages();
		//Zape³nianie pomocniczej tablicy wed³ug której rysowane bêd¹ elementy na ekranie
		board = new byte[LevelCapacity][LevelCapacity];
		board = getBoardLayout("board",(byte) LevelCapacity);
		//Wype³nianie planszy obrazkami
		for (short i = 0 ; i < LevelCapacity ; i++)	
			for (short j = 0 ; j < LevelCapacity ; j++)
			{				
							//Zmiania pocz¹tkowego punktu do rysowania
							ControllingPoint.setLocation((int)(i*(LayerWidth/LevelCapacity)),
														(int)(j*(LayerHeight/LevelCapacity)));
							/*Funkcja odpowidaj¹ca za wrysowywanie w komponent g2 typu Graphics rysunków 
							 * wczytanych wczeœniej z tablicy Images w odpowiednie miejsca na planszy */
							g2.drawImage(Images[ board[i][j] ],
							(int) ControllingPoint.getX(),(int) ControllingPoint.getY(),
							(int)( ControllingPoint.getX()+ ( LayerWidth/LevelCapacity )),
							(int)( ControllingPoint.getY()+ ( LayerHeight/LevelCapacity )),
							0,0,Images[ board[i][j] ].getWidth(),Images[ board[i][j] ].getHeight(),null);
			}
	}
	
}

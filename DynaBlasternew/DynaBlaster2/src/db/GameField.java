package db;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.WindowAdapter;
//import java.awt.AWTEvent;
import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 
 * @author Robert
 *
 */

public class GameField extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public int LocationX,LocationY;

	private Player p;
	private Timer timer;
	private Bomb b;
	
	Opponent Opponent1;
	Opponent Opponent2;
	Opponent Opponent3;
	
	Thread Opp1Thread;
	Thread Opp2Thread;
	Thread Opp3Thread;
	//sprawdza czy trzeba wrysowaæ bombe
	private int czy_bomba;
	//Zmienne przekazane do konstruktora z klasy GUI

    public int screenWidth;
    public int screenHeight;
    private int widthCell;
    private int heightCell;
	public int LevelCapacity = 20;
	//tablica wartoœci odpowiadaj¹cych za rodzaj œcian na planszy
	public byte[][] board;
	BufferedImage[] Images;
	private int LayerWidth;
	private int LayerHeight;
	boolean PierwszyRaz;
	String Board_NameOfFile;
	
	public GameField(String BoardFile){
		//m = new Map();
		Board_NameOfFile = BoardFile;
		p = new Player();
		
		board = getBoardLayout(Board_NameOfFile,(byte) LevelCapacity);
		
		Opponent1 = new Opponent(LevelCapacity-2, 1, board);
		Opponent2 = new Opponent(1, LevelCapacity-2, board);
		Opponent3 = new Opponent(LevelCapacity-2, LevelCapacity-2, board);
		
		Opp1Thread = new Thread(Opponent1.new OpponentMoving());
		Opp2Thread = new Thread(Opponent2.new OpponentMoving());
		Opp3Thread = new Thread(Opponent3.new OpponentMoving());
		
		Opp1Thread.start();
		Opp2Thread.start();
		Opp3Thread.start();
		setDoubleBuffered(true);
//		addKeyListener(this);
		setFocusable(true);
//		timer = new Timer(25,this);
//		timer.start();
		PierwszyRaz=true;
	}
	
	public synchronized void actionPerformed(ActionEvent e) {
		repaint();
		}
	/** Overriden paint method to draw images*/
	public void paintComponent(Graphics g)	{
		LayerWidth = getRootPane().getWidth();
		LayerHeight = getRootPane().getHeight();
		super.setSize(LayerWidth, LayerHeight);
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		DrawImages(g2);
		setBackground(Color.black);
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
        } catch (IOException e) {
        	System.out.println("Nie ma obrazka");}
        return img;
    }
	
	/**Method passImages passes images with the names below to get
	 * get the images, from which the board will be build.
	 * Uses {@link loadImage}
	 * @return Images table of images
	 */
	private BufferedImage[] passImages()	{
		BufferedImage[] Images = new BufferedImage[4];
		Images[0] = loadImage("floor");
		Images[1] = loadImage("crushable");
		Images[2] = loadImage("uncrushable");
		Images[3] = loadImage("podloze");
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
	public byte[][] getBoardLayout(String NameOfFile, byte LevelCapacity)    {
		board = new byte[LevelCapacity][LevelCapacity];
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
		return board;
	}
	
	/**This method draws images on Graphics g2 image
	 * Uses passImages method
	 * Is invoked in paint method
	 * @param g2 An Rectangle to draw on
	 * @param LevelCapacity expected board size*/
	private void DrawImages(Graphics g2){
		if (PierwszyRaz)	{
		//Wczytywanie obrazków z wygl¹dem œcian
		Images = passImages();
		//Zape³nianie pomocniczej tablicy wed³ug której rysowane bêd¹ elementy na ekranie
		board = new byte[LevelCapacity][LevelCapacity];
		PierwszyRaz=false;
		}
		//Wype³nianie planszy obrazkami
		board = getBoardLayout(Board_NameOfFile,(byte) LevelCapacity);
		
		widthCell = LayerWidth/LevelCapacity;
		heightCell = LayerHeight/LevelCapacity ;
		
		for (short j = 0 ; j < LevelCapacity ; j++)	
			for (short i = 0 ; i < LevelCapacity ; i++)
			{				
							
						g2.drawImage(Images[ board[i][j] ],i * widthCell,j*heightCell,
								widthCell, heightCell,null);
			}
		if(czy_bomba==1){
			g2.drawImage(b.GetBomb(),
					b.GetPosition().width *widthCell ,
					b.GetPosition().height *heightCell , 
					widthCell, heightCell, null);
			//board[p.GetLocationX()][p.GetLocationY()]=3;
			//System.out.println(board[p.GetLocationX()][p.GetLocationY()]);
			if (b.BAnm.isAlive()==false)	{
				czy_bomba=0;
				repaint();
			}
			if (b.BAnm.isAlive()==true)	{
				repaint();
			}
		}
		g2.drawImage(p.GetBomberman(), p.GetLocationX() *widthCell , 
				p.GetLocationY() *heightCell , widthCell, heightCell, null);
		g2.drawImage(Opponent1.GetBomberman(), Opponent1.GetLocationX() *widthCell , 
				Opponent1.GetLocationY() *heightCell , widthCell, heightCell, null);
		g2.drawImage(Opponent2.GetBomberman(), Opponent2.GetLocationX() *widthCell , 
				Opponent2.GetLocationY() *heightCell , widthCell, heightCell, null);
		g2.drawImage(Opponent3.GetBomberman(), Opponent3.GetLocationX() *widthCell , 
				Opponent3.GetLocationY() *heightCell , widthCell, heightCell, null);
		if (Opp1Thread.isAlive() && Opp2Thread.isAlive() && Opp3Thread.isAlive()){
			repaint();
		}
		//g2.drawImage(b.GetBomb(), p.GetLocationX() *widthCell , 
		//p.GetLocationY() *heightCell , widthCell, heightCell, null);
	}

	
public class BombermanListener {
	public void keyPressed(KeyEvent e){
		int keycode = e.getKeyCode();

		if (keycode == KeyEvent.VK_UP  ){
			if(board[p.GetLocationX()][p.GetLocationY()-1]==0){
				p.BombermanMove(0,-1);
			}
		}
			
		if (keycode == KeyEvent.VK_DOWN){
			if(board[p.GetLocationX()][p.GetLocationY()+1]==0){
				p.BombermanMove(0,1);
			}
		}
		
		if (keycode == KeyEvent.VK_LEFT){
			if(board[p.GetLocationX()-1][p.GetLocationY()]==0){
				p.BombermanMove(-1,0);
			}
		}
		//System.out.println("Nacisnieta strzalka w prawo");
		if (keycode == KeyEvent.VK_RIGHT){

			if(board[p.GetLocationX()+1][p.GetLocationY()]==0)
					{
				p.BombermanMove(1,0);
				//repaint();
			}
		}
		if (keycode == KeyEvent.VK_SPACE){
				b =new Bomb(Board_NameOfFile,(byte)LevelCapacity,p.GetLocationX(),p.GetLocationY());
				czy_bomba=1;
				b.BAnm.start(); 
				}
		
	}
}

}

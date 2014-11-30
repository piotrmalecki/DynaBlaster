package db;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;
import java.awt.Graphics;
import javax.imageio.ImageIO;


public class Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	Keys Ks;
	Scores scores;
	GUI mainframe;
	int OptionNumber;
	String[] txts =	{"Start Game","Scores","Keys","End Game"};
	String Pane = null;
	private Graphics2D g2D;
	private BufferedImage BackgroundImage=null;
	private BufferedImage img;
	
	/**Konstruktor kasy Menu uruchamiany z wewn�trz klasy {@linkplain GUI}
	 * 	Tworzy nowy panel, w kt�rym wy�wietlany jest menu.	 */
	
	public Menu(GUI main)	{
		super(new BorderLayout());
		this.mainframe = main;
		CreatingObjects();
		getAndRegisterFont();
		this.requestFocusInWindow();
		OptionNumber=0;
		Pane="Menu";
		setDoubleBuffered(true);
	}
	

	/**Tworzenie obiekt�w ukazuj�cych si� po wywo�aniu odpowiednich
	 * funkcji w menu
	 */
	private void CreatingObjects() {
		/**Torzenie panelu wy�wietlaj�cego informacje o klawiszach u�ywanych w grze*/
		Ks = new Keys();
		/**Torzenie panelu wy�wietlaj�cego informacje o wynikach graczy*/
		scores = new Scores();
	}

	
	/**This method creates an image from a file 
	 * @param {@link String} name of a file
	 * @returns  {@link BufferedImage} img , or null if the path was invalid.
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
	
	/** This method gets the font from the .TTF file and adds it to the current 
	 * {@link GraphicsEnvironment}. The loaded font is named "Eurostile Pogrubiony"
	 */
	public void getAndRegisterFont()	{
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Eurostib.ttf")));
		} catch (FontFormatException e) {} 
		  catch (IOException e) {}
//System.out.println("Czcionka");
	}
	
	/** Ta metoda rysuje teksty zawarte w tablicy txts zaznaczaj�c kt�ry tekst
	 *  jest wybrany z klawiatury.
	 * @param num Parametr kt�ry opowiada, kt�ry z kolei tekst jest aktualnie zaznaczony
	 * przy pomocy klawiatury.
	 */
	private void DrawStrings(int num)	{
		int YPosition = super.getSize().height/4;
		g2D.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		Font font = new Font("Eurostile Pogrubiony", Font.ITALIC, 40);
		Font font2 = font.deriveFont((float)30);
		g2D.setColor(Color.white);
		switch (num)	{
			case	0	:	{
				//Napis 1 jest wi�kszy, reszta mniejsza
				g2D.setFont(font);
				g2D.drawString(txts[0], 5, YPosition);
				g2D.setFont(font2);
				g2D.drawString(txts[1], 5, YPosition + 50);
				g2D.drawString(txts[2], 5, YPosition + 100);
				g2D.drawString(txts[3], 5, YPosition + 150);
				return;
			}
			case	1	:	{
				//Napis 2 jest wi�kszy, reszta mniejsza
				g2D.setFont(font2);
				g2D.setColor(Color.white);
				g2D.drawString(txts[0], 5, YPosition);
				g2D.setFont(font);
				g2D.drawString(txts[1], 5, YPosition + 50);
				g2D.setFont(font2);
				g2D.drawString(txts[2], 5, YPosition + 100);
				g2D.drawString(txts[3], 5, YPosition + 150);
				return;
			}
			case	2	:	{
				//Napis 3 jest wi�kszy, reszta mniejsza
				g2D.setFont(font2);
				g2D.drawString(txts[0], 5, YPosition);
				g2D.drawString(txts[1], 5, YPosition + 50);
				g2D.setFont(font);
				g2D.drawString(txts[2], 5, YPosition + 100);
				g2D.setFont(font2);
				g2D.drawString(txts[3], 5, YPosition + 150);
				return;
			}
			case	3	:	{
				//Napis 4 jest wi�kszy, reszta mniejsza
				g2D.setFont(font2);
				g2D.drawString(txts[0], 5, YPosition);
				g2D.drawString(txts[1], 5, YPosition + 50);
				g2D.drawString(txts[2], 5, YPosition + 100);
				g2D.setFont(font);
				g2D.drawString(txts[3], 5, YPosition + 150);
				return;
			}
		}		
	}
	
	/**Przeci��ona metoda paintComponent*/
	public void paintComponent(Graphics g)	{
		super.paintComponent(g);
		if (BackgroundImage==null)	{
			//Menu wy�wietlane po raz pierwszy, wybranie obrazka do t�a menu
			// menulayout - nazwa obrazka w katalogu /Images/
			img = loadImage("MENUIMAGE");
		}
		//Tworzenie obrazka do obs�ugi podw�jnego buforowania
		//Nowy Background Image jest tworzony tylko przy okazji zwi�kszania rozmiaru okna
		BackgroundImage = new BufferedImage(super.getWidth(),super.getHeight(),BufferedImage.TYPE_INT_RGB);
		g2D = (Graphics2D)BackgroundImage.getGraphics();
		//Wklejanie obrazka w t�o z obrazku z ty�u
		g2D.drawImage(img,0,0,super.getSize().width,super.getSize().height,
									0,0,img.getWidth(),img.getHeight(),this);
		//Napisanie tekst�w na obrazku
		DrawStrings(OptionNumber);
		//Wklejenie obrazka z ty�u do obrazka odpowiadaj�cego za Panel Menu
		g.drawImage(BackgroundImage,0,0,null);
		System.out.println(super.getSize());
	}
	

	/**Funkcja odpowiadaj�ca za nas�uchiwanie klawiszowe w menu
	 * Funkcje obs�uguj�ce odpowiednie klawisze obs�ugiwane w poszczeg�lnych panelach
	 * s� zawarte w odpowiednich klasach */
	public void KeyReleased(KeyEvent e) {
		//Zmienna kt�ra zachowuje warto�� zwracan� z metod KeyChanging z odpowiednich klas
		int Number;
		
		//Sprawdzenie czy teraz nas�uchujemy Panelu klasy Menu
		if (Pane == "Menu")	{
			Number = KeyChanging(e);
			switch (Number)	{
				case	0	:	{
					//Wci�ni�to Start Game w Menu
					//Wej�cie do g��wnej gry
					mainframe.remove(this);
					mainframe.SetUpGame();
					//Przekazanie sterowania do Panelu w�a�ciwego gry
				}
				case	1	:	{
					//Wci�ni�to Scores w Menu
					//Obs�uga wy�wietlania pola z wynikami
					mainframe.remove(this);
					SetUpScores();
					mainframe.revalidate();
					mainframe.repaint();
					Pane = "Scores";
					return;
				}
				case 	2	:	{
					//Wci�ni�to pole z klawiszami
					//Widok obrazka z zapisanymi klawiszami
					mainframe.remove(this);
					SetUpKeys();
					mainframe.revalidate();
					mainframe.repaint();
					Pane = "Keys";
					return;
				}
				case	3	:	{
					//Obs�uga wyj�cia z gry po wci�ni�ciu "Exit Game"
					System.exit(0);
					return;
				}
				default	:	{
					//Obs�uga zmiany wy�wietlania menu w wyniku naci�ni�cia strza�ek
					mainframe.repaint();
					return;
				}
				
			}
		}
		
		//Sprawdzenie nas�uchiwania aktualnie wy�wietlanego panelu Keys
		if (Pane == "Keys")	{
			Number = Ks.KeyChanging(e);
			if (Number == 1){
				//Wci�ni�to BackSpace, ponowne wy�wietlanie menu
				mainframe.remove(Ks);
				mainframe.add(this);
				Pane="Menu";
				mainframe.revalidate();
				mainframe.repaint();
			}
		}
		
		//Sprawdzenie nas�uchiwania aktualnie wy�wietlanego panelu Keys
		if (Pane == "Scores")	{
			Number = scores.KeyChanging(e);
			if (Number == 1){
				//Wci�ni�to BackSpace, ponowne wy�wietlanie menu
				mainframe.remove(scores);
				mainframe.add(this);
				Pane = "Menu";
				mainframe.revalidate();
				mainframe.repaint();
			}
		}
				
	}
	
	/**Metoda obs�uguj�ca panel z informacjami o klawiszach*/
	private void SetUpKeys() {
		mainframe.add(Ks);
		//Przekazanie sterowania do Panelu klawiszy
	}
	
	/**Metoda obs�uguj�ca panel z wynikami*/
	private void SetUpScores() {
		mainframe.add(scores);
	}

	
	/**
	 * Determinuje co zrobi� gdy naci�ni�ty zostanie odpowiedni klawisz
	 * @param Key przkazywany z przeci��onej metody {@link keyReleased} z klasy GUI
	 * Metoda ta s�u�y g��wnie do zdeterminowania, kt�ry tekst ma by� wy�wietlany jako wi�kszy
	 */
	protected int KeyChanging (KeyEvent Key)	{
		int ID = Key.getKeyCode();
		System.out.println(Key.getKeyCode());
		if (ID == KeyEvent.VK_UP)	{
			//Naci�ni�to strza�k� do g�ry
			if (OptionNumber==0)	{
				OptionNumber = txts.length-1;
			}
			else	{ 
				OptionNumber -=1;
			}
		}
		else if (ID == KeyEvent.VK_DOWN) {
			//Naci�ni�to strza�k� do g�ry
			if (OptionNumber==txts.length-1)	{	
				OptionNumber = 0;
			}
			else	{ 
				OptionNumber +=1;
			}
		}
		else if (ID == KeyEvent.VK_ENTER) {
			//Naci�ni�to na klawiaturze ENTER
			return OptionNumber;				
			}	
		//Je�li naci�ni�to strza�ki zwr�� liczb� wi�ksz� od d�ugo�c tablicy, w
		//kt�rej zapisane s� teksty do wy�wietlania w menu
		return 5;
	}
	
}

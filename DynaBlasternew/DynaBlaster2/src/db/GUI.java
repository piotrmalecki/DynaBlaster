package db;

import javax.swing.*;

import db.GameField.BombermanListener;

import java.awt.*;
import java.awt.event.*;

/**	G³ówna klasa do tworzenia okienka do gry DynaBlaster
 * Wykorzystuje konstruktor jako Ÿród³o tworzenia wygl¹du okna
 * @param args nieu¿ywane
 * @param JFrame okno gry
 * @param dims wymiary okna 
 */
public class GUI extends JFrame implements KeyListener {
	//Serializacja klasy
	private static final long serialVersionUID = 1L;
	//Utworzenie obiektów okna i jego wymiarów
//	private JPanel LayerOfResults;
	public Dimension dims;
	Menu menu = null;
	GameField GF = null;
	BombermanListener bomberlistener=null;
	ResultsField RF=null;
	
	/**
	 * Konstruktor klasy GUI tworzy now¹ ramkê JFrame
	 * @param name nazwa ramki
	 */
	public GUI (String name)	{
		//Tworzenie nowego okna i dodanie funkcjonalnoœci zamykania
		addWindowListener(new WindowAdapter() {
            /**
             * Handles window closing events.
             * @param evt window event
             */
            public void windowClosing(WindowEvent evt) {
                /** terminate the program */
                System.exit(0);
            }
        	public void windowActivated(WindowEvent e)	{
        		repaint();
        	}
            
        });
		
		//Pobranie rozdzielczoœci ekranu
		dims = new Dimension();
		dims = getResolution(dims);
		
		/*Ustawienie rozmiarów ramki na odpowiednie i w odpowiednim
		miejscu na ekranie*/
		setPreferredSize(dims);
		setLocation(100, 0);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
		/**Tworzenie i dodawanie panelu g³ównego menu przy pomocy klasy {@link Menu}*/
		add(menu = new Menu(this));
		
		pack();	
	}
	

	/**
	 * This method uses {@link Toolkit.getDefaultToolkit().getScreenSize()} to get the size
	 * of the screen and optimize it for the field game.
	 * @param dims contains the frame size
	 * @return modified frame size
	 */
	private Dimension getResolution (Dimension dims)	{
		dims.width = (Toolkit.getDefaultToolkit().getScreenSize().width);
		dims.width /= 2;
		dims.width -=14;
		dims.height = (Toolkit.getDefaultToolkit().getScreenSize().height)*5;
		dims.height /= 6;
		dims.height -= 10;
		return dims;
	}
	
	/**Metoda wstawiaj¹ca 2 warstwy do ramki okna
	 * wywo³uj¹ca planszê gry i pole wyników
	 * w jednym panelu JSplitPane w po³o¿eniu pionowym
	 */
	public void SetUpGame()	{
		/**Tworzenie pola gry*/
		GF = new GameField("board");
		/**Tworzenie obiektu nas³uchuj¹cego zdarzenia w GameField*/
		bomberlistener = GF.new BombermanListener();
		/**Tworzenie pola wyników*/
		RF = new ResultsField();
		menu=null;
		add(RF,BorderLayout.PAGE_START);
		add(GF);
		/*JSplitPane SP = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true, RF,GF);
		SP.setDividerSize(0);
		add( SP );*/	
		revalidate();
		repaint();
	}
	
	/**G³ówna funkcja programu, uruchamia okno JFrame wyœwietlaj¹ce grê*/
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
					new GUI("Dyna Blaster").setVisible(true);
            }
         });
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
        if (GF != null)	{
        	bomberlistener.keyPressed(e);
        	repaint();
        }
        
    }
	
	@Override
	public void keyReleased(KeyEvent e) {
        if (menu != null)	{
        	menu.KeyReleased(e);
        	repaint();
        }
    }

 	@Override
 	public void keyTyped(KeyEvent e) {
 		// TODO Auto-generated method stub	
 	}

}

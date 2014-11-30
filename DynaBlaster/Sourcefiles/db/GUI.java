package db;

import javax.swing.*;
import java.awt.*;

/**	G³ówna klasa do tworzenia okienka do gry DynaBlaster
 * Wykorzystuje konstruktor jako Ÿród³o tworzenia wygl¹du okna
 * @param args nieu¿ywane
 * @param JFrame okno gry
 * @param dims wymiary okna 
 */

public class GUI extends JFrame {
	//Serializacja klasy
	private static final long serialVersionUID = 1L;
	//Utworzenie obiektów okna i jego wymiarów
//	private JPanel LayerOfResults;
	public Dimension dims;
	
	//Konstruktor wywo³any z metody main 
	public GUI (String name)	{
		//super(name);
		//setLayout(new BorderLayout());
		
		//Tworzenie nowego okna i dodanie funkcjonalnoœci zamykania
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Pobranie rozdzielczoœci ekranu
		dims = new Dimension();
		dims = getResolution(dims);
		
		/*Ustawienie rozmiarów ramki na odpowiednie i w odpowiednim
		miejscu na ekranie*/
		setPreferredSize(dims);
		setLocation(100, 0);
		
		/*Ustawianie warstw*/
		SetUpLayers();
		
		pack();	
	}
	
	/**Metoda wstawiaj¹ca 2 warstwy do @param frames
	 * @param frame ramka g³ówna
	 * @param dims wymiary ramki g³ównej
	 * @return frames ramka z na³o¿onymi warstwami typu JPanel
	 */
	public void SetUpLayers()	{
//		LayerOfResults = new JPanel();

		//Wymiary ramki z rezultatami
//		int LayerWidth = dims.width;
//		int LayerHeight = ((dims.height)/3);
		
		ResultsField RF = new ResultsField();
//		LayerWidth = dims.width;
//		LayerHeight = (LayerHeight*2);
		GameField GF = new GameField();
		JSplitPane SP = new JSplitPane(JSplitPane.VERTICAL_SPLIT, RF,GF);
//		add( RF );
//		add( GF );
		add( SP );
		
	}

	private Dimension getResolution (Dimension dims)	{
		dims.width = (Toolkit.getDefaultToolkit().getScreenSize().width)*2;
		dims.width /= 7;
		dims.height = (dims.width)*3;
		dims.height /= 2;
		return dims;
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
					new GUI("Dyna Blaster").setVisible(true);
            }
         });
	}

}

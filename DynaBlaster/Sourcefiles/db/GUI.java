package db;

import javax.swing.*;
import java.awt.*;

/**	G��wna klasa do tworzenia okienka do gry DynaBlaster
 * Wykorzystuje konstruktor jako �r�d�o tworzenia wygl�du okna
 * @param args nieu�ywane
 * @param JFrame okno gry
 * @param dims wymiary okna 
 */

public class GUI extends JFrame {
	//Serializacja klasy
	private static final long serialVersionUID = 1L;
	//Utworzenie obiekt�w okna i jego wymiar�w
//	private JPanel LayerOfResults;
	public Dimension dims;
	
	//Konstruktor wywo�any z metody main 
	public GUI (String name)	{
		//super(name);
		//setLayout(new BorderLayout());
		
		//Tworzenie nowego okna i dodanie funkcjonalno�ci zamykania
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Pobranie rozdzielczo�ci ekranu
		dims = new Dimension();
		dims = getResolution(dims);
		
		/*Ustawienie rozmiar�w ramki na odpowiednie i w odpowiednim
		miejscu na ekranie*/
		setPreferredSize(dims);
		setLocation(100, 0);
		
		/*Ustawianie warstw*/
		SetUpLayers();
		
		pack();	
	}
	
	/**Metoda wstawiaj�ca 2 warstwy do @param frames
	 * @param frame ramka g��wna
	 * @param dims wymiary ramki g��wnej
	 * @return frames ramka z na�o�onymi warstwami typu JPanel
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

package db;

import javax.swing.*;

import java.awt.*;

public class Layers extends JPanel {
	

	private static final long serialVersionUID = 1L;
	
	public JPanel LayerOfResults;
	public JPanel LayerOfGameField;
	public Dimension dims;
	
	public Layers(Dimension dim)	{
		dims = dim;
		LayerOfResults = new JPanel();
		LayerOfGameField = new JPanel();
	}
	
	public void paint(Graphics g)	{
		dims = super.getSize();
		System.out.println("Wywo³ano paint w Layers");
		System.out.println(dims);
		//Wymiary ramki z rezultatami
		int LayerWidth = dims.width;
		int LayerHeight = (dims.height/3);
		LayerOfResults.setPreferredSize(new Dimension(LayerWidth,LayerHeight));
		LayerOfResults.setBorder(BorderFactory.createTitledBorder("Moj Pierwszy Panel:"));
		//Wymiary ramki pola gry 2* wy¿sze ni¿ ramki z rezultatami
		LayerOfGameField.setPreferredSize(new Dimension( LayerWidth , (2*LayerHeight)-20 ));
		LayerOfGameField.setSize(new Dimension( LayerWidth , (2*LayerHeight) ));
		//Dodanie dwóch paneli do g³ównego okna (zewnêtrzne klasy i metody)
		ResultsField RF = new ResultsField();
		RF.CreateRF( LayerOfResults.getSize() );
		LayerOfResults.add( RF, BorderLayout.CENTER);
		add(LayerOfResults,BorderLayout.NORTH);
		
		//GameField GF = new GameField();
//		LayerOfGameField.add( new GameField() , BorderLayout.CENTER );
		add( LayerOfGameField , BorderLayout.SOUTH );
		
		super.paint(g);
	}

}

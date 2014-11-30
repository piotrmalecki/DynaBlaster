package db;

import java.awt.*;
import javax.swing.*;

public class ResultsField extends JPanel	{
	
	private static final long serialVersionUID = 1L;
	
	int LayerWidth;
	int LayerHeight;
	private String[] texts;
	private JLabel ScoreText;
	private JLabel TimeText;
	private JLabel NoOfLifes;
	Color BackgroundColor;
	Color FontColor;
	private static final byte NoOfElements = 3;
	
	public ResultsField () {
		super(new GridLayout(1,3));
//		LayerWidth = super.getSize().width;
//		LayerHeight = super.getSize().height;
//		super.setLayout();
		
		super.setBackground(new Color(0,0,0));
		ScoreText = new JLabel();
		ScoreText.setPreferredSize( new Dimension(super.getSize().width/3,super.getSize().height) );
		ScoreText.setSize( new Dimension(super.getSize().width/3,super.getSize().height) );
		TimeText = new JLabel();
		NoOfLifes = new JLabel();
		preferences();
		add(ScoreText);
		add(TimeText);
		add(NoOfLifes);
//		add(new JButton("Button"));
//		repaint();
	}
//	public void paint (Graphics g)	{
//		this.setBackground(new Color(255,0,0));
//		LayerWidth = super.getSize().width;
//		LayerHeight = super.getSize().height;
//		texts = StringTexts();
//		ScoreText.setText(texts[0]);
//		ScoreText.setBackground(new Color(255,0,0));
//		System.out.println("Utworzono tekst z ScoreText: " + ScoreText.getText());
//		TimeText.setText(texts[1]);
//		System.out.println("Utworzono tekst z TimeText: " + TimeText.getText());
//		NoOfLifes.setText(texts[2]);
//		System.out.println("Utworzono tekst z NoOfLifes: " + NoOfLifes.getText());
//	}
	
	public String[] StringTexts()	{
		texts[0]="00:00:00";
		texts[1]="0987";
		texts[1] = texts[1] + " pts";
		texts[2] = "Lives: 3";
		return texts;
	}
	private void preferences ()	{
		BackgroundColor = new Color (0,0,0);
		FontColor = new Color (255,255,255);
		texts = new String[NoOfElements];
		texts = StringTexts();
		
		ScoreText.setForeground(FontColor);
		ScoreText.setBackground(BackgroundColor);
		ScoreText.setOpaque(true);
		ScoreText.setHorizontalAlignment(JLabel.CENTER);
//		ScoreText.setFont(Font.ROMAN_BASELINE);
		ScoreText.setText(texts[0]);
		
		TimeText.setForeground(FontColor);
		TimeText.setBackground(BackgroundColor);
		TimeText.setOpaque(true);
		TimeText.setHorizontalAlignment(JLabel.CENTER);
		TimeText.setText(texts[1]);
		
		NoOfLifes.setForeground(FontColor);
		NoOfLifes.setBackground(BackgroundColor);
		NoOfLifes.setOpaque(true);
		NoOfLifes.setHorizontalAlignment(JLabel.CENTER);
		NoOfLifes.setText(texts[2]);
	}
	
	public Dimension getPreferredSize()	{
		return new Dimension( 300 , 60 );
	}
	
}

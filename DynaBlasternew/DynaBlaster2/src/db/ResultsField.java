package db;

import java.awt.*;
//import java.io.File;
//import java.io.IOException;
import javax.swing.*;
	
	/** This class creates a Panel with time, score and remaining lives.
	 *  Used in {@link GUI}
	 * 
	 *
	 */
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
		super.setBackground(new Color(0,0,0));
		ScoreText = new JLabel();
		TimeText = new JLabel();
		NoOfLifes = new JLabel();
		preferences();
		add(TimeText);
		add(ScoreText);
		add(NoOfLifes);
		Thread time = new Thread(new Timing());
		time.start();
		Thread wynik = new Thread(new Wynik());
		wynik.start();
		Thread zycia = new Thread(new Zycia());
		zycia.start();
	}
	
	synchronized public String[] StringTexts()	{
		texts[0]="00:00";
		texts[1]="00000";
		texts[1] = texts[1] + " pts";
		texts[2] = "Lives: 3";
		return texts;
	}
	
	/**This method optimizes the three panels 
	 * used to paint time, score and lives*/
	private void preferences ()	{
		BackgroundColor = new Color (0,0,0);
		FontColor = new Color (255,255,255);
		texts = new String[NoOfElements];
		texts = StringTexts();
		//getAndRegisterFont();
		Font font = new Font("Eurostile Pogrubiony",Font.ITALIC, 24);
		ScoreText.setFont(font);
		TimeText.setFont(font);
		NoOfLifes.setFont(font);
		
		TimeText.setForeground(FontColor);
		TimeText.setBackground(BackgroundColor);
		TimeText.setOpaque(true);
		TimeText.setHorizontalAlignment(JLabel.CENTER);
		TimeText.setText(texts[0]);
		
		ScoreText.setForeground(FontColor);
		ScoreText.setBackground(BackgroundColor);
		ScoreText.setOpaque(true);
		ScoreText.setHorizontalAlignment(JLabel.CENTER); 
		ScoreText.setText(texts[1]);
		
		NoOfLifes.setForeground(FontColor);
		NoOfLifes.setBackground(BackgroundColor);
		NoOfLifes.setOpaque(true);
		NoOfLifes.setHorizontalAlignment(JLabel.CENTER);
		NoOfLifes.setText(texts[2]);
	}
	
//	/** This method gets the font from the .TTF file and adds it to the current 
//	 * {@link GraphicsEnvironment}. The loaded font is named "Eurostile Pogrubiony"
//	 * 
//	 */
//	public void getAndRegisterFont()	{
//		try {
//			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Eurostib.ttf")));
//		} catch (FontFormatException e) {} 
//		  catch (IOException e) {}
//	}
	
	public Dimension getPreferredSize()	{
		return new Dimension( 300 , 30 );
	}
	
	public JLabel getTimeText()	{
		return TimeText;
	}
	
	public JLabel getScoreText()	{
		return ScoreText;
	}
	
	public JLabel getNoOfLifes()	{
		return NoOfLifes;
	}

private class Timing extends Thread	{
	
		private static final long OneSecond = 1000;
		int time;
		int minutes;
		int seconds;
		String TimeTxt;
		@Override
		public void run() {
			while(true)
				getTimeText().setText(MeasureTime());
			
		}
		public String MeasureTime ()	{
			long previousTime = System.currentTimeMillis();
			try	{
				Thread.sleep(1000);
				time++;
				if (System.currentTimeMillis() - previousTime >= OneSecond)	{
					if ( time/60 >= 1)	{
						minutes = time/60;
						seconds = time%60;
						if (seconds<10)	{
							TimeTxt = "0"+minutes+":0"+seconds;
						}
						else	{
							TimeTxt = "0"+minutes+":"+seconds;
						}
					}
					else	{
						seconds = time%60;
						if (seconds<10)	{
							TimeTxt = "00:0"+seconds;
						}
						else	{
							TimeTxt = "00:"+seconds;
						}
					}
				}
			}	catch (InterruptedException e) {};
		return TimeTxt;
		}
		
	}

private class Wynik extends Thread	{
	int Score;
	String text;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)	{
		try	{	
			Thread.sleep(250);
			Score+=20;
			text=Score+"0";
			getScoreText().setText(text);
		}	catch (InterruptedException e)	{};
		}
		
	}
}

private class Zycia extends Thread	{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)	{
			
		}
		
	}
	
	}
}
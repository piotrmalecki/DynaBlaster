package db;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Scores extends JPanel {

	private static final long serialVersionUID = 1L;
	//Obiekt typu Graphics uzywany do malowania za BufferedImage BackgroundImage
	private Graphics2D g2D;
	//Obrazek wykorzystywany w podwójnym buforowaniu
	private BufferedImage BackgroundImage=null;
	//Obrazek symbolizujacy t³o
	private BufferedImage img;
	
	/**Konstruktor klasy Scores tworz¹cy obiekt klasy JPanel*/
	public Scores ()	{
		super(new BorderLayout());
		setDoubleBuffered(true);
	}
	
	/**Obs³uga malowania*/
	public void paintComponent(Graphics g)	{
		super.paintComponent(g);
		if (BackgroundImage==null)	{
			img = loadImage("BESTRESULTS");
		}
		BackgroundImage = new BufferedImage(super.getWidth(),super.getHeight(),BufferedImage.TYPE_INT_RGB);
		g2D = (Graphics2D)BackgroundImage.getGraphics();
		g2D.drawImage(img,0,0,super.getSize().width,super.getSize().height,
									0,0,img.getWidth(),img.getHeight(),this);
		g.drawImage(BackgroundImage,0,0,null);
	}
	
	
	/**Pobieranie obrazka z pliku
	 * @param name nazwa pliku w którym znajduje siê obraz z katalogu /Images
	 * @return img obraz z pliku*/
	private BufferedImage loadImage(String name) {
        String imgFileName = "Images/"+name+".png";
        BufferedImage img = null;
        try {
            img =  ImageIO.read(new File(imgFileName));
        } catch (Exception e) {
        	System.out.println("Nie ma obrazka");}
        return img;
    }

	
	protected int KeyChanging (KeyEvent Key)	{
		int ID = Key.getKeyCode();
		if (ID == KeyEvent.VK_BACK_SPACE) {
			return 1;
		}
		return 0;
	}

}
package pacman;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Board extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private Timer timer;
	public int locationX,locationY;
	private Map m;
	private Player p;
	private Ghost d;
    public int screenWidth;
    public int screenHeight;
    private int widthCell;
    private int heightCell;
   
    private ImageIcon img1 = new ImageIcon("C://Users//Marek//Desktop//workspace//pacman//Images//kratka.png");
    private Image kafelek = img1.getImage();
    
    private ImageIcon img2 = new ImageIcon("C://Users//Marek//Desktop//workspace//pacman//Images//kropka.png");
    private Image kropka = img2.getImage();
	
	public Board(){
		m = new Map();
		p = new Player();
		d = new Ghost();
		addKeyListener(new PacmanListener());
		setFocusable(true);
		timer = new Timer(25,this);
		timer.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		}
	
	public void paint(Graphics g){
		super.paint(g);
		screenWidth = super.getSize().width;
		screenHeight = super.getSize().height;
		widthCell = screenWidth/Map.getGridX();
		heightCell = screenHeight/Map.getGridY() ;
		setBackground(Color.black);
		
		for(int x=0; x<Map.getGridX(); x++){
			for(int y=0; y<Map.getGridY();y++){
				
				if(m.getMap(x, y).equals("X")){
					g.drawImage(kafelek, x * widthCell , y * heightCell, widthCell, heightCell, null);
					g.setColor(Color.GREEN);
					g.drawRect(x * widthCell , y * heightCell, widthCell, heightCell);
				}
				if(m.getMap(x, y).equals("Y")){
					g.drawImage(kropka, x * widthCell , y * heightCell, widthCell, heightCell, null);
				}
				
			}
		}
		g.drawImage(p.getPacman(), p.getlocationX() * widthCell,p.getlocationY() * heightCell, widthCell, heightCell, null);
		g.drawImage(d.getGhost(), d.getlocationX() * widthCell,d.getlocationY() * heightCell, widthCell, heightCell, null);
	
	}
	
	public class PacmanListener extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			int keycode = e.getKeyCode();
			
			if (keycode == KeyEvent.VK_UP  ){
				if(!m.getMap(p.getlocationX(), p.getlocationY()-1).equals("X")){
					p.move(0,-1);
				}
			}
				
			if (keycode == KeyEvent.VK_DOWN){
				if(!m.getMap(p.getlocationX(), p.getlocationY() + 1).equals("X")){
						p.move(0,1);
				}
			}
			
			if (keycode == KeyEvent.VK_LEFT){
				if(!m.getMap(p.getlocationX() - 1, p.getlocationY()).equals("X")){
					p.move(-1,0);
				}
			}
			
			if (keycode == KeyEvent.VK_RIGHT){
				if(!m.getMap(p.getlocationX() + 1, p.getlocationY()).equals("X")){
					p.move(1,0);
				}
			}
		}
		
		public void keyRelased(KeyEvent e){
			
		}

		public void keyTyped(KeyEvent e){
			
		}
	}
}



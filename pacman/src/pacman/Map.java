package pacman;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Map {
	private Scanner m;
	private static int gridX=19;
	private static int gridY=14;
	private String Map[] = new String[gridY];
	
	public static int getGridX(){ return gridX; }
	public static int getGridY(){ return gridY; }
	
	public Map(){
		openFile();
		readFile();
		closeFile();
	}
	
	public String getMap(int x, int y){
		String index = Map[y].substring(x, x + 1 );
		return index;
	}
	public void openFile(){
		
		
		try {
			m = new Scanner(new File("C://Users//Marek//Desktop//workspace//pacman//src//pacman//Map.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Nie ma obrazka");
		}
	}
	public void readFile(){
		while(m.hasNext()){
			for(int i = 0; i <gridY; i++){
				Map[i] = m.next();
			}
		}	
	}
		
	public void closeFile(){
		m.close();
	}
}

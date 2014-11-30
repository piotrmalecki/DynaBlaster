package pacman;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Maze {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
			new Maze();
			}
		});
	}
	public Maze(){
		JFrame f= new JFrame();
		f.setSize(420,500);
		Board b = new Board();
		f.add(b);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

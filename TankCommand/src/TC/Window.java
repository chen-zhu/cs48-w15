package TC;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame{
	public Window(){
		this.setTitle("Tank Command"); 
		this.setSize(1000, 530);
		this.setLocationRelativeTo(null);
		this.setResizable(true); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		//create framework that extends the canvas and put it on the frame 
		this.setContentPane(new Framework());
		this.setVisible(true); 
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Window(); 
			}}); 
		
	}

}

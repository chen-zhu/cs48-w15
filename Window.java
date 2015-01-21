package tank;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame{
	private Window()
	    {
		    JFrame f=new JFrame(); 
		    //f.add(new playertank());
		    f.add(new background());
		    //f.add(new playertank());
		    f.setTitle("Tank Command");
	        f.setSize(1768, 530);
	        f.setResizable(true);
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	        
	        f.setVisible(true);
	    }

	public static void main(String[] args) {
		 new Window(); 
	} 

}

package tank;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*; 
import java.awt.geom.*; 

public class playertank extends JPanel implements ActionListener, KeyListener{
	Timer t = new Timer(5, this); 
	double x = 0, y = 0, velx=0, vely=0; 
	public Image img; 
	int d=2;
	
	public playertank(){
		t.start(); 
		addKeyListener(this); 
		setFocusable(true); 
	}
	
	public void paintComponent(Graphics g){
		ImageIcon i = new ImageIcon("Wot-heavytank.png"); 
		img = i.getImage();
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g; 
		g2.drawImage(img, (int)x, 300, null); 
	}
	
	public void actionPerformed(ActionEvent e){
		repaint(); 
		x+=velx; 
		if (x<2){
			x=2; 
		}
		if (x>250){
			x=250; 
		}
	}
	
	public void left(){
		velx=-2; 
	}
	
	public void right(){
		velx=+2; 
	}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); 
		if (code==KeyEvent.VK_RIGHT){
			right(); 
		}
		if (code==KeyEvent.VK_LEFT){
			left(); 
		}	
	}
	public void keyReleased(KeyEvent e) {
		velx=0; 
	}

	public void keyTyped(KeyEvent e) {
	}

}


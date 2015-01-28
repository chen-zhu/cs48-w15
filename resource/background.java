package tank;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*; 
import java.awt.geom.*; 

public class background extends JPanel implements ActionListener, KeyListener{
	Timer t = new Timer(5, this); 
	double x = 0, y = 0, velx=0, vely=0; 
	public Image img; 
	int d=2;
	playertank tank; 
	
	public background(){
		tank = new playertank();
		t.start(); 
		addKeyListener(this); 
		setFocusable(true); 
		//setFocusTraversalKeysEnabled(false); 
	}
	
	public void paintComponent(Graphics g){
		ImageIcon i = new ImageIcon("desert.png"); 
		//ImageIcon ii = new ImageIcon("Wot-heavytank.png"); 
		//Image imgg=ii.getImage();
		img = i.getImage();
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D) g; 
		g2.drawImage(img, (int)x, 0, null); 
		g2.drawImage(img, (int)x+768*1, 0, null);//how to make it continue?
		g2.drawImage(img, (int)x+768*2, 0, null);
		//g2.drawImage(imgg, 0, 250, null); 
	}
	
	public void actionPerformed(ActionEvent e){
		repaint(); 
		x+=velx; 
		if (x>0){
			x=0; 
		}
	}
	
	public void left(){
		velx=6; 
	}
	
	public void right(){
		velx=-6; 
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode(); 
		if (code==KeyEvent.VK_RIGHT){
			right(); 
		}
		if (code==KeyEvent.VK_LEFT){
			left(); 
		}	
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		velx=0; 
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

}

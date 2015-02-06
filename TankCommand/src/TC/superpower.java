package TC;

import java.awt.*; 
import java.awt.image.*; 

public class superpower {
	
	public static long superpowerperiod = (long) (Framework.nanosecond/1); //time between the two superpower <------changed the data to change the frequency of the bullet.
	public static long lastcreatsuperpower=0; 

	//superpower image 
	public static BufferedImage superpower; 
	
	//set superpower damege 
	public static int superdamage=100; 
	
	//superpower position 
	public double x; 
	public double y; 
	public static double speed=4; 
	
	//constructor for superpower 
	public superpower(){
		this.x=-5; 
		this.y=0; 
	}
	
	public boolean isleft(){
		if (x<Framework.width )
			return false; 
		else 
			return true; 
 
	}
	
	//update the superpower class(make it move to the right 
	

	public void update(){
		x+=speed; //only move to the right 

	}
	
	//draw the superpower onto the screen 
	public void Draw(Graphics2D g2d){
		g2d.drawImage(superpower, (int)x, (int)y, null); 
	}
	
	
	
	
	
	
	

}

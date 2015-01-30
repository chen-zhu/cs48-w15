package TC;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import java.io.*; 
import java.net.*; 
import java.util.logging.*; 

import javax.imageio.*; 


public class playertank {
	
	//setup the health of tank. 
	public int healthinit=100; //initial health. 
	public int health; 
	
	//position of the tank on the screen 
	public int x; 
	public int y=500; //tank is always on the ground?? except jump? 
	
	//movingspeed 
	public double xspeed; 
	public double yspeed; //used for jump? 
	
	//image for tank 
	public BufferedImage tank; 
	
	//initialize the position of bullet 
	public int xgunontank;
	public int ygunontank; 
	
	//position of gun 
	public int xgun; 
	public int ygun; 
	
	//playertank constructor; 
	public playertank(int x, int y){
		this.x=x; 
		loadimage(); 
		initialize(); 
	}
	
	
	//initialize for all the tank data; 
	public void initialize(){
		this.health=healthinit;
		this.xspeed=0; 
		this.yspeed=-(10*(xspeed-0.89))*(10*(xspeed-0.89))+80; //the data is not accurate 
		//set gun position 
		this.xgunontank=tank.getWidth()-40; //try to changed the position of bullet we shoot!!!!
		this.ygunontank=tank.getHeight(); 
		this.xgun=this.x+this.xgunontank; 
		this.ygun=this.y+this.ygunontank; 
	}
	
	//load the image for tank body. 
	public void loadimage(){
		
	}
	
	
	
	
	

}

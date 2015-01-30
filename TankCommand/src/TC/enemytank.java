package TC;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import java.io.*; 
import java.net.*; 
import java.util.logging.*; //useless
import javax.imageio.*; 


public class enemytank {
	//public int healthinit=20; 
	public int health; 
	
	//position of the enemy 
	public int x;
	public int y=350; 
	
	//moving speed//reandom movement; 
	public double xspeed; 
	public double yspeed; 
	
	//image for enemy tank 
	public BufferedImage enemytank; 
	
	//initialize the position of bullet; 
	public int xgunonenemy; 
	public int ygunonenemy; 
	
	//position of gun on the jpanel; 
	public int xgun;
	public int ygun; 
	
	//enemy tank doesnt jump; 
	public int xacc=4; 
	public int yacc=0; 
	
	//enemytank class constructor 
	public enemytank(int x, int y){
		this.x=x; 
		loadimage(); 
		initialize(); 
	}
	
	//initialize all the enemy tank data 
	public void initialize(){
		//this.health=healthinit; 
		this.xspeed=0; 
		this.yspeed=0; 
		//set the position of gun 
		this.xgunonenemy=enemytank.getWidth()-40; 
		this.ygunonenemy=enemytank.getHeight(); 
		this.xgun=this.x+this.xgunonenemy; 
		this.ygun=this.y+this.ygunonenemy;
	}
	
	//load the image for enemy tank 
	public void loadimage(){
		try{
			URL enemyurl=this.getClass().getResource("whatever???noimage changce later"); 
			enemytank=ImageIO.read(enemyurl); 
		}catch (IOException whattt){
			//do something here for debugging 
		}//no set up for animination 
		
		
	}
	
	
	
	
	
	
	
	
	
	

}

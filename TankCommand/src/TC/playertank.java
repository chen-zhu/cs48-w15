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
	public int y=350; //tank is always on the ground?? except jump? 
	
	//movingspeed 
	public double xspeed; 
	public double yspeed; //used for jump? 
	
	//image for tank 
	public BufferedImage tank; 
	
	//initialize the position of bullet 
	public int xgunontank;
	public int ygunontank; 
	
	//accelerate for jump (gravity)
	public int xacc=4; 
	public int yacc=1; 
	
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
		this.yspeed=0; //-(10*(xspeed-0.89))*(10*(xspeed-0.89))+80; //the data is not accurate yspeed set up for jumping. 
		//set gun position 
		this.xgunontank=tank.getWidth()-40; //try to changed the position of bullet we shoot!!!!
		this.ygunontank=tank.getHeight(); 
		this.xgun=this.x+this.xgunontank; 
		this.ygun=this.y+this.ygunontank; 
	}
	
	//load the image for tank body. 
	public void loadimage(){
		try {
			URL tankurl=this.getClass().getResource("/resources/images/tank.png"); 
			tank=ImageIO.read(tankurl); 
		}catch(IOException ex){
			//do something. 
		}    //no set up for animination 
	}
	
	//reset the position for tank for each round of tank 
	public void reset(int x, int y){
		this.health = healthinit; 
		this.x=x; 
		//this.y=y; y position is always 500, it doesnt change
		this.xgun=this.x+this.xgunontank; 
		this.ygun=this.x+this.ygunontank;
		this.xspeed=0; 
		this.yspeed=0; //jumping initial speed; 
		
	}
	
	
	//set tank shooting and the period between shooting 
	public boolean shotting(long gametime){
		if(drawingpanel.mouseButtonState(MouseEvent.BUTTON1) && ((gametime-bullet.lastcreatbullet)>=bullet.bulletperiod)){
			return true; 
		}
		else 
			return false; 
	}
	
	//show the status of tank, check if player's tank is moving, then setting the speed for jump if there is jumping. 
	public void moving(){                                         //<------------------------------------------------controlling jimping speed. need to fix. 
		if(drawingpanel.keystate(KeyEvent.VK_D) ||drawingpanel.keystate(KeyEvent.VK_RIGHT) ){
			xspeed=xacc; 
		}
		else if (drawingpanel.keystate(KeyEvent.VK_A) ||drawingpanel.keystate(KeyEvent.VK_LEFT) ){
			xspeed=-xacc; 
		}
		
		if(drawingpanel.keystate(KeyEvent.VK_W) ||drawingpanel.keystate(KeyEvent.VK_UP) ){
			yspeed=20; 
			yspeed-=yacc; 
		}
	}
	
	
	//making tank move according to the change of coordinate. 
	public void update(){
		x+=xspeed; 
		y+=yspeed;
		this.xgun=this.x+this.xgunontank; 
		this.ygun=this.y+this.ygunontank; 
	}
	
	//draw the tank in jpanel 
	public void Draw(Graphics2D g2d){
		g2d.drawImage(tank, (int)x, (int)y, null); 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

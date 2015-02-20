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
	public int x=0;
	public int y=350; //tank is always on the ground?? except jump? 
	
	//movingspeed 
	public double xspeed; 
	public double yspeed; //used for jump? 
	//public  int tmp=0; 
	
	//setup for super power. 
	public int numberofsuperpower = 5;
    public int superpowerfinal;

	
	//image for tank 
	public BufferedImage tank, healthbar, healthbar1; 
	
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
		this.superpowerfinal=numberofsuperpower; 
		this.xspeed=0; 
		this.yspeed=0; //-(10*(xspeed-0.89))*(10*(xspeed-0.89))+80; //the data is not accurate yspeed set up for jumping. 
		//set gun position 
		this.xgunontank=tank.getWidth()-40; //try to changed the position of bullet we shoot!!!!
		this.ygunontank=tank.getHeight(); 
		this.xgun=this.x+this.xgunontank; 
		this.ygun=this.y+this.ygunontank;//update the position of gun  
	}
	
	//load the image for tank body. 
	public void loadimage(){
		try {
			URL tankurl=this.getClass().getResource("/TC/resources/images/tank.png"); 
			tank=ImageIO.read(tankurl);
			tankurl=this.getClass().getResource("/TC/resources/images/healthbar5.jpg"); 
			healthbar=ImageIO.read(tankurl);
			tankurl=this.getClass().getResource("/TC/resources/images/healthbar2.png"); 
			healthbar1=ImageIO.read(tankurl);
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
	public boolean shooting(long gametime){
		if(drawingpanel.mouseButtonState(MouseEvent.BUTTON1) && ((gametime-bullet.lastcreatbullet)>=bullet.bulletperiod)){
			return true; 
		}
		else 
			return false; 
	}
	
	public boolean superpowering(long gametime){
		if(drawingpanel.mouseButtonState(MouseEvent.BUTTON3) && superpowerfinal>0 && ((gametime-superpower.lastcreatsuperpower)>=superpower.superpowerperiod)){
			superpowerfinal-=1; 
			return true; 
		}
		else 
			return false; 
	}
	
	//show the status of tank, check if player's tank is moving, then setting the speed for jump if there is jumping. 
	public void moving(){ //<------------------------------------------------controlling jumping speed. need to fix.
        //boundary for tank
		if(y>350){
			y=350;
		}
		if(y<0){
		        y=0;
		}
		if(x<0){
			x=0;
		}
		if(x>900){
			x=900; 
		}
        //tank controls
		if(drawingpanel.keystate(KeyEvent.VK_D) ||drawingpanel.keystate(KeyEvent.VK_RIGHT) ){
			xspeed=xacc; 
		}
		else if (drawingpanel.keystate(KeyEvent.VK_A) ||drawingpanel.keystate(KeyEvent.VK_LEFT) ){
			xspeed=-xacc; 
		}
		else if(!drawingpanel.keystate(KeyEvent.VK_D) ||!drawingpanel.keystate(KeyEvent.VK_RIGHT) ){
			xspeed=0; 
		}
		else if (!drawingpanel.keystate(KeyEvent.VK_A) ||!drawingpanel.keystate(KeyEvent.VK_LEFT) ){ //add condition when key is released
			xspeed=-0; 
		}
		if(drawingpanel.keystate(KeyEvent.VK_W) ||drawingpanel.keystate(KeyEvent.VK_UP) ){
			yspeed=-5; //yspeed-=yacc
			try {
			    URL tankurl=this.getClass().getResource("/TC/resources/images/rocket_tank.png");
			    tank=ImageIO.read(tankurl);
			}catch(IOException ex){
			    //do something.
			}    //no set up for animination
		}
		else if(!drawingpanel.keystate(KeyEvent.VK_W) ||!drawingpanel.keystate(KeyEvent.VK_UP) ){
			yspeed=8;
			if(y<=348){     //============================================>tank will not shake when we dont move it. 
			    try {
				URL tankurl=this.getClass().getResource("/TC/resources/images/tank.png");
				tank=ImageIO.read(tankurl);
			    }catch(IOException ex){                        
				//do something.
			    }    //no set up for animination}
			//yspeed-=yacc; 
			}
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
        int h = healthbar1.getHeight();
        int w = healthbar1.getWidth();
        //System.out.println(w);
        g2d.drawImage(healthbar1, x+27, y+6, (w/4)/100*106, h/4, null); 
		g2d.drawImage(healthbar,x+30, y+10, (w/4)/100*health + 1, h/6 - 1, null); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

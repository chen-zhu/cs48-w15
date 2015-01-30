package TC;

import java.awt.*; 
import java.awt.image.*; 

public class bullet {
	
	//set timeperiod for shooting bullet. 
	public  static long bulletperiod = Framework.nanosecond/10; 
	public static long lastcreatbullet=0; 
	
	//bulletimage 
	public static BufferedImage bullet; 
	
	//set bullet damage 
	public static int damage=20; 
	
	//bulletposition 
	public double x,y; 
	public static int speed=20; 
	public double xspeed; 
	public double yspeed; 
	
	//constructor for bullet 
	public bullet (int x, int y){
		this.x=x; 
		this.y=y; 
		xspeed=speed; 
		yspeed=-(0.08*xspeed-18.7)*(0.08*xspeed-18.7)+350; 
	}
	
	//detect if the bullet left the screen or hit the ground 
	public boolean isleft(){
		if (x>0 && x<Framework.width && y>0 && y<Framework.height)
			return false; 
		else 
			return true; 
	}
	
	
	//making bullet move according to the function in bullet constructor. 
	public void update(){
		x-=xspeed; 
		y-=yspeed; 
	}
	
	//draw the bullet onto the screen 
	public void Draw(Graphics2D g2d){
		g2d.drawImage(bullet, (int)x, (int)y, null); 
	}
	
	

}

package TC;

import java.awt.*; 
import java.awt.image.*; 

public class bullet {
	
	//set timeperiod for shooting bullet. 
	public static long bulletperiod = (long) (Framework.nanosecond/.25); //time between the two bullet <------changed the data to change the frequency of the bullet.
	public static long lastcreatbullet=0; 
		
	//bulletimage 
	public static BufferedImage bullet; 
	
	//set bullet damage 
	public static int damage=20; 
	
	//bulletposition 
	public double x,y; 
	public static double speed=9;
	public double yacc=0.01; 
	public double xspeed; 
	public double yspeed; 
	public  int tmp=0; 
	
	//constructor for bullet 
	public bullet (int x, int y, Point mousePosition){
		this.x=x; 
		this.y=y; //
		xspeed=speed; 
		yspeed=speed; 
		//this.tmp=x; //?

	}
	
	//detect if the bullet left the screen or hit the ground 
	public boolean isleft(){
		if (x>0 && x<Framework.width  && y<Framework.height )//&& y>0) //bullet can in the air(no boundary on the top. )
			return false; 
		else 
			return true; 
	}
	
	
	//making bullet move according to the function in bullet constructor. 
	public void update(){
		x+=xspeed; 
		y-=(28); 
		tmp+=1; 
		y+=tmp; 
		//double tmp1=x-tmp; //?
		System.out.println(tmp+" "+x+" "+y);
		//y=398; 
		//y=(2*(x-tmp1)-685)*(2*(x-tmp1)-685)/800+30; 
		//y-=(tmp/100*tmp/100);                         //parabolic movement. 
		
	}
	
	//draw the bullet onto the screen 
	public void Draw(Graphics2D g2d){
		g2d.drawImage(bullet, (int)x, (int)y, null); 
	}
	
	

}

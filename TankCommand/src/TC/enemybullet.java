package TC;

import java.awt.*; 
import java.awt.image.*; 

public class enemybullet {
	
	public static BufferedImage enemybullet; 
	
	public static int damage=10; 
	
	//position of bullet 
	public double x,y; 
	public static double speed=-4; 
	public double xspeed; 
	public double yspeed; 
	
	//constructors 
	public enemybullet(int x, int y, int xtank, int ytank) {
		this.x=x; 
		this.y=y; 
		xspeed = speed; 
		yspeed = (y-ytank)*speed/(x-xtank); 
		System.out.println(xspeed+" "+yspeed);
	}
	
	public boolean isleft(){
		if (x>0 && x<Framework.width  && y<Framework.height )//&& y>0) //bullet can in the air(no boundary on the top. )
			return false; 
		else 
			return true; 
	}
	
	public void update(){
		x+=xspeed; 
		y+=yspeed;	
	}
	
	
	public void Draw(Graphics2D g2d){
		g2d.drawImage(enemybullet, (int)x, (int)y, null); 
	}
	
	
}

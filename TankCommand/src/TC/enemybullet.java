package TC;

import java.awt.*; 
import java.awt.image.*;

/**
 * An enemy can shoot the player using an enemy bullet.
 * 
 * @author UCSB-CS48-W15-G08
 * @version 3/6/15
 */ 

public class enemybullet {
	//enemy bullet image
	public static BufferedImage enemybullet; 
	
	//The amount damage the bullet can give
	public static int damage=10; 
	
	//position of bullet 
	public double x,y;

	//speed of bullet 
	public static double speed=-4; 
	public double xspeed; 
	public double yspeed; 
	
	/**
	 * Constructor to create an enemy bullet with respect to the enemy.
	 *
	 * @param x initial x position
	 * @param y initial y position
	 * @param xtank player tank's x position
	 * @param ytank player tank's y position
	 */
 
	public enemybullet(int x, int y, int xtank, int ytank) {
		this.x=x; 
		this.y=y; 
		xspeed = speed; 
		yspeed = (y-ytank)*speed/(x-xtank); 
		System.out.println(xspeed+" "+yspeed);
	}
	
	/**
	 * Checks to see if the enemy bullet has left the screen.
	 *
	 * @return a boolean that indicates whether the enemy bullet is still on the screen or not
	 */

	public boolean isleft(){
		if (x>0 && x<Framework.width  && y<Framework.height )//bullet can in the air(no boundary on the top. )
			return false; 
		else 
			return true; 
	}
	
	/**
	 * Makes the enemy bullet move.
	 */

	public void update(){
		x+=xspeed; 
		y+=yspeed;	
	}
	
	/**
	 * Draws enemy bullet.
	 */

	public void Draw(Graphics2D g2d){
		g2d.drawImage(enemybullet, (int)x, (int)y, null); 
	}
	
	
}

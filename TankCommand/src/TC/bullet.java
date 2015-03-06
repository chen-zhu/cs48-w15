package TC;

import java.awt.*; 
import java.awt.image.*;

/**
 * A weapon that the player uses to attack enemies.
 *
 * @author UCSB-CS48-W15-G08
 * @version 3/5/15
 */

public class bullet {
	
	//set timeperiod for shooting bullet. 
	public static long bulletperiod = (long) (Framework.nanosecond/.5); //time between the two bullet <------changed the
	                                                                    // data to change the frequency of the bullet.
	public static long lastcreatbullet=0; 
		
	//bulletimage 
	public static BufferedImage bullet; 
	
	//set bullet damage 
	public static int damage=20; 
	
	//bulletposition 
	public double x,y;
	public static double speed=8;
	public double xspeed; 
	public double yspeed; 
	public  int tmp=0;

    /**
     * Constructor for bullet.
     *
     * @param x initial x-coordinate
     * @param y initial y-coordinate
     */
	public bullet (int x, int y){
		this.x=x; 
		this.y=y;
		xspeed=speed; 
		yspeed=speed;

	}

    /**
     * Detects if the bullet left the screen or hit the ground.
     *
     * @return a boolean indicating whether left the screen or hit the ground
     */
	public boolean isleft(){
		if (x>0 && x<Framework.width  && y<Framework.height ) //bullet can in the air(no boundary on the top. )
			return false; 
		else 
			return true; 
	}


    /**
     * Makes bullet move according to the function in bullet constructor.
     */
	public void update(){
		x+=xspeed; 
		y-=28;
		tmp+=1;
		y+=tmp;
		System.out.println(tmp+" "+x+" "+y);
	}

    /**
     * Draws the bullet onto the screen.
     *
     * @param g2d helps draw bullet on screen so that it makes it look like it's moving
     */
	public void Draw(Graphics2D g2d){
		g2d.drawImage(bullet, (int)x, (int)y, null); 
	}
	
	

}

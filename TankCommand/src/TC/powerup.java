package TC;

import java.awt.*; 
import java.awt.image.*; 
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Defeated enemies will drop powerups at random times
 * and the player can pick these up to gain an extra superpower.
 *
 * @author UCSB-CS48-W15-G08
 * @version 3/5/15
 */
public class powerup {

	public static BufferedImage powerupimg; //image of powerup

	public double x,y; //powerup location

    //power speed because the powerup will be bouncing around when dropped
	public double xspeed = 3; 
	public double yspeed = 7;


    /**
     * Powerup constructor that initializes all powerup components.
     *
     * @param x x position of powerup
     * @param y y position of powerup
     */
	public powerup(int x, int y) {
		this.x=x; 
		this.y=y; 
		
		URL URL=this.getClass().getResource("/TC/resources/images/powerup.png"); 
		try {
			powerupimg=ImageIO.read(URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    /**
     * Checks to see if the powerup left the screen.
     *
     * @return a boolean that indicates whether the powerup left the screen
     */
	public boolean isleft(){
		if (x>0 && x<Framework.width  && y<Framework.height )
			return false; 
		else 
			return true; 
	}

    /**
     * Creates the motion of the powerup.
     */
	public void update(){
		x-=xspeed; 
		if (y>Framework.height-50){
			yspeed=-Math.abs(yspeed); 
		}
		else if(y<50){
			yspeed=Math.abs(yspeed); 
		} 
		y+=yspeed; 
	}

    /**
     * Draws the powerup.
     *
     * @param g2d
     */
	public void Draw(Graphics2D g2d){
		g2d.drawImage(powerupimg, (int)x, (int)y, null); 
	}

}

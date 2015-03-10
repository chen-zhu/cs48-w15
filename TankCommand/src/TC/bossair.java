package TC;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import java.io.*; 
import java.net.*; 
import java.util.Random;
import java.util.logging.*; //useless

import javax.imageio.*; 

/**
 * Class for enemy bosses that move in the air
 *
 * @author UCSB-CS48-W15-G08
 * @version 3/8/15
 */

public class bossair {
    /**
     * Air boss's initial period of appearance.
     */
	public static long periodenemyinit=16*Framework.nanosecond;

    /**
     * Air boss's period of appearance.
     */
	public static long periodenemy=periodenemyinit;

    /**
     * Keeps track of how many air bosses appeared in the game.
     */
	public static long lastcreatedenemy=0;

	private Random random= new Random();
	private int tmp=random.nextInt(300)+600 ;

    /**
     * Air boss's health.
     */
	public int health;

    /**
     * Air boss's x position.
     */
	public int x;

    /**
     * Air boss's y position.
     */
	public int y;

    /**
     * Air boss's initial horizontal speed.
     */
	public static double xmovinginit=-0.05;

    /**
     * Air boss's horizontal speed.
     */
	public static double xmoving=xmovinginit;

    /**
     * Air boss image.
     */
	public static BufferedImage bossairimg; 
	
	/**
	 * Initialize the aerial boss 
	 * @param x x coordinate of initialization
	 * @param y y coordinate of initialization
	 */
	public void initialize(int x, int y){
		health = 200; 
		this.x=x; 
		this.y=y; 
		
		this.xmoving=-0.05; 
	}
	
	/**
	 * Restarts the aerial boss
	 */
	public static void restartenemy(){
		bossair.periodenemy=periodenemyinit;
		bossair.lastcreatedenemy=0; 
		bossair.xmoving=xmovinginit; 
	}
	
	/**
	 * Speeds the aerial boss in case the window scrolls faster than the boss 
	 */
	public static void speedup(){
		if(bossair.periodenemy > Framework.nanosecond){
			bossair.periodenemy-=Framework.nanosecond/10; 
			bossair.xmoving-=0.25; 
		}
		
	}
	
	/** 
	 * Test to see whether boss is left
	 * @return true if the boss is left the screen
	 */
	public boolean isleft(){
		if(x<0 - bossairimg.getWidth())
			return true; 
		else
			return false; 
	}
	
	/**
	 * Uses random number to determine whether boss shoots
	 * @return true if the boss is shooting
	 */
	public boolean shooting(){
		if(x==tmp){
			return true; 
		}
		else 
			return false; 
	}

	
	/**
	 * Function to update the boss movement
	 */
	public void update(){
		Random random = new Random();
		int i =random.nextInt(999); 
		if(i%4==1){
		    x+=(xmoving+0.05); 
		    y+=(xmoving-0.1);}
		if(i%4==2){
			x+=(xmoving+0.05);
			y-=(xmoving-0.1);}
		if(i%4==0){
			x+=(xmoving); 
			//y+=xmoving;
		}
		if(i%4==3){
			x+=(xmoving); 
			//y+=xmoving;
		}
		if(y<40)
			y=40; 
		if(y>400)
			y=400;
		
	}
	
	/** 
	 * Function to draw aerial boss on side-scrolling window
	 * @param g2d to apply graphical methods 
	*/
	public void Draw(Graphics2D g2d){
		g2d.drawImage(bossairimg, x, y, null); 
	}
	
	
	

}

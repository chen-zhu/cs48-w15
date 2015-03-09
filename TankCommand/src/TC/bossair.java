package TC;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import java.io.*; 
import java.net.*; 
import java.util.Random;
import java.util.logging.*; //useless

import javax.imageio.*; 


public class bossair {
	//the time between enemies show 
	public static long periodenemyinit=16*Framework.nanosecond; 
	public static long periodenemy=periodenemyinit; 
	public static long lastcreatedenemy=0; 
	
	//construct a random function for flying 
	public Random random= new Random();  
	private int tmp=random.nextInt(300)+600 ;
	//health of enemy 
	public int health; 
	
	//position of enemy 
	public int x;
	public int y;
	
	//moving speed:
	public static double xmovinginit=-0.05; 
	public static double xmoving=xmovinginit; 
	
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
	 * Not really sure what this does or if we need it for the boss 
	 */
	public static void speedup(){
		if(bossair.periodenemy > Framework.nanosecond){
			bossair.periodenemy-=Framework.nanosecond/10; 
			bossair.xmoving-=0.25; 
		}
		
	}
	
	/** 
	 * Same as last function
	 * @return boolean to see if the boss left the screen
	 */
	public boolean isleft(){
		if(x<0 - bossairimg.getWidth())
			return true; 
		else
			return false; 
	}
	
	/**
	 * 
	 * @param r - not sure
	 * @return whether or not the boss is shooting
	 */
	public boolean shooting(int r){
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
	 * 
	 * @param g2d so GUI methods can be applied to draw the tank onto JPanel
	 */
	public void Draw(Graphics2D g2d){
		g2d.drawImage(bossairimg, x, y, null); 
	}
	
	
	

}

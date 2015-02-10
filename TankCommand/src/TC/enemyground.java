package TC;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class enemyground {
	//the time between enemies show 
	public static long periodgroundinit=8*Framework.nanosecond; 
	public static long periodground=periodgroundinit; 
	public static long lastcreatedground=0; 
	
	//img for enemyground.java
	public static BufferedImage enemygroundimg; 
	
	//health of enemy 
		public int health; 
		
		//position of enemy 
		public int x;
		public int y;
		
		//moving speed:
		public static double xmovinginit=-0.1; 
		public static double xmoving=xmovinginit; 
	
	//update speed up 
	public static void speedup(){
		if(enemyground.periodgroundinit > Framework.nanosecond){
			enemyground.periodground-=Framework.nanosecond/10; 
			enemyground.xmoving-=0.1; 
		}
		
	}
	
	//change the properties of enemy; 
	public static void restartenemyground(){
			enemyground.periodground=periodgroundinit;
			enemyground.lastcreatedground=0; 
			enemyground.xmoving=xmovinginit; 
		}
	
	//override the initialize position 
	public void initialize(int x, int y){
		health = 40; 
		this.x=x; 
		this.y=400; 
		
		//this.xmoving=-1; 
	}

	
	//@override te method in the superclass
	//make enemytank move 
	public void update(){
		/*if (x<300){
			xmoving=0.1; 
		}
		
		if (x>Framework.width){     ----------------------> bug!!! tank wont go back. 
			xmoving=-0.1; 
		} */

		x+=(xmoving);

	}
	
	//check if the grounder left the screen or not
	public boolean isleft(){
		if(x<0 - enemygroundimg.getWidth())
			return true; 
		else
			return false; 
	}

	
	//override the method Draw in the superclass//for the enemy on the ground, the y coordinate doesnt change. 
	public void Draw(Graphics2D g2d){
		g2d.drawImage(enemygroundimg, x, 384, null); 
	}



}

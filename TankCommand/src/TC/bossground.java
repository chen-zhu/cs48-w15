package TC;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class bossground {
	
	//the time between enemies show 
	public static long periodgroundinit=16*Framework.nanosecond; 
	public static long periodground=periodgroundinit; 
	public static long lastcreatedground=0; 
	private Random random= new Random(); 
		
	//img for enemyground.java
	public static BufferedImage bossgroundimg; 
		
	//health of enemy 
	public int health; 
			
	//position of enemy 
	public int x;
	public int y;
	private int tmp=random.nextInt(300)+600; 
			
	//moving speed:
	public static double xmovinginit=-0.05; 
	public static double xmoving=xmovinginit; 
		
	//update speed up 
	public static void speedup(){
		if(bossground.periodgroundinit > Framework.nanosecond){
				bossground.periodground-=Framework.nanosecond/18; 
				bossground.xmoving-=0.05; 
		}
			
	}
		
		//change the properties of enemy; 
	public static void restartbossground(){
				bossground.periodground=periodgroundinit;
				bossground.lastcreatedground=0; 
				bossground.xmoving=xmovinginit; 
	}
		
		//override the initialize position 
	public void initialize(int x, int y){
			health = 200; 
			this.x=x; 
			this.y=400; 
			this.xmoving=-1; 
			//this.tmp=random.nextInt(200)+700; 
	}

		
		//@override the method in the superclass
		//make bossground move 
	public void update(){
			if (x<300){
				xmoving=0.1; 
			}
			if (x>900){     
				xmoving=-0.1; 
			} 

			x+=xmoving;

	}
		
	/*check if the grounder left the screen or not
	public boolean isleft(){
			if(x<0 - bossgroundimg.getWidth()) <-------------is this needed?
				return true; 
			else
				return false; 
	}
	*/	
		//check it the enemy is shooting or not. 
	public boolean shooting(int r){
			if(x==tmp){
				return true; 
			}
			else 
				return false; 
	}

		
		//override the method Draw in the superclass//for the enemy on the ground, the y coordinate doesnt change. 
	public void Draw(Graphics2D g2d){
			g2d.drawImage(bossgroundimg, x, 389, null); 
	}



	}


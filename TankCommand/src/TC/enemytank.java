package TC;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 
import java.io.*; 
import java.net.*; 
import java.util.Random;
import java.util.logging.*; //useless

import javax.imageio.*; 


public class enemytank {
	//the time between enemies show 
	public static long periodenemyinit=5*Framework.nanosecond; 
	public static long periodenemy=periodenemyinit; 
	public static long lastcreatedenemy=0; 
	
	//construct a random function for flying 
	public Random random;  
	
	//health of enemy 
	public int health; 
	
	//position of enemy 
	public int x;
	public int y;
	
	//moving speed:
	public static double xmovinginit=-4; 
	public static double xmoving=xmovinginit; 
	
	public static BufferedImage enemytankimg; 
	
	//initialize theenemy 
	public void initialize(int x, int y){
		health = 40; 
		this.x=x; 
		this.y=y; 
		
		this.xmoving=-1; 
	}
	
	//change the properties of enemy; 
	public static void restartenemy(){
		enemytank.periodenemy=periodenemyinit;
		enemytank.lastcreatedenemy=0; 
		enemytank.xmoving=xmovinginit; 
	}
	
	
	//modify the time, frequency and the difficulty of enemy 
	public static void speedup(){
		if(enemytank.periodenemy > Framework.nanosecond){
			enemytank.periodenemy-=Framework.nanosecond/10; 
			enemytank.xmoving-=0.25; 
		}
		
	}
	
	//check if the enemy left the screen or not; 
	public boolean isleft(){
		if(x<0 - enemytankimg.getWidth())
			return true; 
		else
			return false; 
	}
	
	
	
	//make enemytank move 
	public void update(){
		Random random = new Random();
		int i =random.nextInt(999); 
		if(i%4==1){
		    x+=(xmoving+1); 
		    y+=(xmoving-2);}
		if(i%4==2){
			x+=(xmoving+1);
			y-=(xmoving-2);}
		if(i%4==0){
			x+=(xmoving); 
			//y+=xmoving;
		}
		if(i%4==3){
			x+=(xmoving); 
			//y+=xmoving;
		}
		//if(i==3){
			//x+=(xmoving+1); 
			//y+=xmoving;
		//}
/*		if(i==0)
			y+=xmoving;
		if(i==4)
			y-=xmoving;
		if(i==5)
			y-=xmoving;    */
		if(y<40)
			y=40; 
		if(y>400)
			y=400;
		
	}
	
	//draw the enemy onto jpanel 
	public void Draw(Graphics2D g2d){
		g2d.drawImage(enemytankimg, x, y, null); 
	}
	
	
	

}

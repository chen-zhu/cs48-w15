package TC;

import java.awt.*; 
import java.awt.image.*; 
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class powerup {
	public static BufferedImage powerupimg; 
	public double x,y; 
	public double xspeed = 3; 
	public double yspeed = 7; 
	
	
	//initialize everything 
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
	
	public boolean isleft(){
		if (x>0 && x<Framework.width  && y<Framework.height )
			return false; 
		else 
			return true; 
	}
	
	
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
	
	public void Draw(Graphics2D g2d){
		g2d.drawImage(powerupimg, (int)x, (int)y, null); 
	}

}

package TC;

import java.awt.Graphics2D; 
import java.awt.image.*; 


public class background {
	
	public BufferedImage background; 
	
	//speed of rolling 
	public double speed; 
	
	//position 
	public double x[]; 
	public int y; 
	
	//initialize the content 
	
	public void initialize(BufferedImage i, double a, int b){
		this.background = i; 
		this.speed = a; 
		this.y=b; 
		//to calc how many times should we put image into the frame
		int number=(Framework.width/this.background.getWidth())+2; //in case of empty space +2
		x=new double[number]; 
		//set x to draw n times 
		for (int f = 0; f<x.length; f++){
			x[f]=f*background.getWidth(); 
		}
	}
	
	//make image move, change coordinate move 
	private void update(){
		for (int i = 0; i<x.length; i++){
			
			//change picture coordinate by adding speed
			x[i]+=speed; 
			if (speed < 0){//move left
				//if move out of screen, it move to the other end of image. 
				if(x[i]<=-background.getWidth()){
					x[i]=background.getWidth()*(x.length-1); 
				}
			}
			else //move right
			{
				if(x[i]>=background.getWidth()*(x.length-1)){
					x[i]=-background.getWidth(); 
			}}
			
		}
	}
	
	//update all the pictures' coordinate. 
	public void Draw(Graphics2D g2d){
		this.update(); 
		for (int i = 0; i<x.length; i++){
			g2d.drawImage(background,  (int)x[i], y, null); 
		}
	}
	
	

}

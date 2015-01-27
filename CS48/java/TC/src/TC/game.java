package TC;

import java.awt.*; 
import java.awt.geom.*; 
import java.awt.image.*; 
import java.io.*; 
import java.net.*; 
import java.util.*; 
import java.util.logging.*; 

import javax.imageio.*; 

public class game {
	public BufferedImage cloud; 
	public BufferedImage desert; 
	public background cloudmoving; 
	public background desertmoving; 
	public Random random; 
	public Robot robot; 
	
	//set down everything for the game
	private void initialize(){
		random = new Random(); 
		try{
			robot = new Robot(); 
		}catch (AWTException e){
			Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, e);
		}
		cloudmoving = new background(); 
		desertmoving = new background(); 
		//font = new Font("what wtahttttttt", Font.BOLD, 18);<<<<<<<<<<<<---------------------------------------
	}
	
	//Assign images to variables. 
	private void load(){
		try {
			URL cURL=this.getClass().getResource("cloud_layer_1.png"); 
			cloud=ImageIO.read(cURL);
			URL dURL=this.getClass().getResource("desert.png"); 
			desert=ImageIO.read(dURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, e);
		} 
		
		
		//initialize moving 
		desertmoving.initialize(desert, -6, 0);//---------------------------change data to modify background position
		cloudmoving.initialize(cloud, -2, 0);
	}
	
	
	//draw the picture onto the screen 
	public void Draw(Graphics2D g2d, Point mouseposition, long gametime){
		desertmoving.Draw(g2d);
		cloudmoving.Draw(g2d);
		//g2d.drawString("............", 20,41); 
		
		
	}
	
	public game(){
		Framework.gamestate=Framework.GameState.gameloading; 
		Thread threadForInitGame=new Thread(){
			@Override 
			public void run(){
				initialize(); 
				load(); 
				Framework.gamestate=Framework.GameState.playing;
			}
		};
		threadForInitGame.start(); 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}

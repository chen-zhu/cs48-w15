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
	public playertank player; 
	public ArrayList<bullet> bulletlist; //the arraylist for bullets 
	
	//set down everything for the game
	private void initialize(){
		random = new Random(); 
		try{
			robot = new Robot(); 
		}catch (AWTException e){
			//Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, e);
		}
		
		//making background moving 
		cloudmoving = new background(); 
		desertmoving = new background(); 
		
		player=new playertank(Framework.width/10, Framework.height/4); //set the initial position for player tank 
		bulletlist=new ArrayList<bullet>(); //set up the bullet for tank 
		

		//font = new Font("what wtahttttttt", Font.BOLD, 18);<<<<<<<<<<<<---------------------------------------
	}
	
	//Assign images to variables. 
	private void load(){
		try {
			URL cURL=this.getClass().getResource("/TC/resources/images/cloud_layer_1.png"); 
			cloud=ImageIO.read(cURL);
			URL dURL=this.getClass().getResource("/TC/resources/images/desert.png"); 
			desert=ImageIO.read(dURL);
			URL bulletURL=this.getClass().getResource("/TC/resources/images/bullet.png"); 
			bullet.bullet=ImageIO.read(bulletURL);     //read image for bullet 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, e);
			//do whatever something here
		} 
		
		//initialize moving 
		desertmoving.initialize(desert, -2, 0);//---------------------------change data to modify background position
		cloudmoving.initialize(cloud, -2, 0);
	}
	
	//check if the player is alive; 
	public boolean isplayeralive(){
		if (player.health<=0){
			return false; 
		}
		else 
			return true; 
	}
	
	//check if the player is shooting; 
	public void isplayershooting(long gametime, Point mouseposition){
		if(player.shooting(gametime)){
			bullet.lastcreatbullet=gametime; 
			bullet bullet=new bullet(player.xgun-120, player.ygun-50, mouseposition); 
			bulletlist.add(bullet); 
		}
	}
	
	//make bullet move 
	public void updatebullet(){
		for(int i=0; i<bulletlist.size(); i++){
			bullet bullet = bulletlist.get(i); 
			bullet.update();
			//check if bullet left screen 
			if(bullet.isleft()){
				bulletlist.remove(i);
				continue; 
			}
			//check if the bullet hit the enemy; 
			
		}
	}
	
	//draw the picture onto the screen 
	public void Draw(Graphics2D g2d, Point mouseposition, long gametime){
		desertmoving.Draw(g2d);
		cloudmoving.Draw(g2d);

		//draw player 
		if(isplayeralive())
			player.Draw(g2d);
		
		//draw arraylist for bullet 
		for(int i=0; i<bulletlist.size(); i++){
			bulletlist.get(i).Draw(g2d);
		}
		
	}
	
	
	//update the logic of the game//keep game check the whole logic. 
	public void updategame(long gametime, Point mouseposition){
		
		//player is alive, the keep update. 
		if(isplayeralive()){
			isplayershooting(gametime, mouseposition); 
			player.moving();
			player.update();
		}
		
		//update bullet action
		updatebullet(); 
		
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

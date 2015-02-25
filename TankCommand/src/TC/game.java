package TC;

import java.awt.*; 
import java.awt.geom.*; 
import java.awt.image.*; 
import java.io.*; 
import java.net.*; 
import java.util.*; 
import java.util.logging.*; 

import javax.imageio.*; 

import java.applet.Applet;
import java.applet.AudioClip;

public class game {
	static Thread currThread; 
	public BufferedImage cloud; 
	public BufferedImage desert; 
	public background cloudmoving; 
	public background desertmoving; 
	private Random random= new Random(); 
	public Robot robot; 
	public playertank player; 
	public ArrayList<bullet> bulletlist; //the arraylist for bullets 
	public ArrayList<superpower> superpowerlist; //the arraylist for superpower 
	public ArrayList<enemytank> enemylist=new ArrayList<enemytank>(); 
	public ArrayList<enemyground> groundlist=new ArrayList<enemyground>(); 
	public ArrayList<enemybullet> enemybulletlist=new ArrayList<enemybullet>(); //the arraylist for bullet given by enemy 
	public int runaway; 
	public int killed; 
	//public int r = random.nextInt(400)+500; 
	AudioClip explode, attack, rocket, crash; 
	
	static Thread threadForInitGame;
	
	
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
		enemylist=new ArrayList<enemytank>(); 
		groundlist=new ArrayList<enemyground>(); 
		superpowerlist=new ArrayList<superpower>(); 
		enemybulletlist=new ArrayList<enemybullet>();
		
		runaway=0; 
		killed=0; 
		Framework.clip.loop();
		//font = new Font("what wtahttttttt", Font.BOLD, 18);<<<<<<<<<<<<---------------------------------------
	}
	
	//Assign images to variables. 
	private void load(){
		try {
			//music
			URL musicURL=this.getClass().getResource("/TC/resources/sound/explode.wav");
			explode=Applet.newAudioClip(musicURL);
			URL music1URL=this.getClass().getResource("/TC/resources/sound/tank.wav");
			attack=Applet.newAudioClip(music1URL);
			music1URL=this.getClass().getResource("/TC/resources/sound/rocket.wav");
			rocket=Applet.newAudioClip(music1URL);
			music1URL=this.getClass().getResource("/TC/resources/sound/crash.wav");
			crash=Applet.newAudioClip(music1URL);
			
			//pics
			URL cURL=this.getClass().getResource("/TC/resources/images/cloud_layer_1.png"); 
			cloud=ImageIO.read(cURL);
			URL dURL=this.getClass().getResource("/TC/resources/images/desert.png"); 
			desert=ImageIO.read(dURL);
			URL bulletURL=this.getClass().getResource("/TC/resources/images/bullet.png"); 
			bullet.bullet=ImageIO.read(bulletURL);     //read image for bullet 
			URL enemybulletURL=this.getClass().getResource("/TC/resources/images/enemybullet.png"); 
			enemybullet.enemybullet=ImageIO.read(enemybulletURL);     //read image for enemybullet
			URL pURL=this.getClass().getResource("/TC/resources/images/superpower.png"); 
			superpower.superpower=ImageIO.read(pURL);     //read image for bullet 
			URL enemytankURL=this.getClass().getResource("/TC/resources/images/enemy_plane.jpg"); 
			enemytank.enemytankimg=ImageIO.read(enemytankURL); //read image for enemy 
			URL enemygroundURL=this.getClass().getResource("/TC/resources/images/enemyground.png"); 
			enemyground.enemygroundimg=ImageIO.read(enemygroundURL); //read image for enemyground
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
			Framework.clip.stop(); 
			explode.play(); 
			return false; 
		}
		else 
			//attack.play();
			return true; 
	}
	
	//check if the player is shooting; 
	public void isplayershooting(long gametime, Point mouseposition){
		if(player.shooting(gametime)){
			attack.play();
			bullet.lastcreatbullet=gametime; 
			bullet bullet=new bullet(player.xgun-120, player.ygun-50, mouseposition); 
			bulletlist.add(bullet);
			 
		}
	}
	
	//check if the enemy is shooting 
	public void isgroundenemyshooting(){
		for (int i =0; i<groundlist.size(); i++){
			if(groundlist.get(i).shooting(random.nextInt(400)+500)){
				enemybullet enb=new enemybullet(groundlist.get(i).x, groundlist.get(i).y, player.x, player.y+50); 
				enemybulletlist.add(enb); 
			}
		}
	}
	
	//check if the plane is shooting 
	public void isenemyshooting(){
		for (int i =0; i<enemylist.size(); i++){
			if(enemylist.get(i).shooting(random.nextInt(400)+500)){
				enemybullet enb=new enemybullet(enemylist.get(i).x, enemylist.get(i).y, player.x, player.y+50); 
				enemybulletlist.add(enb); 
			}
		}
	}
	
	public void isplayerusingsuperpower(long gametime, Point mouseposition){
		if(player.superpowering(gametime)){
			rocket.play(); 
			superpower.lastcreatsuperpower=gametime; 
			superpower s=new superpower(); 
			superpowerlist.add(s); 
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
			Rectangle b=new Rectangle((int)bullet.x, (int)bullet.y,bullet.bullet.getWidth(), bullet.bullet.getHeight()); 
			//then use for loop to check enemylist, if there is any enemy got hit or not? 
			for (int t=0; t<enemylist.size(); t++){
				enemytank r=enemylist.get(t); 
				Rectangle e=new Rectangle(r.x, r.y,r.enemytankimg.getWidth(), r.enemytankimg.getHeight()); 
				if (b.intersects(e)){
					r.health-=bullet.damage; 
				}
			}
			for (int t=0; t<groundlist.size(); t++){
				enemyground rr=groundlist.get(t); 
				Rectangle f=new Rectangle(rr.x, rr.y,rr.enemygroundimg.getWidth(), rr.enemygroundimg.getHeight()); 
				if (b.intersects(f)){
					rr.health-=bullet.damage; 
				}
			} 

			
		}
	}

	//make enemybullet move
	public void updateenemybullet(){
		for(int i=0; i<enemybulletlist.size(); i++){
			enemybullet enemybullet = enemybulletlist.get(i); 
			enemybullet.update();
			//check if bullet left screen 
			if(enemybullet.isleft()){
				enemybulletlist.remove(i);
				continue; 
			}
			//check if the bullet hit the enemy; 
			Rectangle b=new Rectangle((int)enemybullet.x, (int)enemybullet.y,enemybullet.enemybullet.getWidth(), enemybullet.enemybullet.getHeight()); 
			Rectangle p=new Rectangle(player.x, player.y+50,player.tank.getWidth()/2, player.tank.getHeight()/2); 
			if(p.intersects(b)){
				crash.play();
				player.health-=enemybullet.damage; 
				enemybulletlist.remove(i);    //<============================================================Doing collision. 
			}
		} 

			
	}

	//make superpower move 
	public void updatesuperpower(){
		for(int i=0; i<superpowerlist.size(); i++){
			superpower s = superpowerlist.get(i); 
			s.update();
			//check if bullet left screen 
			if(s.isleft()){
				superpowerlist.remove(i);
				continue; 
			}
			//check if the bullet hit the enemy; 
			Rectangle b=new Rectangle((int)s.x, (int)s.y,superpower.superpower.getWidth(), superpower.superpower.getHeight()); 
			//then use for loop to check enemylist, if there is any enemy got hit or not? 
			for (int t=0; t<enemylist.size(); t++){
				enemytank r=enemylist.get(t); 
				Rectangle e=new Rectangle(r.x, r.y,r.enemytankimg.getWidth(), r.enemytankimg.getHeight()); 

				if (b.intersects(e)){
					r.health-=s.superdamage; 
				}
			}
			
			for (int t=0; t<groundlist.size(); t++){
				enemyground rr=groundlist.get(t); 
				Rectangle f=new Rectangle(rr.x, rr.y,rr.enemygroundimg.getWidth(), rr.enemygroundimg.getHeight()); 
				if (b.intersects(f)){
					rr.health-=bullet.damage; 
				}
			}


			
		}
	}
	
	//draw the picture onto the screen 
	public void Draw(Graphics2D g2d, Point mouseposition, long gametime){
		desertmoving.Draw(g2d);
		cloudmoving.Draw(g2d);

		//draw player 
		if(isplayeralive())
			player.Draw(g2d);
		
		//draw enemytank 
		for (int i=0; i<enemylist.size(); i++){
			enemylist.get(i).Draw(g2d);
		}
		
		//draw enemyground
		for (int i=0; i<groundlist.size(); i++){
			groundlist.get(i).Draw(g2d);
		}
		
		//draw arraylist for bullet 
		for(int i=0; i<bulletlist.size(); i++){
			bulletlist.get(i).Draw(g2d);
		}
		
		//draw arraylist for enemybullet 
		for(int i=0; i<enemybulletlist.size(); i++){
			enemybulletlist.get(i).Draw(g2d);
		}
		
		//draw superpower 
		for(int i=0; i<superpowerlist.size(); i++){
			superpowerlist.get(i).Draw(g2d);
		}
		
		g2d.setFont(new Font("what wtahttttttt", Font.BOLD, 18));
		g2d.setColor(Color.gray );
		g2d.drawString("Killed: "+killed, 10, 20);
		g2d.drawString("Rocket: "+player.superpowerfinal, 10, 40);
		g2d.drawString("Run away: "+runaway, 250, 20);
		g2d.drawString("bullet period: "+bullet.bulletperiod/1000000000+"s", 250, 40);
		if(player.health<0){
			player.health=0;
		}
		g2d.drawString("Player's health: "+player.health, 560, 20);
		
	}
	
	
	//update the logic of the game//keep game check the whole logic. 
	@SuppressWarnings("deprecation")
	public void updategame(long gametime, Point mouseposition){
		
		//restart the game if the player is dead. 
		if(!isplayeralive()){
			Framework.gamestate=Framework.gamestate.gameover; 
			return; //stop the game
		}
		
		//player is alive, the keep update. 
		if(isplayeralive()){
			isplayershooting(gametime, mouseposition); 
			isplayerusingsuperpower(gametime, mouseposition);
			player.moving();
			player.update();
		}
		
		//go through all the groundenemy 
		isgroundenemyshooting();
		isenemyshooting(); 
		//update bullet action
		updatebullet(); 
		updatesuperpower(); 
		updateenemybullet();
		//update the enemy 
		createenemytank(gametime); 
		updateenemy(); 
		//update the enemyground 
		createenemyground(gametime); 
		updateenemyground();
		currThread=Thread.currentThread();
		if(Framework.pause == true){
		try {
			Thread.currentThread().sleep(10000);
			Framework.pause = false; 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		/*if(Framework.pause == false && Thread.currentThread().getState()==Thread.State.TIMED_WAITING){
			Thread.currentThread().interrupt();
		}*/
		//System.out.println(Thread.currentThread().getName()+" "+Thread.currentThread().getState()); 
	}
	
	
	//create the enemyground when it comes to the right time 
	public void createenemyground(long gametime){
		if (gametime-enemyground.lastcreatedground>=enemyground.periodground){
			enemyground r = new enemyground(); 
			r.initialize(Framework.width, 400);
			//System.out.println("1");
			groundlist.add(r); 
			//System.out.println("2");
			enemyground.speedup();
			enemyground.lastcreatedground=gametime; 
		}
	}
	
	//update all the ground enemy in the list(making the move and remove from the list if they are not exist 
	public void updateenemyground(){
		for (int i=0; i<groundlist.size(); i++){
			enemyground r = groundlist.get(i); 
			r.update(); 
			Rectangle p=new Rectangle(player.x, player.y+50,player.tank.getWidth()/2, player.tank.getHeight()/2); 
			Rectangle e=new Rectangle(r.x, r.y,r.enemygroundimg.getWidth(), r.enemygroundimg.getHeight()); 
			if(p.intersects(e)){
				crash.play();
				player.health-=30; 
				groundlist.remove(i);   
			}
			if(r.health<=0){
				//attack.play();
				explode.play(); 
				groundlist.remove(i); 
				killed+=1;
				continue; 
			}
			if(r.isleft()){
				groundlist.remove(i); 
				runaway+=1; }
			
		}
	}
	
	//creates the enemy when it comes to the right time 
	public void createenemytank(long gametime){
		if(gametime-enemytank.lastcreatedenemy>=enemytank.periodenemy){
			enemytank r=new enemytank(); 
			random = new Random(); 
			int x=Framework.width; 
			int y=random.nextInt(Framework.height-enemytank.enemytankimg.getHeight()-120); 
			r.initialize(x, y);
			//System.out.println("1");
			enemylist.add(r); 
			enemytank.speedup();
			//update the last created time!!!
			enemytank.lastcreatedenemy=gametime; 
		}
	}
	
	//update all the enemy in the list 
	private void updateenemy(){
		for(int i=0; i<enemylist.size(); i++){
			enemytank r=enemylist.get(i); 
			r.update(); 
			//is crashed or not???
			//enemy die after crashing with player 
			Rectangle p=new Rectangle(player.x, player.y+50,player.tank.getWidth()/2, player.tank.getHeight()/2); 
			Rectangle e=new Rectangle(r.x, r.y,r.enemytankimg.getWidth(), r.enemytankimg.getHeight()); 
			if(p.intersects(e)){
				crash.play();
				player.health-=30; 
				enemylist.remove(i);    //<============================================================Doing collision. 
			}
			if(r.health<=0){
				explode.play(); 
				enemylist.remove(i); 
				killed+=1;
				continue; 
			}
			if(r.isleft()){
				enemylist.remove(i); 
				runaway+=1; 
				//if runaway, remove from list 
			}
		}
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
		//System.out.println("thread"); 
	}
	
	
	//set restart game for gameover 
	public void restartgame(){
		player.reset(0,450); 
		enemytank.restartenemy();
		enemyground.restartenemyground();
		runaway=0; 
		killed=0; 
		superpower.lastcreatsuperpower=0; 
		bullet.lastcreatbullet=0; 
		enemylist.clear(); 
		bulletlist.clear(); 
		enemybulletlist.clear(); 
		superpowerlist.clear(); 
		groundlist.clear(); 
	}
	
	public void print(Graphics2D g2d, long gametime){
		g2d.drawString("time: "+gametime/1000000000+"s", 450, 530/2-30); 
		g2d.drawString("you've killed: "+killed+" enemies", 450, 530/2+20); 
		g2d.drawString("run away: "+runaway, 450, 530/2+70); 
		
	}
	

}

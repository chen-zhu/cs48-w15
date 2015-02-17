package TC;

import java.awt.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException; 
import java.net.URL; 
import java.util.logging.*; 

import javax.imageio.ImageIO; 

//the main class that provide the function for the whole game. 

public class Framework extends drawingpanel{
	public static int width; 
	public static int height; 
	//nanoseconds; 
	public static long nanosecond=1000000000L; 
	//one millisecond in nanoseconds 
	public static long milisecond=1000000L; 
	
	//the frame should update 60 times per second in order to be smooth. 
	private int fps=60; 
	
	//period during two updates:
	private long period = nanosecond/fps; 
	
	public static enum GameState{starting, gameloading, main_menu, options, playing, gameover, destroyed, visualizing} 
	
	//current state of game:
	public static GameState gamestate; 
	
	//game running time; 
	public long gametime; 
	public long lasttime; 
	
	private game game; 
	private Font font; 
	
	//Image for starting menu. 
	private BufferedImage b1; 
	private BufferedImage title; 
	private BufferedImage menuborder; 
	private BufferedImage cloud; 
	private BufferedImage black; 	
	
	//update, showing the position of mouse
	private Point mouseposition(){
		try {
			Point a=this.getMousePosition(); 
			if(a != null)
				return this.getMousePosition(); 
			else 
				return new Point(0, 0); 
		}catch (Exception e){
			return new Point(0,0); 
		}
	}
	
	
	//start a new game 
	private void newgame(){
		gametime = 0; 
		lasttime=System.nanoTime();
		game = new game(); 
	}
	
	//restart a new game 
	private void restartgame(){
		gametime = 0; 
		lasttime=System.nanoTime();
		game.restartgame(); //<--------------------------------------------------------
		gamestate=GameState.playing; //changing the state of the game 
	}
	
	
	//initialize the variables and objects. 
	
	private void initialize(){
		font = new Font ("Tank Command", Font.ITALIC, 28); 
	}
	
	//load images for starting menu 
	
	private void load(){
		try {
			URL burl=this.getClass().getResource("/TC/resources/images/desert.png");
			b1 = ImageIO.read(burl); 
			
			URL turl=this.getClass().getResource("/TC/resources/images/title.png");
			title = ImageIO.read(turl); 
			
			URL murl=this.getClass().getResource("/TC/resources/images/menu_border.png");
			menuborder = ImageIO.read(murl); 
			
			URL curl=this.getClass().getResource("/TC/resources/images/cloud_layer_1.png");
			cloud = ImageIO.read(curl); 
			
			URL blackurl=this.getClass().getResource("/TC/resources/images/black.jpg");
			black = ImageIO.read(blackurl);
		}
		catch (IOException ex){
			//System.err.println("............"); 
			Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
		}		
	}
	
	
	
	//making different action by checking gamestate
	private void loop(){
		
		//need time to load the correct frame and window content. 
		long visualizingtime=0, lastvisualizingtime=System.nanoTime(); 
		
		//calc for how long we need to put threat to sleep to match with fps. 
		long begintime, timetaken, timeleft; 
		
		while(true){
			begintime = System.nanoTime(); 
			
			switch (gamestate){
			case playing : 
				gametime +=System.nanoTime() - lasttime; 
				game.updategame(gametime, mouseposition()); //<------------------------------------------
				lasttime = System.nanoTime(); 
				break; 
				
			case gameover:
				break; 
			case main_menu:
				break; 
			case options: 
				break; 
			case gameloading: 
				break; 
			case starting:
				initialize(); 
				load(); 
				//
				gamestate=GameState.main_menu; 
				break; 
			case visualizing: 
				if(this.getWidth()>1 && visualizingtime > nanosecond){
					width = this.getWidth(); 
					height=this.getHeight(); 
					
					gamestate=GameState.starting; 
				}
				else 
				{
					visualizingtime += System.nanoTime() - lastvisualizingtime; 
					lastvisualizingtime = System.nanoTime(); 
				}
				
				break; 
			}
			
			//update 
			repaint(); 
			
			//calc how long should we put thread into sleep to satisfy fps; 
			timetaken = System.nanoTime() - begintime; 
			timeleft=(period - timetaken)/milisecond; 
			if (timeleft<10){
				timeleft=10; 
			}
			try {
				Thread.sleep(timeleft); 
			} catch (InterruptedException ex){
			}
			
		}
		
	}
	
	
	
	//draw the begining menu 
	private void drawmenu(Graphics2D g2d){
		g2d.setBackground(Color.green);
		g2d.drawImage(b1, 0, 0, width, height, null); 
		g2d.drawImage(cloud, 0, 0, width, height, null); 
		g2d.drawImage(menuborder, 0, 0, width, height, null); 
		g2d.setColor(Color.WHITE);
		g2d.drawString("UCSB -CS48 -G08", 15, height-10);
	}
	
	
	
	//draw onto the screen. 
	@Override
	public void Draw(Graphics2D g2d){
		switch (gamestate){
			case playing: 
				game.Draw(g2d, mouseposition(), gametime); 
				break; 
			case gameover: 
				drawmenu(g2d); 
				g2d.setColor(Color.GRAY);
				g2d.drawString("Press Enter to restart or ESC to exit." , width/2-97, height/4+30);
				game.print(g2d, gametime); //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<important!!!!
				g2d.setFont(font);
				g2d.drawString("Game over", width/2-70, height/4);
				break; 
			case main_menu: 
				drawmenu(g2d); 
				g2d.drawImage(title, width/2-title.getWidth()/2, height/4, null); 
				g2d.setColor(Color.GRAY); 
				g2d.drawString("Use A, W, D to move the tank. Press left mouse button to fire bullet and right mouse button to use superpower." , width/2-310,  height/2);
				g2d.setFont(new Font("whatevereverever", Font.BOLD, 20));
				g2d.drawString("Press Enter key to start the game, or ESC to exit.", width/2-250, height/2+30);
				break; 
			case options:
				//......
				break; 
			case gameloading: 
				g2d.drawImage(black, 0, 0,null); 
				g2d.setColor(Color.white); 
				g2d.setFont(new Font("what wtahttttttt", Font.ITALIC, 20));
				g2d.drawString("Game is Loading!", 800/2, 530/2); 
				break; 
				
				//case select:              //adding control for level selecting. 
				//g2d.drawImage(select_level, 0, 0 , null); //loadcontent for level select. 
				//g2d.setColor(Color.white); 
				//g2d.serFont(new Font("whatever you gonna do", Font.ITALIC,20)); 
				//g2d.drawString("please input the level you want:", 800/2, 530/2); 
				//g2d.drawString("press anykey to exit to main menu!", 800/2, 530/2+50); //add button to enter level and bring user to that level. gotta do it next week. 

		}
	}
	

	


	@Override
	public void keyReleasedFramework(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		switch (gamestate){
		case gameover: 
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
				restartgame(); 
			break; 
		case main_menu: 
			if(e.getKeyCode() == KeyEvent.VK_ENTER) //delete? //press enter key to start game!!!!!!!!!!!!
			newgame(); 
			break; 
		}
		
		
	}

	
	
	public Framework(){
		super(); 
		gamestate = GameState.visualizing; 
		
		//construct new thread. runing the thread on the same time. 
		Thread gameThread = new Thread(){
			@Override 
			public void run(){
				loop(); 
			}}; 
			gameThread.start(); 
	}
	
	
	
	
	
	
	
}

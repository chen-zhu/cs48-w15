package TC;

import java.awt.*; 
import java.awt.event.*; 
import java.awt.image.*; 

import javax.swing.JPanel; 

//it is JPanel, we can draw on it and put it inside the window. //add keylistener. 

public abstract class drawingpanel extends JPanel implements KeyListener, MouseListener {
	private static boolean[] keystate=new boolean[525]; //record the state of keys
	private static boolean[] mousestate=new boolean[3];
	public drawingpanel(){
		//using double buffer to draw on screen 
		this.setDoubleBuffered(true);
		this.setFocusable(true); 
		this.setBackground(Color.black); 
		//remove the mouse cursor 
		if(true){
			BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB); 
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), null); 
			this.setCursor(blankCursor);
		}
		this.addKeyListener(this); 
		this.addMouseListener(this); 
	}
	
	//overroden in framework 
	//used to draw to the screem. 
	public abstract void Draw(Graphics2D g2d); 
	
	@Override 
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g; 
		Draw(g2d); 
	}
	
	//check if the user presses any key or not 
	public static boolean keystate(int key){
		return keystate[key];
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		keystate[e.getKeyCode()] = true; 
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		keystate[e.getKeyCode()] = false;
		keyReleasedFramework(e);
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		//keystate[e.getKeyCode()] = true; 
	}

	public abstract void keyReleasedFramework(KeyEvent e);
	
	
	//check if mouse click or not 
	public static boolean mouseButtonState(int button){
		return mousestate[button - 1]; 
	}
	
	private void mouseKeyStatus(MouseEvent e, boolean status ){
		if(e.getButton()==MouseEvent.BUTTON1)
			mousestate[0]=status; 
		else if (e.getButton()==MouseEvent.BUTTON2)
			mousestate[1]=status; 
		else if(e.getButton() == MouseEvent.BUTTON3)
			mousestate[2]=status; 
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		mouseKeyStatus(e, true); 
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		mouseKeyStatus(e, false); 
	}
	
	@Override
	public void mouseClicked(MouseEvent e){}
	
	@Override
	public void mouseEntered(MouseEvent e){}
	
	@Override
	public void mouseExited(MouseEvent e){}
	
	
}

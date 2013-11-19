package UI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.*;

import GameCore.Control;
import GameCore.GameLogic;
import GameCore.Map;
import GameCore.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;


/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */
	public class GamePanel extends Canvas implements KeyListener, Runnable {
		boolean suspendflag;
		private static GameLogic gameLogic;
		public static int size = 512;
		public static int width = size;
		public static int height = size;
		private static final long serialVersionUID = 1L;
		private static final int BUFFER = 3;
		private static final int numberOfTiles = 128;
		//this determines the size of each square in pixel, 2 = 4x4 , 3 = 8x8 , 4= 16x16

	
		
		long timer;
		long lastTime;
		private int playingspeed = 1 ; //TODO move this to a map class. 
		//	private MusicPlayer soundEffectPlayer
		//keyboards input are stored in the the linkedlists
		public int[] tiles = new int [numberOfTiles * numberOfTiles];
		public static Map gameMap;
	
		private Thread thread;
		private static boolean running = false;
		// number of ticks
		public static int updates; //TODO move this to map. 
		//number of frames displayed on the screen
		public static int frames;
		
		public static boolean resetGame = false;
		public static boolean endGame = false;
		public static Player winner;
		
		//  (creates an image)
		private BufferedImage endimg = null;
		private BufferedImage bkgimg = null;
		private BufferedImage gameImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		//converts the image objects into an array of int (allows to draw things on the image)
		private int[] pixels = ((DataBufferInt)gameImage.getRaster().getDataBuffer()).getData();

		/**
		 * this is the game panel object . only 1 copy exist per game ~!
		 */
		private static GamePanel gamePanelInstance = new GamePanel();
		/**
		 * Create the panel.
		 */
		
		//Constructor
		GamePanel() {
			gameLogic = new GameLogic();
			this.setSize(size, size);
			addKeyListener(this);
			
			
			
			try {
				//Change this to your own path
			    bkgimg = ImageIO.read(new File("C:/Users/Owner/git/team-15/tron2.jpg"));
			} catch (IOException e) {}	
		}
		
		@Override
		public synchronized void keyPressed(KeyEvent e) {
            
        	switch(e.getKeyCode()) {

        	//for player 1
        	case KeyEvent.VK_W:
        		System.out.println("up");
        		gameLogic.addP1Direction(Control.NORTH);
        		break;
        		// for player 2
        	case KeyEvent.VK_UP:
        		gameLogic.addP2Direction(Control.NORTH);
        		break;
        		//for player 1
        	case KeyEvent.VK_S:
        		gameLogic.addP1Direction(Control.SOUTH);
        		break;
        		// for player 2
        	case KeyEvent.VK_DOWN:
        		gameLogic.addP2Direction(Control.SOUTH);
        		break;
        		//for player 1
        	case KeyEvent.VK_A:
        		gameLogic.addP1Direction(Control.WEST);
        		break;
        		//for player 2        
        	case KeyEvent.VK_LEFT:
        		gameLogic.addP2Direction(Control.WEST);
        		break;
        	case KeyEvent.VK_D:
        		gameLogic.addP1Direction(Control.EAST);
        		break;

        		//for player 2
        	case KeyEvent.VK_RIGHT:
        		gameLogic.addP2Direction(Control.EAST);
        		break;
        	}
        }
		
		
	
		public static GamePanel getInstance(){
			return gamePanelInstance;
		}
		//TODO fill in reset()
		/**5
		 * reset gameMap and players
		 */
	
		//start() will be called to start a new thread start the game
		public synchronized void start() {
			suspendflag = false;
			running = true;
			thread = new Thread(this,"Tron");
			gameLogic.initializePlayers();
			thread.start();
		}

		public void stop() {
			running = false;
			gameLogic.reinitializeGame();
			clearScreen(pixels);
		}
		
		public synchronized void resume(){
			updates = 0;
			frames = 0;
			this.timer = System.currentTimeMillis();
			this.lastTime = System.nanoTime();
			running = true;
			resetGame= false;
		}
		//when the thread start it runs this 
		public void run() {
			
			frames = 0;
			updates = 0;
			this.timer = System.currentTimeMillis();

			//Timer variables ( limits the update rate to 30 times per second)
			this.lastTime = System.nanoTime();
			double framesPerSecond = 30.0 * playingspeed ;
			//time of one frame
			final double ns = 1000000000.0 / framesPerSecond;

			double delta = 0.0;
			//main game loop
			while(running){
				gameInterruptionCheck();
				long now = System.nanoTime();
				delta += (now-lastTime) / ns;
				lastTime = now;
				//this only happens 30 times per second
				while (delta >=1 ){
					gameLogic.update();
					updates++;
					delta--;
				}
				render();
				frames++;

				if(System.currentTimeMillis() - timer > 1000){
					timer += 1000;
					System.out.println(updates + "ups, " + frames + " fps");
					updates = 0;
					frames = 0;
				}
			}
			
		}

	
		//render takes care of the graphical processing of the game
		private void render() {
			//Use bufferstrategy to pre-render new frames and reduce choppiness of the gameplay
			BufferStrategy bs = getBufferStrategy();
			if (bs == null){
				createBufferStrategy(BUFFER);
				return;
			}
			
			clearScreen(pixels);
			renderScreen(pixels);

			//Creates a link between Graphics and buffer
			Graphics g = bs.getDrawGraphics();
			
			//set the background to black
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(),getHeight());

			//draw tron image on top of the background
			//comment this out if you don't want the awesome background,however you must set the background to black 
			//g.drawImage(bkgimg, 0, 0, getWidth(), getHeight(),null);
			//draw the actual game on top of the tron image
			g.drawImage(gameImage, 0, 0, getWidth(), getHeight(),null);

			//release system ressources, remove the current frame
			g.dispose();

			//swap buffer. Make the next available buffer visible in the window.
			bs.show();

		}

		//renderScreen draws the game
		public void renderScreen( int[] pixels){
			gameLogic.renderScreen(pixels);
		}

		public void clearScreen(int[] pixels){
			for ( int i = 0 ; i < pixels.length ; i++){
				pixels[i] = 0;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	    
	    public void gameInterruptionCheck(){
	    	if(resetGame){
	    		stop();
	    		//do something here
	    		showRoundEndMsg();
	    		//stop();
	    		resume();
	    	}
	    	else if (endGame){
	    		
	    		showGameEndMsg();
	    		stop();
	    		//do something here
	    	}
	    }
	    
	    private void showRoundEndMsg(){
	    	
	    	Object[] options = {"Next Round",
            };
	    	JOptionPane.showOptionDialog (
	    			   null, 
	    			   "Round " +  gameLogic.getGameRoundNumber() + " Player 1 Wins",
	    			   "Round End", JOptionPane.YES_OPTION,
	    			   JOptionPane.QUESTION_MESSAGE,
	    			   null,
	    			   options,
	    			   options[0]);
	    }
	    
	    private void showGameEndMsg(){
	    	
	    	Object[] options = {"Return to menu",
            };
	    	JOptionPane.showOptionDialog (
	    			   null, 
	    			   "Player 1 Wins Game",
	    			   "Game End", JOptionPane.YES_OPTION,
	    			   JOptionPane.QUESTION_MESSAGE,
	    			   null,
	    			   options,
	    			   options[0]);
	    }
	
}


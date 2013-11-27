package UI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
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

/**This class has two main functions : Take in key inputs and displays the game on the screen
 * 

 /**
 * 	@author Han Yang Zhao
 *
 */
	public class GamePanel extends Canvas implements KeyListener, Runnable {
		boolean suspendflag;
		private static GameLogic gameLogic;
		public static int size = 512;
		public static int width = 600;
		public static int height = 400;
		private static final long serialVersionUID = 1L;
		private static final int BUFFER = 3;
		private static final int tilesWidth = 75;
		private static final int tilesHeight = 50;
		//this determines the size of each square in pixel, 2 = 4x4 , 3 = 8x8 , 4= 16x16

		long timer;
		long lastTime;
		private int playingspeed = 1 ; //TODO move this to a map class. 
		//keyboards input are stored in the the linkedlists
		public int[] tiles = new int [tilesWidth * tilesHeight];

		private  Thread thread;
		private static boolean running = false;
		// number of ticks
		public static int updates; //TODO move this to map. 
		//number of frames displayed on the screen
		public static int frames;
		public BufferStrategy bs;
		
		public static boolean resetGame = false;
		public static boolean endGame = false;  
		public static Player winner;
		public static boolean isDraw = false;
		
		//  (creates an image)
		private BufferedImage endimg = null;  //TODO not used 
		private BufferedImage bkgimg = null;  //TODO not used 
		private BufferedImage gameImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		//converts the image objects into an array of int (allows to draw things on the image)
		private int[] pixels = ((DataBufferInt)gameImage.getRaster().getDataBuffer()).getData();

		/**
		 * this is the game panel object . only 1 copy exist per game ~!
		 */
		private static GamePanel gamePanelInstance = new GamePanel();
		
		/**
		 * Takes key inputs and add them to player direction LinkedList in {@link GameLogic} 
		 */
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
		
		/**
		 * 
		 * @return  returns a static instance of the GamePanel Class
		 */
		public static GamePanel getInstance(){
			return gamePanelInstance;
		}

		/**
		 * start() will be called to start a new thread start the game
		 */
		public synchronized void start() {
			
			gameLogic = GameLogic.getInstance();
			//gamePanelInstance.createBufferStrategy(BUFFER);
			//Use bufferstrategy to pre-render new frames and reduce choppiness of the gameplay
			bs = gamePanelInstance.getBufferStrategy();
					
			
			
			//adds an background image
			try {
				//Change this to your own path
			    bkgimg = ImageIO.read(new File("C:/Users/Joanna/git/team-15/tron2_2.png"));
			} catch (IOException e) {}	
			isDraw = false;
			resetGame = false;
	    	endGame = false;
			running = true;
			thread = new Thread(gamePanelInstance,"Tron");
			gameLogic.initializePlayers();
			thread.start();
		}
		/**
		 * Interrupts the thread after a round ends or the game ends
		 */
		public void stop() {
			running = false;
			clearScreen(pixels);
		}
		/**
		 * Resume the game after a reinitializing players for a new round
		 */
		public synchronized void resume(){
			
			updates = 0;
			frames = 0;
			gameLogic.initializePlayers();
			this.timer = System.currentTimeMillis();
			this.lastTime = System.nanoTime();
			running = true;
			resetGame= false;
			isDraw = false;
		}
		/**
		 * called at the beginning of the Game match.
		 */
		
			public void run() {

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
						//System.out.println(updates + "ups, " + frames + " fps");
						updates = 0;
						frames = 0;
					}
				}

			}
		

		/**
		 * Render() takes care of the graphical processing of the game.
		 * We use a bufferStrategy to pre-render frames for smoother game.
		 * It calls on renderScreen() to update each pixels in the game,then use g.draw to draw it in the canvas
		 */
		private void render() {
			//Use bufferstrategy to pre-render new frames and reduce choppiness of the gameplay
			 
			clearScreen(pixels);
			renderScreen(pixels);

			//Creates a link between Graphics and buffer
			Graphics g = bs.getDrawGraphics();
			
			//set the background to black
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth(),getHeight());

			//draw tron image on top of the background
			//comment this out if you don't want the awesome background,however you must set the background to black 
			g.drawImage(bkgimg, 0, 0, getWidth(), getHeight(),null);
			//draw the actual game on top of the tron image
			g.drawImage(gameImage, 0, 0, getWidth(), getHeight(),null);

			//release system ressources, remove the current frame
			g.dispose();

			//swap buffer. Make the next available buffer visible in the window.
			bs.show();

		}

		/**
		 * renderScreen() draws the game by updating each pixels in the game window
		 * @param pixels
		 */
		public void renderScreen( int[] pixels){
			gameLogic.renderScreen(pixels);
		}

		/**
		 * clearScreen clears the game window
		 * @param	pixels
		 */
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
	    
		/**
		 * Checks if the resetGame flag and the endGame flag are enabled and restart the game or end the game 
		 * according to the situation
		 */
	    public void gameInterruptionCheck(){
	    	if(resetGame && !endGame){ //when each match ends do :
	    		stop();
	    		//do something here
	    		showRoundEndMsg();
	    		//stop();
	    		resume();
	    	}
	    	else if (endGame){	//when all 3 matches do : 
	    		
	    		stop();
	    		showGameEndMsg();
	    		
	    		gameLogic.initializePlayers();
	    		gameLogic.resetRoundNumber();
	    		
	    		
	    		gamePanelInstance.setVisible(false);
	    		GameFrame.getInstance().setVisible(false);
		    	MainMenu.getInstance().setVisible(true);
		    	
		    	
		    			
	    		//do something here
	    	}
	    }
	    /**
	     * shows message after round ends
	     */
	    private void showRoundEndMsg(){
	    	
	    	if(isDraw){
	    		
	    		Object[] options = {"Next Round",
	            };
		    	JOptionPane.showOptionDialog (
		    			   null, 
		    			   "Round Draw",
		    			   "Round End", JOptionPane.YES_OPTION,
		    			   JOptionPane.QUESTION_MESSAGE,
		    			   null,
		    			   options,
		    			   options[0]);
	    	}
	    	else{
	    		Object[] options = {"Next Round",
	    		};
	    		JOptionPane.showOptionDialog (
	    				null, 
	    				"Round " +  gameLogic.getGameRoundNumber() + " " + winner.getUsername() + " Wins",
	    				"Round End", JOptionPane.YES_OPTION,
	    				JOptionPane.QUESTION_MESSAGE,
	    				null,
	    				options,
	    				options[0]);
	    	}
	    }
		/**
		 * Behavior when player has used power up.
		 * @param player 
		 * @throws UnimplementedException the class has not yet been implemented for the final project.
		 * May have time and interest to implement and expand game in the future.
		 */
		public void usePowerUp(Player player) throws UnimplementedException{ //TODO: fill in usePowerUp
			throw new UnimplementedException();
		}
		/**
		 * The class defines the behavior when player has obtained a power up 
		 * @throws UnimplementedException the class has not yet been implemented for the final project.
		 * May have time and interest to implement and expand game in the future.
		 */
		public void obtainPowerUp() throws UnimplementedException{
			throw new UnimplementedException();
		}

	    /**
	     * display winning message when game ends
	     */
	    private void showGameEndMsg(){
	    	
	    	Object[] options = {"Return to menu",
            };
	    	JOptionPane.showOptionDialog (
	    			   null, 
	    			   winner.getUsername()+ " Wins the Game",
	    			   "Game End", JOptionPane.YES_OPTION,
	    			   JOptionPane.QUESTION_MESSAGE,
	    			   null,
	    			   options,
	    			   options[0]);
	    	
	    }	
}

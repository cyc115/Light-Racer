package UI;

import java.awt.Canvas;
import java.awt.Color;

import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import GameCore.Control;
import GameCore.GameScore;
import GameCore.Map;
import GameCore.Player;
import GameCore.Map.MapSign;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;


import GameCore.*;

/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */
	public class GamePanel extends Canvas implements Runnable{
		public static int size = 512;
		public static int width = size;
		public static int height = size;
		public static int scale = 1;
		private static final long serialVersionUID = 1L;
		private static final int BUFFER = 3;
		private static final int numberOfTiles = 128;
		//this determines the size of each square in pixel, 2 = 4x4 , 3 = 8x8 , 4= 16x16
		private static final int bitshift = 2;

		private Player player1;
		private Player player2;
		private GameScore gamescore;
		private int playingspeed = 1 ; //TODO move this to a map class. 
		//	private MusicPlayer soundEffectPlayer
		//keyboards input are stored in the the linkedlists
		private LinkedList <Control> p1Direction;
		private LinkedList <Control> p2Direction;
		final static int  MAX_KEYINPUT = 5;
		private boolean isPaused = false;
		private boolean endGame = false;
		private boolean endRound = false;
		public int[] tiles = new int [numberOfTiles * numberOfTiles];
		private Map gameMap;
		private MapSign[][] gameMapArray;
		private MapSign[] convertedMapArray;
		private int roundNumber;

		private char userKeypboardInput;
		private Thread thread;
		private boolean running = false;
		// number of ticks
		public static int updates; //TODO move this to map. 
		//number of frames displayed on the screen
		public static int frames;

		//  (creates an image)
		private BufferedImage endimg = null;
		private BufferedImage bkgimg = null;
		private BufferedImage gameImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		//converts the image objects into an array of int (allows to draw things on the image)
		private int[] pixels = ((DataBufferInt)gameImage.getRaster().getDataBuffer()).getData();


		private char userKeyboardInputP1;
		private char userKeyboardInputP2; 
		//Some drawer object here with a lot of logic inside

		/**
		 * this is the game panel object . only 1 copy exist per game ~!
		 */
		private static GamePanel gamePanelInstance = new GamePanel();
		/**
		 * Create the panel.
		 */
		
		//Constructor
		GamePanel() {
			gamescore = new GameScore();
			Coordinate startingPosP1 = new Coordinate(60,1);
			Coordinate startingPosP2 = new Coordinate(65,1);
			player1 = new Player(startingPosP1);
			player2 = new Player(startingPosP2);
			this.setSize(size, size);
			//addKeyListener(this);
			try {
				//Change this to your own path
			    bkgimg = ImageIO.read(new File("C:/Users/Owner/git/team-15/tron2.jpg"));
			} catch (IOException e) {}	
				
			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					
					switch(e.getKeyCode()) {

					//for player 1
					case KeyEvent.VK_W:
						if(!isPaused && !endGame && !endRound) {
							//makes sure we don't poll more than 3 times
							if(p1Direction.size() < MAX_KEYINPUT) {
								//checks that the most recent direction is either EAST or WEST
								Control last = p1Direction.peekLast();
								if(last != Control.SOUTH && last != Control.NORTH) {
									p1Direction.addLast(Control.NORTH);
								}
							}
						}
						break;

						// for player 2
					case KeyEvent.VK_UP:
						if(!isPaused && !endGame) {
							if(p2Direction.size() < MAX_KEYINPUT) {
								Control last = p2Direction.peekLast();
								if(last != Control.SOUTH && last != Control.NORTH) {
									p2Direction.addLast(Control.NORTH);
								}
							}
						}
						break;

						//for player 1
					case KeyEvent.VK_S:
						if(!isPaused && !endGame) {
							//makes sure we don't poll more than 3 times
							if(p1Direction.size() < MAX_KEYINPUT) {
								Control last = p1Direction.peekLast();
								//checks that the most recent direction is either EAST or WEST
								if(last != Control.NORTH && last != Control.SOUTH) {
									p1Direction.addLast(Control.SOUTH);
								}
							}
						}
						break;

						// for player 2 
					case KeyEvent.VK_DOWN:
						if(!isPaused && !endGame) {
							if(p2Direction.size() < MAX_KEYINPUT) {
								Control last = p2Direction.peekLast();
								if(last != Control.NORTH && last != Control.SOUTH) {
									p2Direction.addLast(Control.SOUTH);
								}
							}
						}
						break;

						//for player 1
					case KeyEvent.VK_A:
						if(!isPaused && !endGame) {
							//makes sure we don't poll more than 3 times
							if(p1Direction.size() < MAX_KEYINPUT) {
								Control last = p1Direction.peekLast();
								//checks that the most recent direction is either North or South
								if(last != Control.WEST && last != Control.EAST) {
									p1Direction.addLast(Control.WEST);
								}
							}
						}
						break;

						//for player 2	
					case KeyEvent.VK_LEFT:
						if(!isPaused && !endGame) {
							//makes sure we don't poll more than 3 times
							if(p2Direction.size() < MAX_KEYINPUT) {
								Control last = p2Direction.peekLast();
								//checks that the most recent direction is either North or South
								if(last != Control.WEST && last != Control.EAST) {
									p2Direction.addLast(Control.WEST);
								}
							}
						}
						break;

						//for player 1
					case KeyEvent.VK_D:
						if(!isPaused && !endGame) {
							//makes sure we don't poll more than 3 times
							if(p1Direction.size() < MAX_KEYINPUT) {
								Control last = p1Direction.peekLast();
								//checks that the most recent direction is either North or South
								if(last != Control.WEST && last != Control.EAST) {
									p1Direction.addLast(Control.EAST);
								}
							}
						}
						break;

						//for player 2 
					case KeyEvent.VK_RIGHT:
						if(!isPaused && !endGame) {
							//makes sure we don't poll more than 3 times
							if(p2Direction.size() < MAX_KEYINPUT) {
								Control last = p2Direction.peekLast();
								//checks that the most recent direction is either North or South
								if(last != Control.WEST && last != Control.EAST) {
									p2Direction.addLast(Control.EAST);
								}
							}
						}
						break;

					case KeyEvent.VK_P:
						if(endGame) {
							pauseGame();
						}
						break;

						/*
						 * Reset the game if one is not currently in progress.
						 */
					case KeyEvent.VK_ENTER:
						if( endGame) {
							resetGame();
						}
						break;
					}
				}
			});
		}
	
		public static GamePanel getInstance(){
			return gamePanelInstance;
		}
		//TODO fill in reset()
		/**
		 * reset gameMap and players
		 */
	
		//start() will be called to start a new thread start the game
		public synchronized void start() {
			
			running = true;
			thread = new Thread(this, "Tron");
			thread.start();
		}

		public synchronized void stop() {
			running = false;
			try{
				thread.join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//when the thread start it runs this 
		public void run() {
			//some initialization for the players
			
			p1Direction = new LinkedList<> ();
			p1Direction.add(player1.getDirection());
			
			p2Direction = new LinkedList<> ();
			p2Direction.add(player2.getDirection());
		
			
			gameMap = new Map(); //right now, the constructor is set up so this will make a blank map
			//     gameMap.createMapFromFile() ...
			//Coordinate staringCoordinateP1 = new Coordinate(0, 0);
			//Coordinate staringCoordinateP2 = new Coordinate(100, 0);
			//player1 = new Player(startingCoordinateP1);
			//player2 = new Player(startingCoordinateP2);
			this.isPaused = false;
			
			frames = 0;
			updates = 0;
			long timer = System.currentTimeMillis();


			//Timer variables ( limits the update rate to 30 times per second)
			long lastTime = System.nanoTime();
			double framesPerSecond = 30.0 * playingspeed ;
			//time of one frame
			final double ns = 1000000000.0 / framesPerSecond;

			double delta = 0.0;
			//main game loop
			while(running){
				long now = System.nanoTime();
				delta += (now-lastTime) / ns;
				lastTime = now;
				//this only happens 30 times per second
				while (delta >=1 ){
					update();
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
			stop();
		}

		private void update() {
			 movePlayers(p1Direction, player1, gameMap,  "player1Trail" , "player1Head");
			 movePlayers(p2Direction, player2, gameMap,  "player2Trail" , "player2Head");
			 handleCollisions(player1, player2);			 
			 if(p1Direction.size() > 1)
				 p1Direction.poll();
			 if(p2Direction.size()> 1)
				 p2Direction.poll();
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

			//Render the map onto the screen
			for (int i = 0 ; i < gameMap.getMapSize(); i++){

				switch(gameMap.getOccupation1D(i)){
				case EMPTY:
					//transparent
					tiles[i]= 0x000000;
					break;
				case WALL:
					//grey
					tiles[i]= 0xFF9100 | 0xB0000000 ;
					break;
				case player1Trail:
					//blue 0x00F0FC
					tiles[i]= 0x00F0FC | 0xD0000000;
					break;
				case player2Trail:
					//red
					tiles[i]= 0xFC0000 | 0xD0000000;
					break;
				case power1:
					//green
					tiles[i]= 0x3BBF3D | 0xFF000000;
					break;
				case power2:
					//orange
					tiles[i]= 0xFF9100 | 0xFF000000;
					break;
				case player1Head:
					//light blue
					tiles[i] = 0x7DB3E3 | 0xFF000000;
					break;
				case player2Head:
					//light red 
					tiles[i] = 0xDE8181 | 0xFF000000;
					break;
				default:
					break;
				}
			}
			//This converts the tiles to pixels to be displayed on the screen
			for (int y = 0; y < height ; y++){
				int yy = y;
				if(yy < 0 || y >= height) break;
				for (int x = 0 ; x < width ; x++){
					int xx = x;
					if(xx < 0 || x >= width) break;
					//updates pixels line by line from left to right and up to bottom
					//each tiles has 16x16 pixels
					int tileIndex = (x >> bitshift) + (y >> bitshift) * numberOfTiles;
					pixels[x + y * width] = tiles[tileIndex];

				}

			}

		}

		public void clearScreen(int[] pixels){
			for ( int i = 0 ; i < pixels.length ; i++){
				pixels[i] = 0;
			}
		}
				
		/**
		 * puts newest player coordinate onto the gameMap
		 * puts player1Trail and player2Trail on the gameMap
		 * paints player1Head and player2Head on the gameMap
		 * Execute collision detection
		 */
		public void movePlayers( LinkedList<Control> playerDirection, Player player,  Map mapArray , String trail , String head){ 
			Control playerDir = playerDirection.peekFirst();
			Coordinate playerCoords = player.getPlayerLocation();
			mapArray.setOccupation(playerCoords, trail);
			
			switch(playerDir){
			
			case NORTH:
				playerCoords.setY(playerCoords.getY() -1 );
				if(hasCollided(player, mapArray, playerCoords))
					break;
				mapArray.setOccupation(playerCoords, head);
				break;
				
			case SOUTH:
				playerCoords.setY(playerCoords.getY() + 1 );
				if(hasCollided(player, mapArray, playerCoords))
					break;
				mapArray.setOccupation(playerCoords, head);
				break;
			case WEST:
				playerCoords.setX(playerCoords.getX() -1 );
				if(hasCollided(player, mapArray, playerCoords))
					break;
				mapArray.setOccupation(playerCoords, head);
				break;
			case EAST:
				playerCoords.setX(playerCoords.getX() + 1 );
				if(hasCollided(player, mapArray, playerCoords))
					break;
				mapArray.setOccupation(playerCoords, head);
				break;
			 default:
				break;
			}
		}
		public int getGameRoundNumber(){ 
			return roundNumber;
		}
		public void incrRoundNumber(){
			this.roundNumber++;
		}
		
		public void endRound(){
			System.out.println("Round End");
			System.out.println( "player 1 Score: " + gamescore.getPlayerOneScore() );
			System.out.println( "player 2 Score: " + gamescore.getPlayerTwoScore() );
			this.stop();
		}
		
		
		public boolean endGame(){
			return true; //TODO: fill in endGame
		}
		
	    private void pauseGame() {
				// TODO Auto-generated method stub
	    }

	    private void resetGame() {
			this.stop();
			this.start();
		}
		
	    public void onGameResume(Player player1, Player player2, Map map){ //TODO: fill in onGameResume
			//			//listener stuff to get the new direction of player1 and player2

			char p1Direction = userKeyboardInputP1;
			char p2Direction = userKeyboardInputP2;			//			Control directionP1;
			//			Control directionP2;
			//TODO convert p1Direction and p2Direction to ControlEnum
			//makeTurn(player1, p1Direction);
			//makeTurn(player2, p2Direction);
			//handleCollisions(player1, player2, gameMap);
			//movePlayers(player1, player2, gameMap);
		}

		
		/**
		 * Checks is player has collided with anything on the gameMap
		 */
	    public boolean hasCollided(Player player, Map gameMap, Coordinate nextLocation){ //TODO: fill in hasCollided
	    	
	    		if(gameMap.isOccupied(nextLocation)){
	    			player.setCollision(true);
	    			return true;
	    		}
	    		else{
	    			return false;
	    		}
	    	
	    	
	    }
		/**
		 * handleCollision will first check if there are any collision, and then handle them.
		 * If there are no collisions, then it will return
		 * If there are collisions, then it will update the GameScore and end the game
		 */
		public boolean handleCollisions(Player player1, Player player2){ //TODO: fill in handleCollissions collision 
			boolean p1HasCollided = player1.getCollision();
			boolean p2HasCollided = player2.getCollision();

			if (!p2HasCollided && !p1HasCollided){
				return false;
			}
			else if(p2HasCollided && p1HasCollided){
				System.out.println("DRAW");
				endRound();
				return true;
			}
			else if( (!p1HasCollided) && (p2HasCollided)){ //p1 wins
				this.gamescore.incrP1Win();
				
				incrRoundNumber();
				
			}
			else if ( (p1HasCollided) && (!p2HasCollided) ){ //p2 wins
				this.gamescore.incrP2Win();
				
				incrRoundNumber();
			}
			if(this.roundNumber <3){
				endRound();
				return true;
			}
			else{
				endGame();
				return true;
			}
		}
		public void usePowerUp(Player player){ //TODO: fill in usePowerUp

		}
		public void obtainPowerUp(){ //TODO: fill in
		}

	
}


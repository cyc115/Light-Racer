package UI;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import GameCore.Control;
import GameCore.GameScore;
import GameCore.Map;
import GameCore.Player;
import GameCore.Map.MapSign;






import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Timer;
import javax.swing.JPanel;

import GameCore.*;


/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */


	public class GamePanel extends Canvas implements Runnable{
		
		public static int width = 1024;
		public static int height = 1024;
		public static int scale = 1;
		private static final long serialVersionUID = 1L;
		private static final int BUFFER = 3;
		private static final int tileSize = 128;
		private static final int bitshift = 3;

		
		private Player player1;
		private Player player2;
		private GameScore gamescore;
		//	private MusicPlayer soundEffectPlayer
		//keyboards input are stored in the the linkedlists
		private LinkedList <Control> p1Direction;
		private LinkedList <Control> p2Direction;
		final static int  MAX_KEYINPUT = 5;
		private boolean isPaused = false;
		private boolean endGame = false;
		public int[] tiles = new int [tileSize * tileSize];
		private Map gameMap;
		private MapSign[][] gameMapArray;
		private MapSign[] convertedMapArray;
		private int roundNumber;

		private char userKeypboardInput;
		private Thread thread;
		private boolean running = false;
		// number of ticks
		public static int updates;
		//number of frames displayed on the screen
		public static int frames;

		//  (creates an image)
		private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//converts the image objects into an array of int (allows to draw things on the image)
		private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();


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
		private GamePanel() {

			this.setSize(1024, 1024);
			//addKeyListener(this);


			addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					
					switch(e.getKeyCode()) {

					//for player 1
					case KeyEvent.VK_W:
						System.out.println("W");
						if(!isPaused && !endGame) {
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
			Coordinate startingPosP1 = new Coordinate(28,1);
			Coordinate startingPosP2 = new Coordinate(32,1);
			player1 = new Player(startingPosP1);
			player2 = new Player(startingPosP2);
			p1Direction = new LinkedList<> ();
			p1Direction.add(player1.getDirection());
			p1Direction.add(player1.getDirection());
			p1Direction.add(player1.getDirection());
			p2Direction = new LinkedList<> ();
			p2Direction.add(player2.getDirection());
			p1Direction.add(player1.getDirection());
			p1Direction.add(player1.getDirection());
			
			gameMap = new Map(); //right now, the constructor is set up so this will make a blank map
			//     gameMap.createMapFromFile() ...
			//Coordinate staringCoordinateP1 = new Coordinate(0, 0);
			//Coordinate staringCoordinateP2 = new Coordinate(100, 0);
			//player1 = new Player(startingCoordinateP1);
			//player2 = new Player(startingCoordinateP2);
			this.isPaused = false;
			this.roundNumber = 0;
			
			frames = 0;
			updates = 0;
			long timer = System.currentTimeMillis();


			//Timer variables ( limits the update rate to 60 times per second)
			long lastTime = System.nanoTime();
			double framesPerSecond = 30.0 ;
			//time of one frame
			final double ns = 1000000000.0 / framesPerSecond;

			double delta = 0.0;
			//main game loop
			while(running){
				long now = System.nanoTime();
				delta += (now-lastTime) / ns;
				lastTime = now;
				//this only happens 60 times per second
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

			//draw new image on top of the background
			g.drawImage(image, 0, 0, getWidth(), getHeight(),null);

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
					//grey color
					tiles[i]= 0xBEC0C2;
					break;
				case WALL:
					//black
					tiles[i]= 0x424242;
					break;
				case player1Trail:
					//blue
					tiles[i]= 0x00F0FC;
					break;
				case player2Trail:
					//red
					tiles[i]= 0xFC0000;
					break;
				case power1:
					//green
					tiles[i]= 0x3BBF3D;
					break;
				case power2:
					//orange
					tiles[i]= 0xFF9100;
					break;
				case player1Head:
					//light blue
					tiles[i] = 0x7DB3E3;
				case player2Head:
					//light red
					tiles[i] = 0xDE8181;
				default:
					break;
				}
			}

			for (int y = 0; y < height ; y++){
				int yy = y;
				if(yy < 0 || y >= height) break;
				for (int x = 0 ; x < width ; x++){
					int xx = x;
					if(xx < 0 || x >= width) break;
					//updates pixels line by line from left to right and up to bottom
					//each tiles has 16x16 pixels
					int tileIndex = (x >> bitshift) + (y >> bitshift) * tileSize;
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
		 */
		public void movePlayers( LinkedList<Control> playerDirection, Player player,  Map mapArray , String trail , String head){ 
			Control playerDir = playerDirection.peekFirst();
			Coordinate playerCoords = player.getPlayerLocation();
			mapArray.setOccupation(playerCoords, trail);
			
			 if(playerDirection.size() > 1) {
	                playerDirection.poll();
			}	
			
			switch(playerDir){
			
			case NORTH:
				playerCoords.setY(playerCoords.getY()-1);
				mapArray.setOccupation(playerCoords, head);
				break;
			
			case SOUTH:
				playerCoords.setY(playerCoords.getY()+1);
				mapArray.setOccupation(playerCoords, head);
				break;
			
			case WEST:
				playerCoords.setX(playerCoords.getX()-1);
				mapArray.setOccupation(playerCoords, head);
				break;
			
			case EAST:
				playerCoords.setX(playerCoords.getX()+1);
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
		public boolean endGame(){
			return true; //TODO: fill in endGame
		}
		
	    private void pauseGame() {
				// TODO Auto-generated method stub
				
	    }

	    private void resetGame() {
				// TODO Auto-generated method stub
				
		}
		
	    public void onGameResume(Player player1, Player player2, Map map){ //TODO: fill in onGameResume
			//			//listener stuff to get the new direction of player1 and player2

			char p1Direction = userKeyboardInputP1;
			char p2Direction = userKeyboardInputP2;
			//			Control directionP1;
			//			Control directionP2;
			//TODO convert p1Direction and p2Direction to ControlEnum
			//makeTurn(player1, p1Direction);
			//makeTurn(player2, p2Direction);
			//handleCollisions(player1, player2, gameMap);
			//movePlayers(player1, player2, gameMap);
		}

		
		
		/**
		 * Checks is player has colided with anything on the gameMap
		 */
		/*public boolean hasCollided(Player player, Map gameMap){ //TODO: fill in hasCollided
		//Coordinate playerLocation = player.getPlayerLocation();
		if(gameMap.isOccupied(playerLocation)){
			return true;
		}
		else{
			return false;
		}
	}*/
		/**
		 * handleCollision will first check if there are any collision, and then handle them.
		 * If there are no collisions, then it will return
		 * If there are collisions, then it will update the GameScore and end the game
		 */
		/*public void handleCollisions(Player player1, Player player2, Map gameMap){ //TODO: fill in handleCollissions collision 
		boolean p1HasCollided = hasColided(player1, gameMap);
		boolean p2HasCollided = hasColided(Player2, gameMap);

		if ( (!p1HasCollided)&&(!p2HasCollided)){
			//return;
		}
		else if( (!p1HasCollided)&&(p2HasCollided)){ //p1 wins
			GamePanel.gameScore.incrP1Win();
		}
		else( (!p1HasCollided)&&(p2HasCollided)){ //p2 wins
			GampePanel.gameScore.incrP2Win();
		}
		incrRoundNumber();
		if(GamePanel.roundNumber<3){
			resetGame();
		}
		else{
			endGame();
		}
	}*/
		public void usePowerUp(Player player){ //TODO: fill in usePowerUp
			
		}
		public void obtainPowerUp(){ //TODO: fill in
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		} 

}


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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;

import GameCore.*;


/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */


	public class GamePanel extends Canvas implements  KeyListener,Runnable{
		
		public static int width = 1024;
		public static int height = 1024;
		public static int scale = 1;
		private static final long serialVersionUID = 1L;
		private static final int BUFFER = 3;

		private Timer time;

		private Player player1;
		private Player player2;
		private GameScore gamescore;
		//	private MusicPlayer soundEffectPlayer
		private Control direction;
		private boolean isPaused;
		public int[] tiles = new int [64 * 64];
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

		@Override
		public void keyPressed(KeyEvent arg0) {
			//if()
				//userKeyboardInputP1 = //keypressed
				//else
					//userKeyboardInputP2 = //keypressed
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
		 * this is the game panel object . only 1 copy exist per game ~!
		 */
		private static GamePanel gamePanelInstance = new GamePanel();
		/**
		 * Create the panel.
		 */
		
		//Constructor
		private GamePanel() {
			this.setSize(500, 500);
			addKeyListener(this);
		}
		
		public static GamePanel getInstance(){
			return gamePanelInstance;
		}
		//TODO fill in reset()
		/**
		 * reset gameMap and players
		 */
		public void reset(){
		}

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
			gameMap = new Map(); //right now, the constructor is set up so this will make a blank map
			gameMapArray = gameMap.getMap();
			//     gameMap.createMapFromFile() ...
			//Coordinate staringCoordinateP1 = new Coordinate(0, 0);
			//Coordinate staringCoordinateP2 = new Coordinate(100, 0);
			//player1 = new Player(startingCoordinateP1);
			//player2 = new Player(startingCoordinateP2);
			this.isPaused = false;
			this.roundNumber = 0;
			//onGameResume(player1, player2, Map);
			this.isPaused = false;

			frames = 0;
			updates = 0;
			long timer = System.currentTimeMillis();


			//Timer variables ( limits the update rate to 60 times per second)
			long lastTime = System.nanoTime();
			double framesPerSecond = 60.0 ;
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
			// TODO need to write changed to oneDmapArray
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

			convertedMapArray = convert2Dto1D(gameMapArray);
			//Render the map onto the screen
			for (int i = 0 ; i < convertedMapArray.length ; i++){

				switch(convertedMapArray[i]){
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
					tiles[i]= 0x2580CF;
					break;
				case player2Trail:
					//red
					tiles[i]= 0xCF2550;
					break;
				case power1:
					//green
					tiles[i]= 0x3BBF3D;
					break;
				case power2:
					//orange
					tiles[i]= 0xFF9100;
					break;
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
					int tileIndex = (x >> 4) + (y >> 4) * 64;
					pixels[x + y * width] = tiles[tileIndex];

				}

			}

		}

		public void clearScreen(int[] pixels){
			for ( int i = 0 ; i < pixels.length ; i++){
				pixels[i] = 0;
			}
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
			movePlayers(player1, player2, gameMap);
		}

		private MapSign[] convert2Dto1D(MapSign[][] gameMapArray) {
			MapSign[] convertedArray = new MapSign[gameMapArray.length * gameMapArray.length];
			for (int i = 0 ; i < gameMapArray.length ; i++){
				for( int j = 0 ; j < gameMapArray.length ; j++){
					convertedArray[(i * gameMapArray.length) + j] = gameMapArray[i][j];
				}

			}
			return convertedArray;

		}


		public void makeTurn(Player player, Control direction){ //TODO: fill in makeTurn
			//takes old direction of p1 and updates it using button it gets from listener
			//we can use a switch cases

		}
		/**
		 * puts newest player coordinate onto the gameMap
		 * puts player1Trail and player2Trail on the gameMap
		 */
		public void movePlayers(Player player1, Player player2, Map gameMap){ 
			//gameMap.setOccupation(player1.getPlayerLocation(), gameMap.MapSign.player1Trail);
			//gameMap.setOccupation(player2.getPlayerLocation(), gameMap.MapSign.player2Trail);
		}
		public int getGameRoundNumber(){ 
			return roundNumber;
		}
		public void incrRoundNumber(){
			this.roundNumber++;
		}
		public void endGame(){ //TODO: fill in endGame
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

}


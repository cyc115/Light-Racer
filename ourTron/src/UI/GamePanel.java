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



/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */
public class GamePanel extends Canvas implements Runnable {
	/**
	 * 
	 */
	public static int width = 500;
	public static int height = 500;
	public static int scale = 1;
	private static final long serialVersionUID = 1L;
	private static final int BUFFER = 3;
	private Player player1;
	private Player player2;
	private GameScore gamescore;
//	private MusicPlayer soundEffectPlayer
	private Control direction;
	private boolean isPaused;
	private Map gameMap;

	private int roundNumber;
	private char userKeypboardInput;
	private Thread thread;
	private boolean running = false;
	private Screen screen;
	public static int updates;
	public static int frames;
	
	//  (creates an image)
	private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	//converts the image objects into an array of int (allows to draw things on the image)
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	//private BufferedImage bkgimage = null;
	
	
	
	/**
	 * this is the game panel object . only 1 copy exist per game ~!
	 */
	private static GamePanel gamePanelInstance = new GamePanel();
	/**
	 * Create the panel.
	 */

	private GamePanel() {
		Dimension size = new Dimension(width *scale , height * scale);
		this.setPreferredSize(size);
		screen = new Screen(width,height);
	}
	
	
	//TODO stub
	public static GamePanel getInstance(){
		return gamePanelInstance;
	}
	//TODO fill in reset()
	public void reset(){
	}
	
	/*public void init(){ //TODO: fill in init()
        gameMap = new Map(); //right now, the constructor is set up so this will make a blank map
//        gameMap.createMapFromFile() ...
        Coordinate staringCoordinateP1 = new Coordinate(0, 0);
        Coordinate staringCoordinateP2 = new Coordinate(100, 0);
        player1 = new Player(startingCoordinateP1);
        player2 = new Player(startingCoordinateP2);
        this.isPaused = false;
        this.roundNumber = 0;
        onGameResume(player1, player2, Map);
}*/
	
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
		//gameMap = new Map();
//		gameMap.createMapFromFile() ...
		//player1 = new Player();
		//player2 = new Player();
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
		// TODO Auto-generated method stub
	}

	//render takes care of the graphical processing of the game
	private void render() {
		//Use bufferstrategy to pre-render new frames and reduce choppiness of the gameplay
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(BUFFER);
			return;
		}	
		screen.clear();
		screen.render();
		
		for(int i = 0 ; i< pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		//Creates a link between Graphics and buffer
		Graphics g = bs.getDrawGraphics();
		
		//set the background to black
		g.setColor(Color.BLACK);
		g.fillRect(0,0,getWidth(),getHeight());
		
		//draw new image on top of the background
		g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
		
		//release system ressources, remove the current frame
		g.dispose();
		
		//swap buffer. Make the next available buffer visible.
		bs.show();
		
	}

//	public void onGamePause(){ //TODO: fill in onGamePause
//	}
//	public void onGameResume(Player player1, Player player2, Map){ //TODO: fill in onGameResume
//		while(!isPaused){
//			//listener stuff to get the new direction of player1 and player2
			
//			Control directionP1;
//			Control directionP2;
//			makeTurn(player1, directionP1);
//			makeTurn(Player2, directionP2);
//			handleCollisions(player1, player2, gameMap);
//			movePlayers(Player1, Player2, gameMap);
//			draw gameMap
			
//		}
// }
	
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
		roundNumber++;
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
			GameScore.incrP1Win();
		}
		else( (!p1HasCollided)&&(p2HasCollided)){ //p2 wins
			GameScore.incrP2Win();
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
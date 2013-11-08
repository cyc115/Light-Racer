package UI;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;
import javax.swing.JPanel;
import GameCore.*;


/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener{
	private Timer time;
	private Player player1;
	private Player player2;
	private GameScore gamescore;
//	private MusicPlayer soundEffectPlayer
	private Control direction;
	private boolean isPaused;
	private Map gameMap;
	private int roundNumber;
	private char userKeyboardInputP1;
	private char userKeyboardInputP2; 
	//Some drawer object here with alot of logic inside
	
	//TODO this might need changing
	public MyPanel() {
		this.setPreferredSize(new Dimension(500,500));
		addKeyListener(this);
	}
	
	public void paintComponenet(Graphics g) {
		super.repaint();
	}
	
	//TODO put what you want to change between refreshes here!!! 
	public void actionPerformed(ActionEvent arg0) { //every time exception is thrown in timer, happens in action performed 
		if(isPaused ==  true)
			onPause();
		else
			onGameResume();
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(/*get the key pressed*/)
			userKeyboardInputP1 = //keypressed
		else
			userKeyboardInputP2 = //keypressed
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
	private GamePanel() {
		//initalize the drawer object
		//this.drawer = new Drawer();
		this.setSize(500, 500);
		this.timer = new Timer(1000, this);
		this.init();
		//key = new KeyListener(drawer);
	}
	
	//TODO stub
	public static GamePanel getInstance(){
		return gamePanelInstance;
	}
	//TODO fill in reset()
	/**
	 * reset gameMap and players
	 */
	public void reset(){
	}

	/**
	 * init() will be called to start the game
	 * (I think some of this should be moved into contructor but I need to put more thought in)
	 */
	public void init(){ //TODO: fill in init() 
		gameMap = new Map(); //right now, the constructor is set up so this will make a blank map
//		gameMap.createMapFromFile() ...
		Coordinate staringCoordinateP1 = new Coordinate(0, 0);
		Coordinate staringCoordinateP2 = new Coordinate(100, 0);
		player1 = new Player(startingCoordinateP1);
		player2 = new Player(startingCoordinateP2);
		this.isPaused = false;
		this.roundNumber = 0;
		onGameResume(player1, player2, Map);
	}
	public void onGamePause(){ //TODO: fill in onGamePause
		if(timer != null) {
			this.timer.stop();
			this.timer = null;
		}
	}
	public void onGameResume(Player player1, Player player2, Map){ //TODO: fill in onGameResume
		if(timer == null) {
			this.timer = new Timer(1000, this);
			this.timer.start();
		}
//			//listener stuff to get the new direction of player1 and player2
		
		char p1Direction = userKeyboardInputP1;
		char p2Direction = userKeyboardInputP2;
//			Control directionP1;
//			Control directionP2;
		//TODO convert p1Direction and p2Direction to ControlEnum
			makeTurn(player1, directionP1);
			makeTurn(Player2, directionP2);
			handleCollisions(player1, player2, gameMap);
			movePlayers(player1, player2, gameMap);
			//draw gameMap
			
//		}
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
		gameMap.setOccupation(player1.getPlayerLocation(), gameMap.MapSign.player1Trail);
		gameMap.setOccupation(player2.getPlayerLocation(), gameMap.MapSign.player2Trail);
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
	public boolean hasCollided(Player player, Map gameMap){ //TODO: fill in hasCollided
		Coordinate playerLocation = player.getPlayerLocation();
		if(gameMap.isOccupied(playerLocation)){
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
	public void handleCollisions(Player player1, Player player2, Map gameMap){ //TODO: fill in handleCollissions collision 
		boolean p1HasCollided = hasColided(player1, gameMap);
		boolean p2HasCollided = hasColided(Player2, gameMap);
		
		if ( (!p1HasCollided)&&(!p2HasCollided)){
			return;
		}
		else if( (!p1HasCollided)&&(p2HasCollided)){ //p1 wins
			GamePanel.gameScore.incrP1Win();
		}
		else( (!p1HasCollided)&&(p2HasCollided)){ //p2 wins
			GampePanel.gameScore.incrP2Win();
		}
		incrRoundNumber();
//		if(GamePanel.roundNumber<3){
//			resetGame();
//		}
//		else{
			endGame();
//		}
	}
	public void usePowerUp(Player player){ //TODO: fill in usePowerUp
	}
	public void obtainPowerUp(){ //TODO: fill in
	} 
	
}
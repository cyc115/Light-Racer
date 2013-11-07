package UI;

import javax.swing.JPanel;
import GameCore.*;


/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */
public class GamePanel extends JPanel {
	private Player player1;
	private Player player2;
	private GameScore gamescore;
//	private MusicPlayer soundEffectPlayer
	private Control direction;
	private boolean isPaused = false;
	private Map gameMap;
	private int roundNumber;
	private char userKeypboardInput;
	/**
	 * this is the game panel object . only 1 copy exist per game ~!
	 */
	private static GamePanel gamePanelInstance = new GamePanel();
	/**
	 * Create the panel.
	 */
	private GamePanel() {
		this.setSize(500, 500);
	}
	
	//TODO stub
	public static GamePanel getInstance(){
		return gamePanelInstance;
	}
	//TODO stub
	public void reset(){
	}

	//init() will be called to start the game
	public void init(){ //TODO: fill in init() 
		gameMap = new Map();
//		gameMap.createMapFromFile() ...
		player1 = new Player();
		player2 = new Player();
		this.isPaused = false;
	}
	public void onGamePause(){ //TODO: fill in onGamePause
	}
	public void onGameResume(){ //TODO: fill in onGameResume
		while(!isPaused){
//			//listener stuff to get the new direction of player1 and player2
//			Control directionP1;
//			COntrol directionP2;
//			makeTurn(player1, directionP1);
//			makeTurn(Player2, directionP2);
//			checkCollision();
//			movePlayer()
		}
	}
	public void makeTurn(Player player, Control direction){ //TODO: fill in makeTurn
	//takes old direction of p1 and updates it using button it gets from listener
	}
	public void movePlayer(Player player){ //TODO: fill in movePlayer
	}
	public int getGameRoundNumber(){ 
		return roundNumber;
	}
	public void endGame(){ //TODO: fill in endGame
	}
//	public boolean hasCollided(){ //TODO: fill in hasCollided
//	}
	public void usePowerUp(Player player){ //TODO: fill in usePowerUp
	}
	public void obtainPowerUp(){ //TODO: fill in
	} 
	
}

package UI;

import javax.swing.JPanel;
import GameCore.Control;
import GameCore.GameScore;
import GameCore.Map;
import GameCore.Player;


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
	private boolean isPaused;
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
	public void obtainPowerUp(){ //TODO: fill in onGamePause
	} 
	
}

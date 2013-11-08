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
	}
	public void onGameResume(Player player1, Player player2, Map){ //TODO: fill in onGameResume
		while(!isPaused){
//			//listener stuff to get the new direction of player1 and player2
			
//			Control directionP1;
//			Control directionP2;
//			makeTurn(player1, directionP1);
//			makeTurn(Player2, directionP2);
//			handleCollisions(player1, player2, gameMap);
//			movePlayer(Player1)
//			movePlayer(Player2)
			
		}
	}
	public void makeTurn(Player player, Control direction){ //TODO: fill in makeTurn
	//takes old direction of p1 and updates it using button it gets from listener
	//we can use a switch cases
		
	}
	public void movePlayer(Player player){ //TODO: fill in movePlayer
		
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
			GameScore.incrP1Win();
		}
		else( (!p1HasCollided)&&(p2HasCollided)){ //p2 wins
			GameScore.incrP2Win();
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
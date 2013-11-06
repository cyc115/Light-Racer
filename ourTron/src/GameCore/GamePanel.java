import Control;
import GameScore;
import Map;
import Player;


public class GamePanel {
	private Player player1;
	private Player player2;
	private GameScore gamescore;
//	private MusicPlayer soundEffectPlayer
	private Control direction;
	private boolean isPaused;
	private Map gameMap;
	private int roundNumber;
	private char userKeypboardInput;
	
	
// Constructor	
	public GamePanel() {
		this.isPaused = false;
		init();
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
	public void init(){ //TODO: fill in init() (right now it's just setup to work for the prototype)
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
	public boolean hasCollided(){ //TODO: fill in hasCollided
	}
	public void usePowerUp(Player player){ //TODO: fill in usePowerUp
	}
	public void obtainPowerUp(){ //TODO: fill in onGamePause
	}

//	public void updateGame(){
//	}
	
}

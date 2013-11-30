package GameCore; 

/**
 * The GameScore object is used to keep track of the player1 and player2's score in the current game.
 * Note: each game consists of three rounds.
 *
 */
public class GameScore {
	private int playerOneScore;
	private int playerTwoScore;
	
	public GameScore() {
		this.playerOneScore = 0;
		this.playerTwoScore = 0;
	}
	public void incrP1Win(){
		playerOneScore++;
	}
	public void incrP2Win(){
		playerTwoScore++;
	}
	public int getRoundNumber(){
		return playerOneScore + playerTwoScore;
	}
	
	public int getPlayerOneScore() {
		return playerOneScore;
	}
	public int getPlayerTwoScore() {
		return playerTwoScore;
	}
	
}

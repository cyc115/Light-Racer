package GameCore; 

public class GameScore {
	private int playerOneScore;
	private int playerTwoScore;
	
	public GameScore() {
		initialize();
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
	public void initialize(){
		this.playerOneScore = 0;
		this.playerTwoScore = 0;
	}
	
}

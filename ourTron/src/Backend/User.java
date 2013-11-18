/**
 * reviewed: Mike
 * 
 */

package Backend;

import java.io.Serializable;
import java.util.LinkedList;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private LinkedList<GameEntry> gameHistory;
	private UserDataBase userDB;
	
	public User() {
		this.username=null;
		this.password=null;
		this.gameHistory=new LinkedList<GameEntry>();
		this.userDB = new UserDataBase();
	}
	
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.gameHistory = new LinkedList<GameEntry>();
		for(GameEntry entry : user.gameHistory)
			this.gameHistory.add(entry.clone());
		this.userDB = new UserDataBase();
	}
	
	public User(String username, String password) {
		this.username=username;
		this.password=password;
		this.gameHistory=new LinkedList<GameEntry>();
		this.userDB = new UserDataBase();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addGameResult(User opponent, boolean won) {
		gameHistory.add(new GameEntry(opponent.username, won));
		userDB.modifyUser(this);
	}
	
	public int getWinsVsOpponent(User opponent) {
		int count = 0;
		for(GameEntry entry : this.gameHistory)
			//if the oppoent is that user and the entry is won
			if(entry.opponent.equals(opponent) && entry.won)
				count++;
		return count;
	}
	
	public int getTotalWins() {
		int count = 0;
		for(GameEntry entry : this.gameHistory)
			//if the oppoent is that user and the entry is won
			if(entry.won)
				count++;
		return count;
	}
	
	
	//TODO make sure equals works as it would be expected to here.
	public boolean equals(User user){
		boolean userSame = username.equals(user.username);
		boolean passSame = password.equals(user.password);
		boolean gameHistorySame = gameHistory.equals(user.gameHistory);
		return userSame && passSame && gameHistorySame;
	}
	
	
	//TODO remove for final project.
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}



	/**
	 * A game entry object just contains the username and the result of the game.
	 * @author danielle
	 */
	public class GameEntry {
		private String opponent;
		private boolean won;
		
		public GameEntry(String opponentUsername, boolean won) {
			this.opponent = opponentUsername;
			this.won = won;
		}
		
		public GameEntry clone() {
			return new GameEntry(this.opponent, this.won);
		}
		
		public boolean equals(GameEntry entry) {
			boolean opponentSame = this.opponent.equals(entry.opponent);
			boolean resultSame = this.won == entry.won;
			return opponentSame && resultSame;
		}

		public String getOpponent() {
			return opponent;
		}

		public void setOpponent(String opponent) {
			this.opponent = opponent;
		}

		public boolean isWon() {
			return won;
		}

		public void setWon(boolean won) {
			this.won = won;
		}
	}
}

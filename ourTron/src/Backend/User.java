/**
 * reviewed: Mike
 * 
 */

package Backend;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;


public class User implements Serializable, Comparable<User> {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private LinkedList<GameEntry> gameHistory;
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", gameHistory=" + gameHistory + "]";
	}
	
	public User() {
		//this user will always be rejected
		this.username=null;
		this.password=null;
		this.gameHistory=new LinkedList<GameEntry>();
//		UserDataBase.addUser(this);
	}
	
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.gameHistory = new LinkedList<GameEntry>();
		for(GameEntry entry : user.gameHistory)
			this.gameHistory.add(entry.clone());
		UserDataBase.addUser(this);
	}
	
	public User(String username, String password) {
		this.username=username;
		this.password=password;
		this.gameHistory=new LinkedList<GameEntry>();
		UserDataBase.addUser(this);
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
		UserDataBase.modifyUser(this);
	}
	
	public int getWinsVsOpponent(User opponent) {
		int count = 0;
		for(GameEntry entry : this.gameHistory)
			//if the oppoent is that user and the entry is won
			if(entry.opponent.equals(opponent.getUsername()) && entry.won)
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
	
	



	/**
	 * A game entry object just contains the username and the result of the game.
	 * @author danielle
	 */
	public class GameEntry implements Serializable {
		private static final long serialVersionUID = 1L;
		private String opponent;
		private boolean won;
		
		@Override
		public String toString() {
			return "GameEntry (opponent=" + opponent + ", won=" + won + ")";
		}

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



	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static class Comparators {

	    public static Comparator<User> Wins = new Comparator<User>() {
	        @Override
	        public int compare(User user1, User user2) {
	            return user2.getTotalWins() - user1.getTotalWins();
	        }
	    };
	}
}


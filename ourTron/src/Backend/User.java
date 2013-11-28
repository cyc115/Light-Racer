/**
 * reviewed: Mike
 * 
 */

package Backend;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * This class is the abstraction of a User containing essential properties like the a username, password and the game history. 
 * The object is Serializable therefore writable to a file. It is also comparable to another user with regards to wins for {@link PlayerStatistics} purposes. 
 * <p>
 * @author Danielle Mustillo
 * @author Joanna Halpern
 */
public class User implements Serializable, Comparable<User> {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private LinkedList<GameEntry> gameHistory;
	
	/**
	 * Prints the {@link User} information. Used for debugging purposes. 
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", gameHistory=" + gameHistory + "]";
	}
	/**
	 * This creates a default user. Will not be added to the database as it is not valid (all entries are empty).
	 */
	public User() {
		//this user will always be rejected
		this.username=null;
		this.password=null;
		this.gameHistory=new LinkedList<GameEntry>();
	}
	/**
	 * Creates a new {@link User} object with identical information to an existing user object. Creates a deep-copy of the user parameter. This user is written to the database if valid (or not if invalid).
	 * <p>
	 * @param user	A user object to copy. 
	 */
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.gameHistory = new LinkedList<GameEntry>();
		for(GameEntry entry : user.gameHistory)
			this.gameHistory.add(entry.clone());
		UserDataBase.addUser(this);
	}
	/**
	 * Creates a new {@link User} object from a username and a password. The user will be written to the database. 
	 * <p>
	 * @param username	A String object with the username.
	 * @param password	A String object containing the password. 
	 */
	public User(String username, String password) {
		this.username=username;
		this.password=password;
		this.gameHistory=new LinkedList<GameEntry>();
		UserDataBase.addUser(this);
	}
	/**
	 * Retrieves the username from this {@link User}. 
	 * <p>
	 * @return	A String containing the username of this user object.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets the username of this {@link User} to the input username. Should not be used by the client. Therefore it is a protected method. 
	 * <p>
	 * @param username	A String containing the username to change to.
	 */
	protected void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Retrieves the password from this {@link User} object. 
	 * <p>
	 * @return	A String containing the password of this user object.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets the password of this {@link User} to the input password. Should not be used by the client. Therefore it is a protected method. 
	 * <p>
	 * @param username	A String containing the password to change to.
	 */
	protected void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Adds the result of a game to this {@link User} object. Should be used after a series has been played only.
	 * @param opponent	A String object containing the username of the opponent. 
	 * @param won	A boolean whether this user won or not (true if won, false if lost).
	 */
	public void addGameResult(User opponent, boolean won) {
		gameHistory.add(new GameEntry(opponent.username, won));
		UserDataBase.modifyUser(this);
	}
	/**
	 * Compares this {@link User} object to the user parameter. It finds the games played versus the two opponents and returns the results. Protected because should only be used within the backend. 
	 * @param opponent	The user object of the opponent. 
	 * @return	The number of wins this user had versus the parameter user object. 
	 */
	protected int getWinsVsOpponent(User opponent) {
		int count = 0;
		for(GameEntry entry : this.gameHistory)
			//if the oppoent is that user and the entry is won
			if(entry.opponent.equals(opponent.getUsername()) && entry.won)
				count++;
		return count;
	}
	/**
	 * Gets the total number of wins from this {@link User} object. 
	 * @return	An integer containing the number of wins the user has. 
	 */
	protected int getTotalWins() {
		int count = 0;
		for(GameEntry entry : this.gameHistory)
			//if the oppoent is that user and the entry is won
			if(entry.won)
				count++;
		return count;
	}
	/**
	 * Compares this {@link User} object to the parameter user object. Finds if they are internally equal (but not necessarily the same object reference).
	 * @param user The user object to compare to
	 * @return	A boolean whether the two users are internally equal or not. 
	 */
	public boolean equals(User user){
		boolean userSame = username.equals(user.username);
		boolean passSame = password.equals(user.password);
		boolean gameHistorySame = gameHistory.equals(user.gameHistory);
		return userSame && passSame && gameHistorySame;
	}
	
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


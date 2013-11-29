package Backend;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * This class is the abstraction of a User containing essential properties like
 * the a username, password and the game history. The object is Serializable
 * therefore writable to a file. It is also comparable to another user with
 * regards to wins for {@link PlayerStatistics} purposes.
 * <p>
 * 
 * @author Danielle Mustillo
 * @author Joanna Halpern
 */
public class User implements Serializable, Comparable<User> {
	public LinkedList<GameEntry> getGameHistory() {
		return gameHistory;
	}

	public void setGameHistory(LinkedList<GameEntry> gameHistory) {
		this.gameHistory = gameHistory;
	}
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
	 * This creates a default user. Will not be added to the database as it is
	 * not valid (all entries are empty).
	 */
	public User() {
		// this user will always be rejected
		this.username = null;
		this.password = null;
		this.gameHistory = new LinkedList<GameEntry>();
	}

	/**
	 * Creates a new {@link User} object with identical information to an
	 * existing user object. Creates a deep-copy of the user parameter. This
	 * user is written to the database if valid (or not if invalid).
	 * <p>
	 * 
	 * @param user
	 *            A user object to copy.
	 */
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.gameHistory = new LinkedList<GameEntry>();
		for (GameEntry entry : user.gameHistory)
			this.gameHistory.add(entry.clone());
		UserDataBase.addUser(this);
	}

	/**
	 * Creates a new {@link User} object from a username and a password. The
	 * user will be written to the database.
	 * <p>
	 * 
	 * @param username
	 *            A String object with the username.
	 * @param password
	 *            A String object containing the password.
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.gameHistory = new LinkedList<GameEntry>();
		UserDataBase.addUser(this);
	}

	/**
	 * Retrieves the username from this {@link User}.
	 * <p>
	 * 
	 * @return A String containing the username of this user object.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of this {@link User} to the input username. Should not
	 * be used by the client. Therefore it is a protected method.
	 * <p>
	 * 
	 * @param username
	 *            A String containing the username to change to.
	 */
	protected void setUsername(String username) {
		this.username = username;
	}
	
	

	/**
	 * Retrieves the password from this {@link User} object.
	 * <p>
	 * 
	 * @return A String containing the password of this user object.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of this {@link User} to the input password. Should not
	 * be used by the client. Therefore it is a protected method.
	 * <p>
	 * 
	 * @param username
	 *            A String containing the password to change to.
	 */
	protected void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Adds the result of a game to this {@link User} object. Should be used
	 * after a series has been played only.
	 * 
	 * @param opponent
	 *            A String object containing the username of the opponent.
	 * @param won
	 *            A boolean whether this user won or not (true if won, false if
	 *            lost).
	 */
	public void addGameResult(User opponent, boolean won) {
		gameHistory.add(new GameEntry(opponent.username, won));
		UserDataBase.modifyUser(this);
	}
	
	/**
	 * This method will remove the last GameEntry of the user
	 * This method exists only for testing purposes
	 */
	public void removeLastGameResult() {
		GameEntry g = gameHistory.removeLast();
		UserDataBase.modifyUser(this);
	}

	/**
	 * Compares this {@link User} object to the user parameter. It finds the
	 * games played versus the two opponents and returns the results. Protected
	 * because should only be used within the backend.
	 * 
	 * @param opponent
	 *            The user object of the opponent.
	 * @return The number of wins this user had versus the parameter user
	 *         object.
	 */
	protected int getWinsVsOpponent(User opponent) {
		int count = 0;
		for (GameEntry entry : this.gameHistory)
			// if the oppoent is that user and the entry is won
			if (entry.opponent.equals(opponent.getUsername()) && entry.won)
				count++;
		return count;
	}

	/**
	 * Gets the total number of wins from this {@link User} object.
	 * 
	 * @return An integer containing the number of wins the user has.
	 */
	protected int getTotalWins() {
		int count = 0;
		for (GameEntry entry : this.gameHistory)
			// if the oppoent is that user and the entry is won
			if (entry.won)
				count++;
		return count;
	}

	/**
	 * Compares this {@link User} object to the parameter user object and finds if they equal. Finds if
	 * they are internally equal (but not necessarily the same object
	 * reference).
	 * 
	 * @param user
	 *            The user object to compare to
	 * @return A boolean whether the two users are internally equal or not.
	 */
	public boolean equals(User user) {
		boolean userSame = username.equals(user.username);
		boolean passSame = password.equals(user.password);
		boolean gameHistorySame = true;
		if (user.gameHistory.size() == this.gameHistory.size()) {
			int i = 0;
			for (GameEntry entry : gameHistory) {
				gameHistorySame = entry.equals(user.gameHistory.get(i));
				++i;
			}
		} else {
			gameHistorySame = false;
		}
		return userSame && passSame && gameHistorySame;
	}

	/**
	 * A game entry object is simply an abstraction for a game record. A Game Entry contains essential informatioin of a single
	 * series played (best of 3). This would include the opponent name and who won. GameEntry objects are
	 * intended to be iterable across an array and comparable with other
	 * GameEntry instances.
	 * <p>
	 * The results in GameEntry are intended to be relative to its owning {@link User} object.
	 * The owning user provides the remaining information (ie. who does the win
	 * status belong to, who is the other user who is not the opponent).
	 * Therefore, the GameEntry only exists within the context of a User.
	 * 
	 * @author Danielle Mustillo
	 * @author Joanna Halpern
	 */
	protected class GameEntry implements Serializable {
		private static final long serialVersionUID = 1L;
		private String opponent;
		private boolean won;

		@Override
		public String toString() {
			return "GameEntry (opponent=" + opponent + ", won=" + won + ")";
		}

		/**
		 * Unique constructor. Initializes all the internal components of this
		 * object at the start.
		 * 
		 * @param opponentUsername
		 *            A String object with the opponents username
		 * @param won
		 *            The result of this User, true if this user won and false
		 *            if this user lost
		 */
		protected GameEntry(String opponentUsername, boolean won) {
			this.opponent = opponentUsername;
			this.won = won;
		}

		@Override
		protected GameEntry clone() {
			return new GameEntry(this.opponent, this.won);
		}

		/**
		 * Compares two GameEntry objects (this object and the parameter) and
		 * discovers if the two objects are equal. This method is a deep
		 * compare, therefore it will compare the primitive variables composing
		 * these objects.
		 * 
		 * @param entry
		 *            The GameEntry object to compare against this GameEntry
		 *            object.
		 * @return A boolean variable of true if the two objects are equal, else
		 *         false
		 */
		protected boolean equals(GameEntry entry) {
			boolean opponentSame = this.opponent.equals(entry.opponent);
			boolean resultSame = this.won == entry.won;
			return opponentSame && resultSame;
		}

		/**
		 * A basic getter method. Returns the opponent of this GameEntry
		 * belonging to this {@link User}.
		 * 
		 * @return A String object of the opponents username.
		 */
		protected String getOpponent() {
			return opponent;
		}

		/**
		 * Basic setter. Sets the opponent in this GameEntry. Parameter should
		 * not have this {@link User}'s username. Should contain only the
		 * opponent's.
		 * 
		 * @param opponent
		 *            The String of the opponents name.
		 */
		protected void setOpponent(String opponent) {
			this.opponent = opponent;
		}

		/**
		 * Basic getter. Returns the win status of this GameEntry belonging to
		 * this {@link User}.
		 * 
		 * @return The result of this game entry (win, loss)
		 */
		protected boolean isWon() {
			return won;
		}

		/**
		 * Basic setter. Sets the win status of this GameEntry. Parameter should
		 * be true if this user won, false if not.
		 * 
		 * @param won
		 *            The win status of this {@link User}.
		 */
		protected void setWon(boolean won) {
			this.won = won;
		}
	}

	/**
	 * Compares the Users in a LinkedList of Users according to the User's total wins
	 * @author Joanna
	 *
	 */
	public static class Comparators {

		public static Comparator<User> Wins = new Comparator<User>() {
			@Override
			public int compare(User user1, User user2) {
				return user2.getTotalWins() - user1.getTotalWins();
			}
		};
	}
	@Override
	public int compareTo(User arg0) {
		return 0;
	}
}

/**
 * reviewed: Mike
 * 
 */

package Backend;

import java.util.LinkedList;


public class User {
	private String username;
	private String password;
	private int win;
	private int loss;
	//TODO what is gameHistory ,please comment
	private LinkedList<GameEntry> gameHistory;
	private UserDataBase userDB;
	
	public User() {
		this.username=null;
		this.password=null;
		this.win=0;
		this.loss=0;
		this.gameHistory=new LinkedList<GameEntry>();
		this.userDB = new UserDataBase();
	}
	
	public User(User user) {
		this.username = user.username;
		this.password = user.password;
		this.win = user.win;
		this.loss = user.loss;
		
		/*FIXME is public User(User user) tested ?  
 * `this.gameHistory=user.gameHistory;` will not create a new gameHistory but point to a shared one ... 
 * which means that if you change one obj the other one will be affected as well which is not good.*/ 
		
		this.gameHistory=user.gameHistory;
		this.userDB = new UserDataBase();
	}

	public User(String username, String password) {
		this.username=username;
		this.password=password;
		this.win=0;
		this.loss=0;
		
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

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLoss() {
		return loss;
	}

	public void setLoss(int loss) {
		this.loss = loss;
	}
	//TODO comment on the possible use cases  
	public UserDataBase getUserDB() {
		return userDB;
	}
	//TODO comment on the possible use cases  
	public void setUserDB(UserDataBase userDB) {
		this.userDB = userDB;
	}
	
	public void addGameResult(User opponent, boolean won) {
		gameHistory.add(new GameEntry(opponent, won));
		userDB.modifyUser(this);
	}
	
	
	//TODO possible overlaps with GameEntry (user ,win) 
	// it's not good to have other ppl remember to increment in the GameEntry class as well as increment the Win number 
	// in the User class. if this is abs necessary,then have a function to do the increment implicitly for both.
	public void incrementWin() {
		this.win += 1;
	}
	//TODO same as abv 
	public void incrementLoss() {
		this.loss += 1;
	}
	
	public double getWinLoseRation() {
		return (double)win/loss;
	}
	
	//TODO override hash as well. linked list are not covered in the following 
	//TODO get permission of team. Update team/UML.
	public boolean equals(User user){
		boolean userSame = username.equals(user.username);
		boolean passSame = password.equals(user.password);
		boolean winSame = win==user.win;
		boolean lossSame = loss==user.loss;
		return userSame && passSame && winSame && lossSame;
	}
	
	public class GameEntry {
		private User opponent;
		private boolean won;
		
		public GameEntry(User opponent, boolean won) {
			this.opponent = opponent;
			this.won = won;
		}

		public User getOpponent() {
			return opponent;
		}

		public void setOpponent(User opponent) {
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

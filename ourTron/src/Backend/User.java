
//
public class User {
	private String username;
	private String password;
	private int win;
	private int loss;
	//private Date playingTime;pp
//	private boolean validUser;
	private UserDataBase userDataBase;
	
	public User() {
		this.username=null;
		this.password=null;
		this.win=0;
		this.loss=0;
//		this.validUser=false;
//		this.userDataBase=null;
	}

	public User(String username, String password, UserDataBase userDataBase) {
		this.username=username;
		this.password=password;
		this.win=0;
		this.loss=0;
//		this.validUser=true;
		this.userDataBase=userDataBase;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public int getWin() {
		return win;
	}

	public int getLoss() {
		return loss;
	}

	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void incrementWin() {
		this.win += 1;
	}
	
	public void incrementLoss() {
		this.loss += 1;
	}
	
	public double getWinLoseRation() {
		return (double)win/loss;
	}
	
	//TODO figure out what this is
//	public User getUser(String username) {}
	
	
	//TODO get permission of team. Update team/UML.
	public boolean equals(User user){
		boolean userSame = username.equals(user.username);
		boolean passSame = password.equals(user.password);
		boolean winSame = win==user.win;
		boolean lossSame = loss==user.loss;
		return userSame && passSame && winSame && lossSame;
	}
}


public class User {
	private String username;
	private String password;
	private int win;
	private int loss;
	//private Date playingTime;pp
//	private boolean validUser;
	private UserDataBase userDataBase;

	public User(String username, String password, UserDataBase userDataBase) {
		this.username=username;
		this.password=password;
		this.win=0;
		this.loss=0;
//		this.validUser=true;
		this.userDataBase=userDataBase;
	}
	
	//TODO get permission of team. Update team/UML.
	public boolean equals(User user){
		boolean userSame = username.equals(user.username);
		boolean passSame = password.equals(user.password);
		boolean winSame = win==user.win;
		boolean lossSame = loss==user.loss;
		return userSame && passSame && winSame && lossSame;
	}
}

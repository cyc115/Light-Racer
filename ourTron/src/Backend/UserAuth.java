//
public class UserAuth {
	private UserDataBase userDB;
	
	public UserAuth(UserDataBase userDB) {
		this.userDB=userDB;
	}
	
	public boolean isValidInput(String username, String password) {
		User user = userDB.retriveUser(username);
		if(user.getUsername().equals(username) && user.getPassword().equals(password))
			return true;
		else
			return false;
	}
	
	public boolean isRegistered(String username) {
		if(userDB.retriveUser(username) != null)
			return true;
		else
			return false;
	}

}

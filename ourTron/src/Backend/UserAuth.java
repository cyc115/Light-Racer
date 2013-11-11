package Backend;

public class UserAuth {
	private UserDataBase userDB;
	
	public UserAuth(UserDataBase userDB) {
		this.userDB=userDB;
	}
	
	/**Returns a user if the input of username and password matches the credentials of that user in the database
	 * <p>
	 * @param username	A String object containing the username
	 * @param password	A String object containing the password
	 * @return	A user object associated to the username if and only if the password is also correct. Otherwise null is returned. 
	 */
	public User isValidInput(String username, String password) {
		User user = userDB.retrieveUser(username);
		if(user.getUsername().equals(username) && user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	/**Returns whether the username exists in the database
	 * @param username	A string object contianing the username
	 * @return	A boolean variable which is true if the username exists in the database. Otherwise false. 
	 */
	public boolean isRegistered(String username) {
		if(userDB.retrieveUser(username) != null)
			return true;
		else
			return false;
	}

}
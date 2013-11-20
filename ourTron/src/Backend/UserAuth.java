package Backend;

public class UserAuth {
	//unique instance. 
	private static UserDataBase userDB = new UserDataBase();
	
	/**Returns a user if the input of username and password matches the credentials of that user in the database
	 * <p>
	 * @param username	A String object containing the username
	 * @param password	A String object containing the password
	 * @return	A user object associated to the username if and only if the password is also correct. Otherwise null is returned. 
	 */
	public static User isValidInput(String username, String password) {
		User user = UserDataBase.retrieveUser(username);
		if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
			return user;
		}
		else {
			return null;
		}
	}
	
	/**Returns whether the username exists in the database
	 * @param username	A string object contianing the username
	 * @return	A boolean variable which is true if the username exists in the database. Otherwise false. 
	 */
	public static boolean isRegistered(String username) {
		if(UserDataBase.retrieveUser(username) != null) {
			return true;
		}
		else {
			return false;
		}
	}

}

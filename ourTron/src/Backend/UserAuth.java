package Backend;

public class UserAuth {
	/**
	 * This static method will compare the user credentials passed into this
	 * method against that in the database. It will attempt to find a
	 * {@link User} with a matching username and, if successful, will identify
	 * if the password in the database matches that which is input. It will
	 * return the instance of the user in the database.
	 * <p>
	 * This method does not guarantee results if the user does not exist in the
	 * database. That functionality is handled by the method isRegistered().
	 * This method also does not guarantee results if more than one user share
	 * the same username.
	 * 
	 * @param username
	 *            A String object containing the username
	 * @param password
	 *            A String object containing the password
	 * @return Returns a {@link User} object if the input of username and
	 *         password matches the credentials of that user in the database.
	 *         Otherwise null is returned.
	 */
	public static User isValidInput(String username, String password) {
		User user = UserDataBase.retrieveUser(username);
		// compare database credentials to parameters
		if (user.getUsername().equals(username)
				&& user.getPassword().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

	/**
	 * This static method will compare the username input with all the
	 * {@link User} objects in the database in order to find a match. If a user
	 * with the matching username is found, the method return true.
	 * <p>
	 * This method assumes the unique identifier to every user is the username.
	 * It will not guarantee results if more than one user share an identical
	 * username.
	 * 
	 * @param username
	 *            A string object containing the username
	 * @return A boolean variable which is true if the username exists in the
	 *         database. Otherwise false.
	 */
	public static boolean isRegistered(String username) {
		// null means the user does not exist in the database.
		if (UserDataBase.retrieveUser(username) != null) {
			return true;
		} else {
			return false;
		}
	}

}

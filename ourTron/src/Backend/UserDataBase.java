package Backend;

import java.io.*;
import java.util.LinkedList;

//
/**
 * This class is easily extensible to allow temporary users as this class will
 * simply not write/read to a file but instead just keep the temporary user in
 * the linked list.
 */
public class UserDataBase {

	public static boolean doesUserExist(String username) {
		if(retrieveUser(username) != null)
			return true;
		else
			return false;
	}
	
	/**
	 * This method will return a {@link User} object corresponding to the user input in the method. 
	 * If the user does not exist in the database, null is returned.
	 * <p>
	 * @param username	A string of the unique username
	 * @return 	A user object if the user is found, otherwise null is returned
	 */
	public static User retrieveUser(String username) {
		for(User thisUser : UserDataBaseWriter.readFromFile())
			if(thisUser.getUsername().equals(username)) {
				return thisUser;
			}
		return null;
	}
	
	/**
	 * This method will return a {@link User} object corresponding to the user input in the method. 
	 * If that user does not exist in the database, null is returned
	 * <p>
	 * @param user	A user object 
	 * @return	Null if the user does not exist in the database, otherwise the User object in the database.
	 */
	public static User retrieveUser(User user) {
		for(User thisUser : UserDataBaseWriter.readFromFile())
			if(thisUser.equals(user)) {
				return thisUser;
			}
		return null;
		
	}
	
	/**
	 * Adds a {@link User} object to the database and updates the file on the hard drive.
	 * Only does so if the username does not already exist in the database
	 * <p>
	 * @param user	The user to be added
	 */
	public static void addUser(User user) {
		// if the user is not found, add the user and write to file. 
		if(retrieveUser(user.getUsername()) == null) {
			LinkedList<User> db = UserDataBaseWriter.readFromFile();
			db.add(user);
			System.out.println("User added: " + user);
			UserDataBaseWriter.writeToFile(db);
			return;
		} else
			return;
	}
	
	//TODO TEST THIS METHOD.
	/**
	 * This method takes in a {@link User} object and sets the entry in the database to that user object passed in.
	 * This will prove useful when updating user information. This method assumes the username is not changed. Therefore usernames cannot change.
	 * @param user	A user object to be modified to
	 */
	public static void modifyUser(User user) {
		//get the user object
		LinkedList<User> db = UserDataBaseWriter.readFromFile();
		for(User oldEntry : db)
			if(oldEntry.getUsername().equals(user.getUsername())) {
				db.remove(oldEntry);
				db.add(user);
				UserDataBaseWriter.writeToFile(db);
				return;
			}
		return;
	}
}

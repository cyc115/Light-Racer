package Backend;

import java.io.*;
import java.util.LinkedList;

//
/**
 * This class is easily extensible to allow temporary users as this class will
 * simply not write/read to a file but instead just keep the temporary user in
 * the linked list.
 */
public class UserDataBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<User> library;
	private static UserDataBaseWriter dbWriter = new UserDataBaseWriter();

	public UserDataBase() {
		//if the file does not exist, create it. Otherwise load it up.
		if(dbWriter.readFromFile() == null) {
			this.library = new LinkedList<User>();
			dbWriter.writeToFile(this);
		}
		else
			this.library = dbWriter.readFromFile().getLibrary();
	}
	
	/**
	 * Gets the library within the {@link UserDataBase} file. 
	 * @return	A LinkedList of User objects corresponding to the library. 
	 */
	public LinkedList<User> getLibrary() {
		return library;
	}

	/**
	 * Sets the library to an input.
	 * @param library	A linked list of Users. 
	 */
	public void setLibrary(LinkedList<User> library) {
		this.library = library;
	}

	/**
	 * This method will return a {@link User} object corresponding to the user input in the method. 
	 * If the user does not exist in the database, null is returned.
	 * <p>
	 * @param username	A string of the unique username
	 * @return 	A user object if the user is found, otherwise null is returned
	 */
	public User retrieveUser(String username) {
		for(User thisUser : library)
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
	public User retrieveUser(User user) {
		for(User thisUser : library)
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
	public void addUser(User user) {
		// if the user is not found, add the user and write to file. 
		if(retrieveUser(user.getUsername()) == null) {
			library.add(user);
			System.out.println("User added: " + user);
			dbWriter.writeToFile(this);
		} else {
			System.err.print("User already exists in the Database");
		}
	}

	//TODO TEST THIS METHOD.
	/**
	 * This method takes in a {@link User} object and sets the entry in the database to that user object passed in.
	 * This will prove useful when updating user information. This method assumes the username is not changed. Therefore usernames cannot change.
	 * @param user	A user object to be modified to
	 */
	public void modifyUser(User user) {
		//get the user object
		for(User oldEntry : library)
			if(oldEntry.getUsername().equals(user.getUsername())) {
				library.remove(oldEntry);
				library.add(user);
				dbWriter.writeToFile(this);
				return;
			}
		return;
	}
	
	public void printDB() {
		for(User thisUser : library)
			System.out.println(thisUser);
	}

}

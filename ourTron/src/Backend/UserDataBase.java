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
		// if the user is not found in database and the password is valid, add the user and write to file. 
		if(retrieveUser(user.getUsername()) == null && isValidPassword(user.getPassword())) {
			LinkedList<User> db = UserDataBaseWriter.readFromFile();
			db.add(user);
			UserDataBaseWriter.writeToFile(db);
			return;
		} else
			return;
	}
	
	/**
	 * Checks if the password is valid or not based on the specifications. A valid password is an alphanumeric only password of at least 6 characters in length
	 * @param password	The password to check
	 * @return	True if the password is valid, else false;
	 */
	public static boolean isValidPassword(String password) {
		if(password.length() < 6)
			return false;
		else {
			char[] pass = password.toCharArray();
			for(char c : pass) {
				if(!Character.isLetter(c) && !Character.isDigit(c))
					return false;
			}
			return true;
		}
		
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
	
	private static class UserDataBaseWriter {
		private static String location = "UserDataBase.data";

		/**
		 * This method reads from a file that is serialized. It must be in the
		 * UserDataBase format. Therefore, it must be a created .data file for it to
		 * read properly. 
		 * <p>
		 * @return A {@link UserDataBase} object corresponding to the database, or null if the input is invalid.
		 */
		
		/*
		 * FIXME this might not work with adding a win to the user, because of the IOException creating a new DB. This will be debugged when playerstatistics is implemented. 
		 */
		public static LinkedList<User> readFromFile() {
			try {
				FileInputStream fileIn = new FileInputStream(location);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				@SuppressWarnings("unchecked")
				LinkedList<User> db = (LinkedList<User>) in.readObject();
				in.close();
				fileIn.close();
				return db;
			} catch (FileNotFoundException e) {
				writeToFile(new LinkedList<User>());
				return readFromFile();
			} catch (IOException i) {
				writeToFile(new LinkedList<User>());
				return readFromFile();
			} catch (ClassNotFoundException c) {
				System.err.println("Exception Thrown");
				return null;
			}
		}

		/**
		 * This method writes to a file in a serialized fashion. The location of
		 * output is defined in the instance of this object.
		 * 
		 * @param db
		 *            A {@link UserDataBase} object that must be written to a file.
		 */
		public static void writeToFile(LinkedList<User> db) {
			try {
				FileOutputStream fileOut = new FileOutputStream(location);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(db);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				//IOException not expected. 
			}
		}
	}
}

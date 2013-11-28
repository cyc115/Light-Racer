package Backend;

import java.io.*;
import java.util.LinkedList;

/**
 * This class is the abstraction for a database containing {@link User} objects.
 * Every function conducted to users such as modifying them or retrieving them
 * are done through this abstraction.
 * <p>
 * The abstraction ensures a single copy of the UserDataBase exists at any given
 * moment by reading from and writing to the file system at every operation.
 * This ensures data integrity.
 */
public class UserDataBase {
	/**
	 * The method identifies if a {@link User} exists in the file system
	 * database based upon its uniquely identifying username.
	 * 
	 * @param username
	 *            A String object with the unique username.
	 * @return The boolean true if the user exists in the database, false
	 *         otherwise.
	 */
	public static boolean doesUserExist(String username) {
		if (retrieveUser(username) != null)
			return true;
		else
			return false;
	}

	/**
	 * This method will return a {@link User} object corresponding to the
	 * username input in the method.
	 * 
	 * @param username
	 *            A string of the unique username
	 * @return A user object if the user is found in the database, otherwise
	 *         null is returned
	 */
	public static User retrieveUser(String username) {
		// Search the database and return the found user right away
		for (User thisUser : UserDataBaseWriter.readFromFile())
			if (thisUser.getUsername().equals(username)) {
				return thisUser;
			}
		// if the user was not found, return null.
		return null;
	}

	/**
	 * This method will return a {@link User} object corresponding to the user
	 * object input in the method. It performs a deep compare to do this
	 * identification.
	 * 
	 * @param user
	 *            A user object to find.
	 * @return Null if the user does not exist in the database, otherwise the
	 *         User object in the database.
	 */
	public static User retrieveUser(User user) {
		for (User thisUser : UserDataBaseWriter.readFromFile())
			if (thisUser.equals(user)) {
				return thisUser;
			}
		return null;
	}

	/**
	 * Adds a {@link User} object to the database and updates the file on the
	 * hard drive immediately. Only does so if the username does not already
	 * exist in the database.
	 * 
	 * @param user
	 *            The user to be added
	 */
	public static void addUser(User user) {
		boolean doesUserExist = doesUserExist(user.getUsername());
		boolean isPasswordValid = isValidPassword(user.getPassword());
		// if user doesn't exist in database and password is valid, add the
		// user to the database.
		if (!doesUserExist && isPasswordValid) {
			LinkedList<User> db = UserDataBaseWriter.readFromFile();
			db.add(user);
			// write to filesystem immediately.
			UserDataBaseWriter.writeToFile(db);
			return;
		} else
			return;
	}

	/**
	 * Checks if the password is valid or not based on the specifications. A
	 * valid password satisfies each of the following properties: 1. Must
	 * contain 8 characters 2. Must contain at least one lower case letter 3.
	 * Must contain at least one upper case letter 4. Must contain at least one
	 * number 5. Must contain at least one symbol (non-alphanumeric).
	 * 
	 * @param password
	 *            A String object containing the password to check
	 * @return true if the password is valid, else false;
	 */
	public static boolean isValidPassword(String password) {
		if (password.length() < 8)
			return false;
		else {
			char[] pass = password.toCharArray();
			boolean lowerCaseFound = false;
			boolean upperCaseFound = false;
			boolean numberFound = false;
			boolean symbolFound = false;

			for (char c : pass) {
				if (Character.isLetter(c))
					if (Character.isUpperCase(c))
						upperCaseFound = true;
					else
						lowerCaseFound = true;
				else if (Character.isDigit(c))
					numberFound = true;
				else
					symbolFound = true;
			}
			// if one of each requirement is found, returns true
			return lowerCaseFound && upperCaseFound && numberFound
					&& symbolFound;
		}

	}

	/**
	 * This method takes in a {@link User} object and sets the entry in the
	 * database to that user object passed in. This method overwrites all
	 * existing data for that user in the database with the parameter data.
	 * <p>
	 * This method assumes the username of the input user will be the user to
	 * search for. This method performs it's search based on the username.
	 * 
	 * @param user
	 *            A user object containing all the items to change to
	 */

	public static void modifyUser(User user) {
		LinkedList<User> db = UserDataBaseWriter.readFromFile();
		for (int i = 0; i < db.size(); i++)
			if (db.get(i).getUsername().equals(user.getUsername())) {
				db.set(i, user);
			}
		UserDataBaseWriter.writeToFile(db);
		return;
	}

	/**
	 * Returns a {@link}LinkedList of all of the Users in the database.
	 * @returns the LinkedList of Users.
	 */
	public static LinkedList<User> getAllUsers() {
		LinkedList<User> allUsers = new LinkedList<User>();
		for (User thisUser : UserDataBaseWriter.readFromFile()) {
			allUsers.add(thisUser);
		}
		return allUsers;
	}

	/**
	 * This class is the abstraction of the database I/O. It performs all the
	 * writing to the file system and retrieving from the filesystem. It is
	 * entirely safe to call all methods in this class at any time, as
	 * exceptions should always be handled.
	 * <p>
	 * This writer will always write to the file UserDataBase.data within the
	 * present working directory. If that file does not exist in that location,
	 * it will create the file.
	 */
	public static class UserDataBaseWriter {
		private static String location = "UserDataBase.data";

		/**
		 * This method reads from a file that is serialized. It must be in the
		 * UserDataBase format. Therefore, it must be a created .data file that
		 * is a serialized LinkedList of Users for it to read properly.
		 * 
		 * @return A {@link LinkedList<User>} object corresponding to the
		 *         database, or null if the input is invalid.
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
				writeToFile(new LinkedList<User>());
				return readFromFile();
			}
		}

		/**
		 * This method writes the parameter to a file in a serialized fashion.
		 * 
		 * @param db
		 *            A {@link LinkedList<User>} object that must be written to
		 *            a file.
		 */
		public static void writeToFile(LinkedList<User> db) { //TODO: use this to clear database
			try {
				FileOutputStream fileOut = new FileOutputStream(location);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(db);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				System.err.println("ERROR");
				// IOException not expected.
			}
		}
	}
}

import java.io.*;
import java.util.LinkedList;

/**
 * This class is easily extensible to allow temporary users as this class will
 * simply not write/read to a file but instead just keep the temporary user in
 * the linked list.
 */
public class UserDataBase {
	private LinkedList<User> library;
//	private CSVDataBaseLibrary db;
	private String location;

	UserDataBase() {
		location = "UserDataBase.info";
//		this.db = null;
		library = new LinkedList<User>();
	}

	// TODO get this method signature validated
	UserDataBase(String location, boolean create) {
		this.location=location;
		
		// create a new file with nothing in it
		if(create) {
			library = new LinkedList<User>();
			writeToFile();
		}
		//read from an existing file
		else
			readFromFile();
	}
	
	public User retriveUser(String username) {
		for(User thisUser : library)
			if(thisUser.getUsername().equals(username))
				return thisUser;
		return null;
	}
	
	//TODO get this name changed
	/**This method will return a {@link User} object corresponding to the user input in the method. 
	 * If that user does not exist in the database, null is returned
	 * <p>
	 * @param user	A user object 
	 * @return	Null if the user does not exist in the database, otherwise the User object in the database.
	 */
	public User retriveUser(User user) {
		for(User thisUser : library)
			if(thisUser.equals(user))
				return thisUser;
		return null;
	}
	
	/**Adds a {@link User} object to the database and updates the file on the hard drive.
	 * Only does so if the username does not already exist in the database
	 * <p>
	 * @param user	The user to be added
	 */
	public void addUser(User user) {
		if(retriveUser(user.getUsername()) == null) {
			library.add(user);
			writeToFile();
		} else {
			System.err.print("User already exists in the Database. Exiting");
		}
	}

	@SuppressWarnings("unchecked")
	public void readFromFile() {
		try {
			FileInputStream fileIn = new FileInputStream(location);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.library = (LinkedList<User>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("The location does not have a valid file");
			c.printStackTrace();
			return;
		}
	}

	/**
	 * This method writes to a file in a serialized fashion. The location of
	 * output is defined in the instance of this object
	 */
	public void writeToFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream(location);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(library);
			out.close();
			fileOut.close();
			System.out.printf("Your data has been saved in " + location);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}

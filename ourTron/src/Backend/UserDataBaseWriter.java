package Backend;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

//TODO move the writing to this class!!
//This class will need to change name
public class UserDataBaseWriter {
	private String location;

	public UserDataBaseWriter() {
		this.location = "UserDataBase.data";
	}

	public UserDataBaseWriter(String location) {
		// this.library = new LinkedList<User>();
		this.location = location;
	}

	/**
	 * This method reads from a file that is serialized. It must be in the
	 * UserDataBase format. Therefore, it must be a created .data file for it to
	 * read properly. 
	 * <p>
	 * @return A {@link UserDataBase} object corresponding to the database, or null if the input is invalid.
	 */
	public UserDataBase readFromFile() {
		try {
			FileInputStream fileIn = new FileInputStream(location);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			UserDataBase library = (UserDataBase) in.readObject();
			in.close();
			fileIn.close();
			return library;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("The location does not have a valid file");
			c.printStackTrace();
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
	public void writeToFile(UserDataBase db) {
		try {
			FileOutputStream fileOut = new FileOutputStream(location);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(db);
			out.close();
			fileOut.close();
			System.out.printf("Your data has been saved in " + location);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
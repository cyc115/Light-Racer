package Backend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

//TODO move the writing to this class!!
//This class will need to change name
public class UserDataBaseWriter {
	private static String location = "UserDataBase.data";


	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		UserDataBaseWriter.location = location;
	}

	/**
	 * This method reads from a file that is serialized. It must be in the
	 * UserDataBase format. Therefore, it must be a created .data file for it to
	 * read properly. 
	 * <p>
	 * @return A {@link UserDataBase} object corresponding to the database, or null if the input is invalid.
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
			return null;
		} catch (ClassNotFoundException c) {
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
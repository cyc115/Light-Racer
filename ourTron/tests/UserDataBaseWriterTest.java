/**
 * 
 */

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Backend.User;
import Backend.UserDataBase.UserDataBaseWriter;

/**
 *
 */
public class UserDataBaseWriterTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Backend.UserDataBaseWriter#readFromFile()}.
	 */
	@Test
	public final void testReadFromFile() {
		LinkedList<User> db = new LinkedList<User>();
		User user1 = new User();
		user1.setUsername("Hello");
		user1.setPassword("ECSEecse321!");
		db.add(user1);
		UserDataBaseWriter.writeToFile(db);
		LinkedList<User> newDB = UserDataBaseWriter.readFromFile();
		int i = 0;
		boolean equal = true;
		for(User user : db) {
			equal = equal && newDB.get(i).equals(user);
			++i;
		}
		assertTrue("The db's are not equal",equal);
	}
	
	

	/**
	 * Test method for {@link Backend.UserDataBaseWriter#writeToFile(java.util.LinkedList)}.
	 */
	@Test
	
	public final void testWriteToFile() {
		LinkedList<User> db = new LinkedList<User>();
		User user1 = new User();
		user1.setUsername("Hello");
		user1.setPassword("ECSEecse321!");
		db.add(user1);
		UserDataBaseWriter.writeToFile(db);
		LinkedList<User> newDB = UserDataBaseWriter.readFromFile();
		int i = 0;
		boolean equal = true;
		for(User user : db) {
			equal = equal && newDB.get(i).equals(user);
			++i;
		}
		assertTrue("The db's are not equal",equal);
	}

}

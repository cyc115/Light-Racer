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
import Backend.UserAuth;
import Backend.UserDataBase.UserDataBaseWriter;

/**
 * 
 */
public class UserAuthTest {
	static User user1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user1 = new User("User", "ECSEecse321!");
		LinkedList<User> db = new LinkedList<User>();
		db.add(user1);
		UserDataBaseWriter.writeToFile(db);
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
	 * Test method for
	 * {@link Backend.UserAuth#isValidInput(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testIsValidInput() {
		assertTrue(
				"The information input did not match the userDB, something went wrong",
				user1.equals(UserAuth.isValidInput(user1.getUsername(), user1.getPassword())));
	}

	/**
	 * Test method for {@link Backend.UserAuth#isRegistered(java.lang.String)}.
	 */
	@Test
	public void testIsRegistered() {
		assertTrue("The user does not exist in the database",
				UserAuth.isRegistered(user1.getUsername()));
	}

}

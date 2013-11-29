/**
 * 
 */
package Backend;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Backend.UserDataBase.UserDataBaseWriter;

/**
 * @author Joanna
 *
 */
public class UserDataBaseTest {
	User user1;
	User user2;

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
		//create a new database. 
		LinkedList<User> db = new LinkedList<User>();
		user1 = new User("Demo1", "ECSEecse321!");
		user2 = new User("Demo2", "ECSEecse321!");
		user1.addGameResult(user2, true);
		user2.addGameResult(user1, false);
		db.add(user1);
		db.add(user2);
		
		UserDataBaseWriter.writeToFile(db);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Backend.UserDataBase#doesUserExist(java.lang.String)}.
	 */
	@Test
	public final void testDoesUserExist() {
		assertTrue("User was not found in the database",UserDataBase.doesUserExist("Demo1"));
	}

	/**
	 * Test method for {@link Backend.UserDataBase#retrieveUser(java.lang.String)}.
	 * This test tests that a user is retrieved from the database. 
	 */
	@Test
	public final void testRetrieveUserString1() {
		User found = UserDataBase.retrieveUser("Demo1");
		assertNotNull("No user was found in the database", found);
	}
	
	/**
	 * Test method for {@link Backend.UserDataBase#retrieveUser(java.lang.String)}.
	 * This method tests if the two users (the one creates and the one found in the database) are equal. 
	 */
	@Test
	public final void testRetrieveUserString2() {
		User equalFound = UserDataBase.retrieveUser("Demo1");
		boolean equal = equalFound.equals(user1);
		User unequalFound = UserDataBase.retrieveUser("Demo2");
		boolean unequal = unequalFound.equals(user1);
		assertTrue("A user was found, but not the user it should have been",equal && !unequal);
	}

	/**
	 * Test method for {@link Backend.UserDataBase#retrieveUser(Backend.User)}.
	 */
	@Test
	public final void testRetrieveUserUser() {
		User found = UserDataBase.retrieveUser(user1);
		boolean equal = found.equals(user1);
		User unequalFound = UserDataBase.retrieveUser(user2);
		boolean unequal = unequalFound.equals(user1);
		assertTrue("A user was found, but not the user it should have been",equal && !unequal);
	}

	/**
	 * Test method for {@link Backend.UserDataBase#addUser(Backend.User)}.
	 */
	@Test
	public final void testAddUser() {
		User user3 = new User("Demo3", "ECSEecse321!");
		UserDataBase.addUser(user3);
		User test = UserDataBase.retrieveUser(user3);
		assertTrue("The added user is not equal to itself", user3.equals(test));
	}

	/**
	 * Test method for {@link Backend.UserDataBase#isValidPassword(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPassword() {
		boolean tooShort = ! UserDataBase.isValidPassword("Ee1!");
		boolean noLowerCase = ! UserDataBase.isValidPassword("ECSE321!");
		boolean noUpperCase = ! UserDataBase.isValidPassword("ecse321!");
		boolean noNumberCase = ! UserDataBase.isValidPassword("ecseECSE!");
		boolean noSymbol = ! UserDataBase.isValidPassword("ECSEecse321");
		boolean valid = UserDataBase.isValidPassword("ECSEecse321!");
		assertTrue("The password checker meets specifications",tooShort && noLowerCase && noUpperCase && noNumberCase && noSymbol && valid);
	}

	/**
	 * Test method for {@link Backend.UserDataBase#modifyUser(Backend.User)}.
	 */
	@Test
	public final void testModifyUser() {
		User user4 = new User(user2.getUsername(), "NEWnewECSEecse321!");
		UserDataBase.modifyUser(user4);
		User retrived = UserDataBase.retrieveUser(user4.getUsername());
		assertEquals("The user was not modified properly", "NEWnewECSEecse321!", retrived.getPassword());
	}

	/**
	 * Test method for {@link Backend.UserDataBase#getAllUsers()}.
	 */
	@Test
	public final void testGetAllUsers() { //TODO
		LinkedList<User> actual = UserDataBase.getAllUsers();
		LinkedList<User> expected = new LinkedList<User>();
	}
}

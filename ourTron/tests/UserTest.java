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

import Backend.User.GameEntry;

/**
 * @author Joanna
 * 
 */
public class UserTest {
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
		user1 = new User("Demo1", "ECSEecse321!");
		user2 = new User("Demo2", "ECSEecse321!");
		
		LinkedList<GameEntry> entries = new LinkedList<GameEntry>();
		GameEntry entry = user2.new GameEntry("Demo2", true);
		entries.add(entry);
		user2.setGameHistory(entries);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Backend.User#User()}.
	 */
	@Test
	public final void testUser1() {
		User user3 = new User();
		boolean sameUsername = user3.getUsername() == null;
		boolean samePassword = user3.getPassword() == null;
		boolean sameHistory = true;
		
		LinkedList<GameEntry> compared = new LinkedList<GameEntry>();
		int i = 0;
		for(GameEntry entry : user3.getGameHistory()) {
			sameHistory = entry.equals(compared.get(i));
			++i;
		}
		assertTrue(
				"The default constructor for User is not what its supposed to be",
				sameUsername && samePassword && sameHistory);
	}
	
	/**
	 * Test method for {@link Backend.User#User(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUser2() {
		User user3 = new User("Demo1", "ECSEecse321!");
		
		boolean sameUsername = user3.getUsername().equals("Demo1");
		boolean samePassword = user3.getPassword().equals("ECSEecse321!");
		boolean sameHistory = true;
		
		LinkedList<GameEntry> compared = new LinkedList<GameEntry>();
		int i = 0;
		for(GameEntry entry : user3.getGameHistory()) {
			sameHistory = sameHistory && entry.equals(compared.get(i));
			++i;
		}
		
		assertTrue(
				"The constructor for User is not what its supposed to be",
				sameUsername && samePassword && sameHistory);
	}
	
	/**
	 * Test method for {@link Backend.User#User(Backend.User)}.
	 */
	@Test
	public final void testUser3() {
		User user3 = new User(user2);
		
		assertTrue(
				"The default constructor for User is not what its supposed to be",
				user3.equals(user2));
	}

	/**
	 * Test method for {@link Backend.User#addGameResult(Backend.User, boolean)}
	 * .
	 */
	@Test
	public final void testAddGameResult() {
		user1.addGameResult(user2, true);
		
		User user3 = new User(user1.getUsername(), user1.getPassword());
		
		LinkedList<GameEntry> entries = new LinkedList<GameEntry>();
		GameEntry gameEntry = user3.new GameEntry(user2.getUsername(), true);
		entries.add(gameEntry);
		user3.setGameHistory(entries);
		
		assertTrue(
				"The default constructor for User is not what its supposed to be",
				user1.equals(user3));
	}

	/**
	 * Test method for {@link Backend.User#getWinsVsOpponent(Backend.User)}.
	 */
	@Test
	public final void testGetWinsVsOpponent() {
		User user3 = new User("Demo3","ECSEecse321!");
		
		user3.addGameResult(user1, true);
		user1.addGameResult(user3, false);
		
		int u3wins = user3.getWinsVsOpponent(user1);
		int u1wins = user1.getWinsVsOpponent(user3);
		
		assertTrue("The wins versus the opponets were not as expected", u3wins == 1 && u1wins == 0);
	}

	/**
	 * Test method for {@link Backend.User#getTotalWins()}.
	 */
	@Test
	public final void testGetTotalWins() {
		user2.addGameResult(user1, true);
		user2.addGameResult(user1, false);
		
		assertEquals("The number of wins user2 has does not match the expected value", 1, user2.getWinsVsOpponent(user1));
	}

}

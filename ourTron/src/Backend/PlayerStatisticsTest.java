/**
 * 
 */
package Backend;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * @author Joanna </br>
 *
 *Here are the tests for the Head-to-head scores (called user1VsUser2Wins()) and test for Top10Users.
 *
 *For the head-to-head scores, I chose to test Demo01 vs Demo06 since I knew their scores were 2-5.
 *Then I tested Demo01 vs Demo06 because they never played games against each other so their score were 0-0.
 *The I tested blank names to make sure the score would come up as 0-0.
 *
 *For the Top10Users I just tested that the expected demo listings were returned.
 */
public class PlayerStatisticsTest {


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
	 * Test method for {@link Backend.PlayerStatistics#user1VsUser2Wins(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUser1VsUser2WinsForDemo01AndDemo05() {
		String actual = PlayerStatistics.user1VsUser2Wins("Demo01", "Demo05");
		String expected = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Head-To-Head Scores</font></h1>"
				+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'><table align='center'>"
				+ "<tr><td>" + "Demo01" + "</td><td>-------------------</td><td>" + "Demo05" + "</td></tr>"
				+ "<tr><td><center>" + "2" + "</center></td><td> </td><td><center>" + "5" + "</center</td></tr>"
				+ "</table><br/><br/></span></p>";
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testUser1VsUser2WinsForDemo01AndDemo06() {
		String actual = PlayerStatistics.user1VsUser2Wins("Demo01", "Demo06");
		String expected = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Head-To-Head Scores</font></h1>"
				+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'><table align='center'>"
				+ "<tr><td>" + "Demo01" + "</td><td>-------------------</td><td>" + "Demo06" + "</td></tr>"
				+ "<tr><td><center>" + "0" + "</center></td><td> </td><td><center>" + "0" + "</center</td></tr>"
				+ "</table><br/><br/></span></p>";
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testUser1VsUser2WinsForBlanks() {
		String actual = PlayerStatistics.user1VsUser2Wins("", "");
		String expected = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Head-To-Head Scores</font></h1>"
				+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'><table align='center'>"
				+ "<tr><td>" + "User 1" + "</td><td>-------------------</td><td>" + "User 2" + "</td></tr>"
				+ "<tr><td><center>" + "0" + "</center></td><td> </td><td><center>" + "0" + "</center</td></tr>"
				+ "</table><br/><br/></span></p>";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Backend.PlayerStatistics#top10Users()}.
	 */
	@Test
	public final void testTop10Users() {
		String actual = PlayerStatistics.top10Users();
		String expectedTop10 = "1. Demo01: 8<br/>"
				+ "2. Demo06: 8<br/>"
				+ "3. Demo10: 5<br/>"
				+ "4. Demo05: 5<br/>"
				+ "5. Demo04: 4<br/>"
				+ "6. Demo09: 4<br/>"
				+ "7. Demo03: 3<br/>"
				+ "8. Demo08: 3<br/>"
				+ "9. Demo02: 2<br/>"
				+ "10. Demo07: 2<br/>";
		String expected = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Top 10 Users</font></h1>"
				+ "<p style='text-align: center;'><span style='font-family:trebuchet ms,helvetica,sans-serif,;'>" + expectedTop10 + "</span></p>";
		assertEquals(expected, actual);
	}

}

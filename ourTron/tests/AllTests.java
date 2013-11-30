import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Backend.Demo;
import Backend.User;
import Backend.UserDataBase;
import Backend.UserDataBase.UserDataBaseWriter;

/**
 * These are all of the unit tests for the backend. This includes the User, UserDataBase, and PlayerStatistics.
 * 
 * First this sets up the database according to the class demo specified by the professor. (The old database should be cleared/deleted before running this)
 */
@RunWith(Suite.class)
@SuiteClasses({ PlayerStatisticsTest.class, UserDataBaseTest.class,
		UserTest.class, MapTest.class, UserAuthTest.class, UserDataBaseWriterTest.class, GameLogicTest.class})
public class AllTests {

	 @BeforeClass //Master Setup
	 public static void setUpClass() {   
		 ///TODO: Clear existing database 
		 UserDataBaseWriter.writeToFile(new LinkedList<User>());
		 Demo.generateDemoUsers();
	 }

	    @AfterClass //Master TearDown
	    public static void tearDownClass() { 
			 UserDataBase.UserDataBaseWriter.writeToFile(new LinkedList<User>());
	    }
	
	    /**
	     * This will add wins and losses to Users in the database. 
	     * This method is only used for testing purposes and is not needed for the game at all.
	     * 
	     * @param user1Name
	     * @param user2Name
	     * @param user1Wins
	     * @param user2Wins
	     */
		public static void addUserHistory(User user1, User user2, int user1Wins, int user2Wins){
			if(user1 !=null && user2 != null){
				for(int i=0; i<user1Wins; i++){
					user1.addGameResult(user2, true);
					user2.addGameResult(user1, false);
				}
				for(int i=0; i<user2Wins; i++){
					user1.addGameResult(user2, false);
					user2.addGameResult(user1, true);
				}
			}
		}
		/**
		 * This will remove the last specified number of games between 2 users
		 * @param user1Name
		 * @param user2Name
		 * @param GamesToRm
		 */
		public static void removeLastUserHistory(User user1, User user2, int GamesToRm){
			if(user1 !=null && user2 != null){
				for(int i=0; i<GamesToRm; i++){
					user1.removeLastGameResult();
					user2.removeLastGameResult();
				}
			}
		}
}

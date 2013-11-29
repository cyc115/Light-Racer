package Backend;

import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Joanna </br>
 * These are all of the unit tests for the backend. This includes the User, UserDataBase, and PlayerStatistics.
 * 
 *First this sets up the database according to the class demo specified by the professor. (The old database should be cleared/deleted before running this)
 */
@RunWith(Suite.class)
@SuiteClasses({ PlayerStatisticsTest.class, UserDataBaseTest.class,
		UserTest.class })
public class BackendTests {

	 @BeforeClass //Master Setup
	 public static void setUpClass() {   
		 ///TODO: Clear existing database 
		 UserDataBase.UserDataBaseWriter.writeToFile(new LinkedList<User>());
		 
//		 User userScore8 = new User("userScore8", "testing123!");
//		 User userScore9 = new User("userScore9", "testing123!");
//		 
//		 User userScore1 = new User("userScore1", "testing123!");
//		 User userScore2 = new User("userScore2", "testing123!");
//
//		 addUserHistory(userScore8, userScore9, 8, 9);
//		 addUserHistory(userScore1, userScore2, 1, 2);
		 
		 Demo.generateDemoUsers();
	 }

	    @AfterClass //Master TearDown
	    public static void tearDownClass() { 
//			 User userScore8 = UserDataBase.retrieveUser("userScore8");
//			 User userScore9 = UserDataBase.retrieveUser("userScore9");
//			 
//			 User userScore1 = UserDataBase.retrieveUser("userScore1");
//			 User userScore2 = UserDataBase.retrieveUser("userScore2");
			 
//	    	removeLastUserHistory(userScore8, userScore9, (8+9));
//	    	removeLastUserHistory(userScore1, userScore2, (1+2));
	    	
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

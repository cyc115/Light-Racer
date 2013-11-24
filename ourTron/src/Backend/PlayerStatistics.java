package Backend;

/**
 * reviewed : Mike
 * TODO finish this class, and implement everything. right now everything is commented out 
 */


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;


//public class PlayerStatistics {
//	private LinkedList<PlayerStatisticsEntry> stats;
//	private PlayerStatisticsWriter playerStatsWriter;
//	
//	PlayerStatistics() {
//		this.playerStatsWriter = new PlayerStatisticsWriter();
//		
//		//loads the playerStatistics if there is none, otherwise load them. 
//		if(playerStatsWriter.loadStatisticsFromFile() == null)
//			playerStatsWriter.saveStatisticsToFile(this);
//		else
//			this.stats = playerStatsWriter.loadStatisticsFromFile().getStats();
//	}
//	
//	
//	
//	//TODO figure out what goes here!
//	public void displayStatistics() {
//		
//	}
//	
//	private PlayerStatisticsEntry getStatisticsEntry() {
//		for(PlayerStatisticsEntry entry : stats) {
//			if(entry.user1.equals(user1) && entry.user2.equals(user2))
//				return entry;
//			if(entry.user1.equals(user2) && entry.user2.equals(user1))
//				return entry;
//		}
//		return null;
//	}
//	
////	public double computeWinLossRatio() {
////		PlayerStatisticsEntry entry = getStatFile();
////		
////		//TODO get stat file and return it.
////	}
//	
////	//helper method
////	private void setPlayerStatistics(PlayerStatistics entry) {
////		this.stats = entry.stats;
////	}
//	
//	public LinkedList<PlayerStatisticsEntry> getStats() {
//		return stats;
//	}
//
//	public void setStats(LinkedList<PlayerStatisticsEntry> stats) {
//		this.stats = stats;
//	}
//
//	public class PlayerStatisticsEntry {
//		private User user1;
//		private User user2;
//		private User winner;
//		
//		public PlayerStatisticsEntry(User user1, User user2) {
//			this.user1 = user1;
//			this.user2 = user2;
//		}
//		
//		public void addWin(User user) {
//			results.add(user);
//		}
//	
//		public User getUser1() {
//			return user1;
//		}
//
//		public void setUser1(User user1) {
//			this.user1 = user1;
//		}
//
//		public User getUser2() {
//			return user2;
//		}
//
//		public void setUser2(User user2) {
//			this.user2 = user2;
//		}
//
//		public LinkedList<User> getResults() {
//			return results;
//		}
//
//		public void setResults(LinkedList<User> stats) {
//			this.results = stats;
//		}
//		
//		public class GameResult{
//			private User user2;
//			private User winner;
//			
//			GameResult(User user2, User winner) {
//				
//			}
//		}
//	}
//	
//	public class PlayerStatisticsWriter {
//		private String location;
//		
//		public PlayerStatisticsWriter() {
//			this.location = "PlayerStatistics.data";
//		}
//		
//		public PlayerStatistics loadStatisticsFromFile() {
//			try {
//				FileInputStream fileIn = new FileInputStream(location);
//				ObjectInputStream in = new ObjectInputStream(fileIn);
//				PlayerStatistics stats =  (PlayerStatistics) in.readObject();
//				in.close();
//				fileIn.close();
//				return stats;
//			} catch (IOException i) {
//				i.printStackTrace();
//				return null;
//			} catch (ClassNotFoundException c) {
//				System.out.println("The location does not have a valid file");
//				c.printStackTrace();
//				return null;
//			}
//		}
//		
//		/**
//		 * This method writes to a file in a serialized fashion. The location of
//		 * output is defined in the instance of this object
//		 */
//		public void saveStatisticsToFile(PlayerStatistics stats) {
//			try {
//				FileOutputStream fileOut = new FileOutputStream(location);
//				ObjectOutputStream out = new ObjectOutputStream(fileOut);
//				out.writeObject(stats);
//				out.close();
//				fileOut.close();
//				System.out.printf("Your data has been saved in " + location);
//			} catch (IOException i) {
//				i.printStackTrace();
//			}
//		}
//	}
//
//	
//	
//}


public class PlayerStatistics {
	static UserDataBase userDB = new UserDataBase();
	
//	public static int getWins(User user, User opponent) {
//		return user.getWinsVsOpponent(opponent);
//	}
//	
//	public static int getTotalWins(User user) {
//		return user.getTotalWins();
//	}
	
/**
 * Calculates the wins between 2 users
 * @param user1 
 * @param user2
 * @return a String which contains the results of wins/losses of each user
 */
	public static String user1VsUser2Wins(User user1, User user2){
		int user1Wins = user1.getWinsVsOpponent(user2);
		int user2Wins = user2.getWinsVsOpponent(user1);
		
		String htmlDisplay;
		
		if (user1 == null || user2 == null){
			htmlDisplay = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Head-To-Head Scores</font></h1>"
					+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'><table align='center'>"
					+ "<tr><td>" + "User 1" + "</td><td>-------------------</td><td>" + "User 2" + "</td></tr>"
					+ "<tr><td><center>" + "0" + "</center></td><td> </td><td><center>" + "0" + "</center</td></tr>"
					+ "</table><br/><br/></span></p>";
		}
		
		htmlDisplay = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Head-To-Head Scores</font></h1>"
				+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'><table align='center'>"
				+ "<tr><td>" + user1.getUsername() + "</td><td>-------------------</td><td>" + user2.getUsername() + "</td></tr>"
				+ "<tr><td><center>" + String.valueOf(user1Wins) + "</center></td><td> </td><td><center>" + String.valueOf(user2Wins) + "</center</td></tr>"
				+ "</table><br/><br/></span></p>";
		
		return htmlDisplay;
	}
	/**
	 * Calculates the top 10 Users (determined from who has the most wins)
	 * @return a String in html format of top 10 users
	 */
	public static String top10Users(){
		String toDisplay;

		String[] top10 = new String[numberOfUsers-1];
		for(int i=0; i<(numberOfUsers-1); i++){
			top10[i] = null;
		}

		LinkedList<User> allUsers = UserDataBase.getAllUsers();
		
		//add at least 10 blank users to allUsers
		User blank = new User();
		blank.setUsername("---");
		for(int i=0; i<(numberOfUsers); i++){
			allUsers.add(blank);
		}
		
		//sort all the users into top scores according to user.getTotalWins
		Collections.sort(allUsers, User.Comparators.Wins);
		
		//Write username and number of wins in the String toDisplay
		for(User u: allUsers){
			for(int i=1; i<=10; i++){
				toDisplay = toDisplay + String.valueOf(i) + ". " + u.getUsername() + ": " + u.getTotalWins() + "<br/>";
			}
		}
		String htmlDisplay = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Top 10 Users</font></h1>"
				+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'>" + toDisplay + "</span></p>";
		
		return htmlDisplay;
	}
}


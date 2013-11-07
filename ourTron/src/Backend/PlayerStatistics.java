//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.util.LinkedList;
//
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

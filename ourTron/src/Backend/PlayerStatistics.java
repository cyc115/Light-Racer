import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

//
public class PlayerStatistics {
	//TODO last 10 wins and losses between two players.
	private User user1;
	private User user2;
	private LinkedList<PlayerStatisticsEntry> stats;
	
	PlayerStatistics(String filename) {
		loadStatisticsFromFile(filename);
	}
	
	//TODO figure out what goes here!
	public void displayStatistics() {
		
	}
	
	private PlayerStatisticsEntry getStatFile() {
		for(PlayerStatisticsEntry entry : stats) {
			if(entry.user1.equals(user1) && entry.user2.equals(user2) || entry.user1.equals(user2) && entry.user2.equals(user1))
				return entry;
		}
		return null;
	}
	
//	public double computeWinLossRatio() {
//		PlayerStatisticsEntry entry = getStatFile();
//		
//		//TODO get stat file and return it.
//	}
	
//	//helper method
//	private void setPlayerStatistics(PlayerStatistics entry) {
//		this.stats = entry.stats;
//	}
	
	@SuppressWarnings("unchecked")
	public void loadStatisticsFromFile(String filename) {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			stats = (LinkedList<PlayerStatisticsEntry>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("The location does not have a valid file");
			c.printStackTrace();
			return;
		}
	}
	
	/**
	 * This method writes to a file in a serialized fashion. The location of
	 * output is defined in the instance of this object
	 */
	public void saveStatisticsToFile(String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(stats);
			out.close();
			fileOut.close();
			System.out.printf("Your data has been saved in " + filename);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	//TODO figure out the exact implementation of this class. 
	public class PlayerStatisticsEntry {
		private User user1;
		private User user2;
		private User[] results;
		
		public PlayerStatisticsEntry(User user1, User user2) {
			this.user1 = user1;
			this.user2 = user2;
			results = new User[10];
		}
		
		public void addWin(User user) {
			shiftGameResults();
			results[0] = user;
		}
		
		public User[] getStatistics() {
			return results;
		}
		
		public User getUser1() {
			return user1;
		}

		public void setUser1(User user1) {
			this.user1 = user1;
		}

		public User getUser2() {
			return user2;
		}

		public void setUser2(User user2) {
			this.user2 = user2;
		}

		public User[] getResults() {
			return results;
		}

		public void setResults(User[] results) {
			this.results = results;
		}

		private void shiftGameResults() {
			results[10] = results[9];
			results[9] = results[8];
			results[8] = results[7];
			results[7] = results[6];
			results[6] = results[5];
			results[5] = results[4];
			results[4] = results[3];
			results[3] = results[2];
			results[2] = results[1];
			results[1] = results[0];
		}
	}

	
	
}

package Backend;

/**
 *Authored: Joanna
 */


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

	public class PlayerStatistics {
	
		/**
	 * Calculates the wins between 2 users. 
	 * @param userName1 
	 * @param userName2 
	 * @return a String which contains the results of wins/losses of each user in html format. If either username does not exist, then it will return scores of 0 for both User 1 and User 2
	 */
		public static String user1VsUser2Wins(String userName1, String userName2){
			String htmlDisplay;
			User user1 = UserDataBase.retrieveUser(userName1);
			User user2 = UserDataBase.retrieveUser(userName2);
			
			if (user1 != null && user2 != null){
				int user1Wins = user1.getWinsVsOpponent(user2);
				int user2Wins = user2.getWinsVsOpponent(user1);
	
				htmlDisplay = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Head-To-Head Scores</font></h1>"
						+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'><table align='center'>"
						+ "<tr><td>" + user1.getUsername() + "</td><td>-------------------</td><td>" + user2.getUsername() + "</td></tr>"
						+ "<tr><td><center>" + String.valueOf(user1Wins) + "</center></td><td> </td><td><center>" + String.valueOf(user2Wins) + "</center</td></tr>"
						+ "</table><br/><br/></span></p>";
			}
			else{
			htmlDisplay = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Head-To-Head Scores</font></h1>"
					+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'><table align='center'>"
					+ "<tr><td>" + "User 1" + "</td><td>-------------------</td><td>" + "User 2" + "</td></tr>"
					+ "<tr><td><center>" + "0" + "</center></td><td> </td><td><center>" + "0" + "</center</td></tr>"
					+ "</table><br/><br/></span></p>";
			}
			return htmlDisplay;
		}
		/**
		 * Calculates the top 10 Users (determined from who has the most wins)
		 * @return a String in html format of top 10 users
		 */
		public static String top10Users(){
			String toDisplay = "";
	
			LinkedList<User> allUsers = new LinkedList<User>();
			allUsers = UserDataBase.getAllUsers();
			
			//add at least 10 blank users to allUsers
			User blank = new User();
			blank.setUsername("---");
			int numberOfUsers = 10;
			for(int i=0; i<(numberOfUsers); i++){
				allUsers.add(blank);
			}
			
			//sort all the users into top scores according to user.getTotalWins
			Collections.sort(allUsers, User.Comparators.Wins);
			
			//Write username and number of wins in the String toDisplay
			int i = 1;
			for(User u: allUsers){
				if(i<=10){
					toDisplay += String.valueOf(i) + ". " + u.getUsername() + ": " + u.getTotalWins() + "<br/>";
					i++;
				}
			}
			String htmlDisplay = "<h1 style='text-align: center;'><font color='#b22222' face='trebuchet ms, helvetica, sans-serif'>Top 10 Users</font></h1>"
					+ "<p style='text-align: center;'><span style='font-family:trebuchet ms,helvetica,sans-serif,;'>" + toDisplay + "</span></p>";
			
			return htmlDisplay;
		}

}
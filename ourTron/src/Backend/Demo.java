package Backend;

public class Demo {
	public static void main(String[] args) {
		//create user Demo10
		User demoUser = new User("Demo10", "Dem@Us3R10");
		
		//create demo users 1-9
		for(int i=1; i<=9; i++){
			demoUser = new User("Demo0"+ i, "Dem@Us3R0"+ i);
			System.out.println(demoUser.getUsername());
		}
		addDemoUserHistory("Joanna", "Halpern", 2, 3);
		
		//add the demo scores specified by the prof
//		addDemoUserHistory("Demo01", "Demo02", 2, 2);
//		addDemoUserHistory("Demo01", "Demo03", 2, 3);
//		addDemoUserHistory("Demo01", "Demo04", 2, 4);
//		addDemoUserHistory("Demo01", "Demo05", 2, 5);
//		addDemoUserHistory("Demo06", "Demo07", 2, 2);
//		addDemoUserHistory("Demo06", "Demo08", 2, 3);
//		addDemoUserHistory("Demo06", "Demo09", 2, 4);
//		addDemoUserHistory("Demo06", "Demo10", 2, 5);
	}
	
	
	/**
	 * 
	 * @param user1Name
	 * @param user2Name
	 * @param user1Wins
	 * @param user2Wins
	 */
	public static void addDemoUserHistory(String user1Name, String user2Name, int user1Wins, int user2Wins){
		if(user1Name !=null || user2Name != null){
			User user1 = new User();
			user1 = UserDataBase.retrieveUser(user1Name);
			User user2 = new User();
			user2 =	UserDataBase.retrieveUser(user2Name);
//			for(int i=0; i<user1Wins; i++){
				user1.addGameResult(user2, true);
//				user2.addGameResult(user1, false);
//			}
//			for(int i=0; i<user2Wins; i++){
//				user1.addGameResult(user2, false);
//				user2.addGameResult(user1, true);
//			}
		}
	}
}



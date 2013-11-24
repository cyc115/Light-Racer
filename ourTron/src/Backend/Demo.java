package Backend;

public class Demo {
	public static void main(String[] args) {
		User demoUser = new User("Demo10", "Dem@Us3R10");
		for(int i=1; i<=9; i++){
			demoUser = new User("Demo0"+ i, "Dem@Us3R0"+ i);
		}
		
		demoUser.addGameResultFromNames("Demo01", "Demo02", true);
		demoUser.addGameResultFromNames("Demo01", "Demo02", true);
		demoUser.addGameResultFromNames("Demo01", "Demo02", false);
		demoUser.addGameResultFromNames("Demo01", "Demo02", false);

		demoUser.addGameResultFromNames("Demo01", "Demo03", true);
		demoUser.addGameResultFromNames("Demo01", "Demo03", true);
		demoUser.addGameResultFromNames("Demo01", "Demo03", false);
		demoUser.addGameResultFromNames("Demo01", "Demo03", false);
		demoUser.addGameResultFromNames("Demo01", "Demo03", false);

		demoUser.addGameResultFromNames("Demo01", "Demo04", true);
		demoUser.addGameResultFromNames("Demo01", "Demo04", true);
		demoUser.addGameResultFromNames("Demo01", "Demo04", false);
		demoUser.addGameResultFromNames("Demo01", "Demo04", false);
		demoUser.addGameResultFromNames("Demo01", "Demo04", false);
		demoUser.addGameResultFromNames("Demo01", "Demo04", false);
		
		demoUser.addGameResultFromNames("Demo01", "Demo05", true);
		demoUser.addGameResultFromNames("Demo01", "Demo05", true);
		demoUser.addGameResultFromNames("Demo01", "Demo05", false);
		demoUser.addGameResultFromNames("Demo01", "Demo05", false);
		demoUser.addGameResultFromNames("Demo01", "Demo05", false);
		demoUser.addGameResultFromNames("Demo01", "Demo05", false);
		demoUser.addGameResultFromNames("Demo01", "Demo05", false);

		demoUser.addGameResultFromNames("Demo06", "Demo07", false);

	}
	
	public static void addDemoUsers(String user1, String user2, int user1Wins, int user2Wins){
		User demoUser = new User();
		for(int i=0; i<user1Wins; i++){
			demoUser.addGameResultFromNames(user1, user2, true);
		}
		for(int i=0; i<user2Wins; i++){
			demoUser.addGameResultFromNames(user2, user1, true);
		}
	}
}



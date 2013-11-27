package Backend;

/**
 *Stores the information: Instructions and AboutUs
 * @author Joanna
 *
 */
public class Info {
	
/**
 * The game instructions in HTML format
 * @return a String which contains the instruction in HTML format
 */
	public static String getInstruction(){
		String htmlInstructions = "<body>"
				+ "<h1 style='text-align: center;'>Instructions</h1>"
				+ "<h3><span style='color:#b22222;'>Game Play</span></h3>"
				+ "<p>Tron is a fun and fast-paced 2 player game. The rules are simple. The object of the game, is to not crash your light racer. You must stay &quot;alive&quot; (i.e. not crash) longer than your opponent. Crashing means hitting a wall (which is the edge of the game area), obstacle, or the trail of either your opponent or yourself.</p>"
				+ "<h3><span style='color:#b22222;'>Controls</span></h3>"
				+ "<p><strong>Player 1</strong> can use the <strong>w, s, a, d</strong>, keys to move up, down, left, and right respectively.</p>"
				+ "<p><strong>Player 2</strong> can use the <strong>arrow keys</strong> to move up, down, left and right.</p></body>";
		return htmlInstructions;
	}

	/**
	 * The About Us page in HTML format
	 * @return a String which contains the About Us page in HTML format
	 */
	public static String getAboutUs(){
		String htmlAboutUs = "<body>"
				+ "<h1 style='text-align: center;'><span style='font-family:trebuchet ms,helvetica,sans-serif;'><span style='color:#b22222;'>About Us</span></span></h1>"
				+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'>Hi!</span></p>"
				+ "<p><span style='font-family:trebuchet ms,helvetica,sans-serif;'>We&rsquo;re a group of 4 software engineering students from McGill University. Our hobbies include: coding, coding, more coding, yelling at eclipse git, watching youtube videos to cheer us up, more yelling at merging git, celebrating when something actually works, and finding some time to sleep, although we save that for winter/summer break.</span></p>"
				+ "</body>";
		
		return htmlAboutUs;
	}
}


package Launcher;

import UI.MainMenu;

/**
 * Launches the main application. This is the way the user will interface with the program. 
 */
public class Launcher {
	public static void main(String[] args) {
		MainMenu.getInstance().setVisible(true);
	}
}

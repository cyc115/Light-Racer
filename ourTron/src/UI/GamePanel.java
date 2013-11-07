package UI;

import javax.swing.JPanel;

/**
 * implement the game code here.
 * @author <put your name here,who ever's responsible of this class  > 
 *
 */
public class GamePanel extends JPanel {
	
	/**
	 * this is the game panel object . only 1 copy exist per game ~!
	 */
	private static GamePanel gamePanelInstance = new GamePanel();
	/**
	 * Create the panel.
	 */
	private GamePanel() {
		this.setSize(500, 500);
	}
	
	//TODO stub
	public static GamePanel getInstance(){
		return gamePanelInstance;
	}
	
	//TODO stub
	public void reset(){
		
	}
	//TODO stub
	public void static init(){
		
	}
	
}

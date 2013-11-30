/**
 * 
 */
package GameCore;

import static org.junit.Assert.*;
import UI.GamePanel;
import UI.GameFrame;
import GameCore.Map;
import Backend.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The main purpose of this test is to test the collison system of the game.<br>
 * 2 Users are created and placed at their respective starting location<br>
 * To simulate a collision, I override a mapTile. For example, to test Player 2 colliding with a wall, I set the MapTile in front of Player 2 starting position with a Wall<br>
 * To test Player 2 colliding with Player 1's head, I set MapTile in front of Player 2' starting position with Player 1's head.<br>
 * To test Player 2 colliding with Player 1's trail, I set MapTile in front of Player 2' starting position with Player 1's trail.<br>
 * Then I do the same for Player 1.<br>
 * To test 2 Players colliding into each other, I set the MapTile in front of both Players' starting position with the opponent.<br>
 * To Test when both Players collide into a wall at the same time,I set the MapTile in front of both Players' startign position with a wall.<br><br>
 * 
 * We've also done extensive testing by playing the game, and replicating each possible collision scenario.<br>
 *
 */
public class GameLogicTest {

	GamePanel gamePanel;
	GameFrame gameFrame;
	GameLogic gameLogic;
	Map newMap;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gamePanel = GamePanel.getInstance();
		gameFrame = GameFrame.getInstance();
	    gameLogic = GameLogic.getInstance();
		
		//creates 2 new users
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		user1.setUsername("Player 1");
		user2.setUsername("Player 2");
		user3.setUsername("none");
		GameLogic.setUser(1, user1);
		GameLogic.setUser(2,user2);	
		GameLogic.setUser(3,user3);
		newMap = new Map();
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception{
		gameLogic.resetRoundNumber();
	}
	
	/**
	 * Test method for Player 2 colliding into a wall.
	 */
	@Test
	public void P1WinP2Wall() {
		//sets Wall in front of player 2 in map 1 (simulate player 2 colliding with a wall)
		newMap.setOccupation(new Coordinate(74,1), "WALL" );
		gameLogic.overrideMap(newMap);
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 1";		
		assertEquals("Player 1 wins", expected, actual);
	
	}
	/**
	 * Test method for Player 2 colliding into player1's head.
	 */
	@Test
	public void P1WinP2P1Head() {
		//sets player1 in front of player 2 in map 1 (simulate player 2 colliding with player1)
		newMap.setOccupation(new Coordinate(74,1), "player1Head" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 1";		
		assertEquals("Player 1 wins", expected, actual);
	
	}
	/**
	 * Test method for Player 2 colliding into player1's trail.
	 */
	@Test
	public void P1WinP2P1Trail() {
		//sets player1 in front of player 2 in map 1 (simulate player 2 colliding with player1)
		newMap.setOccupation(new Coordinate(74,1), "player1Trail" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 1";		
		assertEquals("Player 1 wins", expected, actual);
	
	}
	/**
	 * Test method for Player 2 colliding into itself.
	 */
	@Test
	public void P1WinP2P2Trail() {
		//sets player2 in front of player 2 in map 1 (simulate player 2 colliding with itself)
		newMap.setOccupation(new Coordinate(74,1), "player2Trail" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 1";		
		assertEquals("Player 1 wins", expected, actual);
	
	}
	
	/**
	 * Test method for Player 1 colliding into a wall.
	 */
	@Test
	public void P2WinP1Wall() {
		//sets Wall in front of player 1 in map 1 (simulate player 1 colliding with a wall)
		newMap.setOccupation(new Coordinate(0,48), "WALL" );
				
				gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 2";		
		assertEquals("Player 2 wins", expected, actual);
	
	}
	/**
	 * Test method for Player 1 colliding into player2's head
	 */
	@Test
	public void P2WinP1P2Head() {
		//sets player2 in front of player 1 in map 1 (simulate player 1 colliding with player1)
		newMap.setOccupation(new Coordinate(0,48), "player2Head" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 2";		
		assertEquals("Player 2 wins", expected, actual);
	
	}
	/**
	 * Test method for Player 1 colliding into player2's trail.
	 */
	@Test
	public void P2WinP1P2Trail() {
		//sets player2 in front of player 1 in map 1 (simulate player 1 colliding with player1)
		newMap.setOccupation(new Coordinate(0,48), "player2Trail" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 2";		
		assertEquals("Player 2 wins", expected, actual);
	
	}
	/**
	 * Test method for Player 1 colliding into itself.
	 */
	@Test
	public void P2WinP1P1Trail() {
		//sets  player1 in front of player 1 in map 1 (simulate player 1 colliding with itself)
		newMap.setOccupation(new Coordinate(0,48), "player1Trail" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "Player 2";		
		assertEquals("Player 2 wins", expected, actual);
	
	}
	/**
	 * Test method for both Players collide into a wall at the same time.
	 */
	@Test
	public void drawWall() {
		//sets a wall in front of player 1 in map 1 (simulate player 1 colliding with a wall)
		newMap.setOccupation(new Coordinate(0,48), "WALL" );
		//sets a Wall in front of player 2 in map 1 (simulate player 2 colliding with a wall)
		newMap.setOccupation(new Coordinate(74,1), "WALL" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "none";		
		assertEquals("Draw", expected, actual);
	
	}
	/**
	 * Test method for head-on collision.
	 */
	@Test
	public void drawHead() {
		//sets player2 in front of player 1 in map 1 (simulate player 1 colliding with player 2)
		newMap.setOccupation(new Coordinate(0,48), "player2Head" );
		//sets a Wall in front of player 2 in map 1 (simulate player 2 colliding with a player 1)
		newMap.setOccupation(new Coordinate(74,1), "player1Head" );
		gameLogic.overrideMap(newMap);
		
		gameFrame.init();
		gamePanel.start();
		// wait 2.5 seconds for game to start
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actual = GamePanel.winner.getUsername();
		String expected = "none";		
		assertEquals("Draw", expected, actual);
	
	}
}

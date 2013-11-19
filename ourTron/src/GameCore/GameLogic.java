package GameCore;

import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

import UI.GamePanel;
import Backend.User;

public class GameLogic {

	public int size = 512;
	public int width = size;
	public int height = size;
	public int scale = 1;
	private final int numberOfTiles = 128;
	private int[] pixels;
	//this determines the size of each square in pixel, 2 = 4x4 , 3 = 8x8 , 4= 16x16
	private final int bitshift = 2;
	
	private Player player1;
	private Player player2;
	public  User user1 = new User();
	public  User user2 = new User();
	private GameScore gamescore;
	private int roundNumber;
	final Coordinate startingPosP1 = new Coordinate(60,1);
	final Coordinate startingPosP2 = new Coordinate(65,126);
	
	private LinkedList <Control> p1Direction;
	private LinkedList <Control> p2Direction;
	private final int  MAX_KEYINPUT = 5;
	
	public int[] tiles = new int [numberOfTiles * numberOfTiles];
	public Map gameMap;
	
	private boolean isPaused = false;
	private boolean endGame = false;
	private boolean endRound = false;
	
	public GameLogic(){
		
		pixels = new int[size*size];
		roundNumber = 0;
	}
	
	
	public void initializePlayers(){
		
			player1 = new Player(startingPosP1, user1, Control.SOUTH);
			player2 = new Player(startingPosP2, user2, Control.NORTH);

			p1Direction = new LinkedList<Control> ();
			p1Direction.add(player1.getDirection());

			p2Direction = new LinkedList<Control> ();
			p2Direction.add(player2.getDirection());
			gamescore = new GameScore();
			gameMap = new Map();
		
	}
	
	public void reinitializeGame(){
		
		player1.setCollision(false);
		player2.setCollision(false);
		final Coordinate startingPos1 = new Coordinate(60,1);
		final Coordinate startingPos2 = new Coordinate(65,126);
		player1.setPlayerLocation(startingPos1);
		player2.setPlayerLocation(startingPos2);
		
		p1Direction.clear();
		p2Direction.clear();
		p1Direction.add(Control.SOUTH);
		p2Direction.add(Control.NORTH);
		gameMap = new Map();
	}
	
	public void update() {
		 movePlayers(p1Direction, player1, gameMap,  "player1Trail" , "player1Head");
		 movePlayers(p2Direction, player2, gameMap,  "player2Trail" , "player2Head");
		 handleCollisions(player1, player2);			 
		 if(p1Direction.size() > 1)
			 p1Direction.poll();
		 if(p2Direction.size()> 1)
			 p2Direction.poll();
	}
	
	public void renderScreen( int[] pixels){

		//Render the map onto the screen
		for (int i = 0 ; i < gameMap.getMapSize(); i++){

			switch(gameMap.getOccupation1D(i)){
			case EMPTY:
				//transparent
				tiles[i]= 0x000000;
				break;
			case WALL:
				//grey
				tiles[i]= 0xFF9100 | 0xB0000000 ;
				break;
			case player1Trail:
				//blue 0x00F0FC
				tiles[i]= 0x00F0FC | 0xD0000000;
				break;
			case player2Trail:
				//red
				tiles[i]= 0xFC0000 | 0xD0000000;
				break;
			case power1:
				//green
				tiles[i]= 0x3BBF3D | 0xFF000000;
				break;
			case power2:
				//orange
				tiles[i]= 0xFF9100 | 0xFF000000;
				break;
			case player1Head:
				//light blue
				tiles[i] = 0x7DB3E3 | 0xFF000000;
				break;
			case player2Head:
				//light red 
				tiles[i] = 0xDE8181 | 0xFF000000;
				break;
			default:
				break;
			}
		}
		//This converts the tiles to pixels to be displayed on the screen
		for (int y = 0; y < height ; y++){
			int yy = y;
			if(yy < 0 || y >= height) break;
			for (int x = 0 ; x < width ; x++){
				int xx = x;
				if(xx < 0 || x >= width) break;
				//updates pixels line by line from left to right and up to bottom
				//each tiles has 16x16 pixels
				int tileIndex = (x >> bitshift) + (y >> bitshift) * numberOfTiles;
				pixels[x + y * width] = tiles[tileIndex];

			}

		}

	}
	
	
		
	public void movePlayers( LinkedList<Control> playerDirection, Player player,  Map mapArray , String trail , String head){ 
		Control playerDir = playerDirection.peekFirst();
		Coordinate playerCoords = player.getPlayerLocation();
		mapArray.setOccupation(playerCoords, trail);
		
		switch(playerDir){
		
		case NORTH:
			playerCoords.setY(playerCoords.getY() -1 );
			if(hasCollided(player, mapArray, playerCoords))
				break;
			mapArray.setOccupation(playerCoords, head);
			break;
			
		case SOUTH:
			playerCoords.setY(playerCoords.getY() + 1 );
			if(hasCollided(player, mapArray, playerCoords))
				break;
			mapArray.setOccupation(playerCoords, head);
			break;
		case WEST:
			playerCoords.setX(playerCoords.getX() -1 );
			if(hasCollided(player, mapArray, playerCoords))
				break;
			mapArray.setOccupation(playerCoords, head);
			break;
		case EAST:
			playerCoords.setX(playerCoords.getX() + 1 );
			if(hasCollided(player, mapArray, playerCoords))
				break;
			mapArray.setOccupation(playerCoords, head);
			break;
		 default:
			break;
		}
	}
	public int getGameRoundNumber(){ 
		return roundNumber;
	}
	public void incrRoundNumber(){
		roundNumber++;
	}
	
	

	
	/**
	 * Checks is player has collided with anything on the gameMap
	 */
    public static boolean hasCollided(Player player, Map gameMap, Coordinate nextLocation){ //TODO: fill in hasCollided
    	
    		if(gameMap.isOccupied(nextLocation)){
    			player.setCollision(true);
    			return true;
    		}
    		else{
    			return false;
    		}
    	
    	
    }
	/**
	 * handleCollision will first check if there are any collision, and then handle them.
	 * If there are no collisions, then it will return
	 * If there are collisions, then it will update the GameScore and end the game
	 */
	public boolean handleCollisions(Player player1, Player player2){ //TODO: fill in handleCollissions collision 
		boolean p1HasCollided = player1.getCollision();
		boolean p2HasCollided = player2.getCollision();

		if (!p2HasCollided && !p1HasCollided){
			return false;
		}
		else if(p2HasCollided && p1HasCollided){
			System.out.println("DRAW");
			
		}
		else if( (!p1HasCollided) && (p2HasCollided)){ //p1 wins
			System.out.println("P1 WINS");
			gamescore.incrP1Win();
			incrRoundNumber();
			GamePanel.winner = player1;
			
			
		}
		else if ( (p1HasCollided) && (!p2HasCollided) ){ //p2 wins
			System.out.println("P2 WINS");
			gamescore.incrP2Win();
			incrRoundNumber();
			GamePanel.winner = player2;
		}
		if(roundNumber <3){
			GamePanel.resetGame=true;
			return true;
		}
		else{
			GamePanel.endGame = true;
			return true;
		}
	}
	
	
	public void addP1Direction(Control direction){
		switch(direction){
		
		case NORTH:
			if(!isPaused && !endGame && !endRound) {
				//makes sure we don't poll more than 3 times
				if(p1Direction.size() < MAX_KEYINPUT) {
					//checks that the most recent direction is either EAST or WEST
					Control last = p1Direction.peekLast();
					if(last != Control.SOUTH && last != Control.NORTH) {
						p1Direction.addLast(Control.NORTH);
					}
				}
			}
			break;
		
		 case SOUTH:
             if(!isPaused && !endGame) {
                     //makes sure we don't poll more than 3 times
                     if(p1Direction.size() < MAX_KEYINPUT) {
                             Control last = p1Direction.peekLast();
                             //checks that the most recent direction is either EAST or WEST
                             if(last != Control.NORTH && last != Control.SOUTH) {
                                     p1Direction.addLast(Control.SOUTH);
                             }
                     }
             }
             break;	
			
		 case WEST:
             if(!isPaused && !endGame) {
                     //makes sure we don't poll more than 3 times
                     if(p1Direction.size() < MAX_KEYINPUT) {
                             Control last = p1Direction.peekLast();
                             //checks that the most recent direction is either North or South
                             if(last != Control.WEST && last != Control.EAST) {
                                     p1Direction.addLast(Control.WEST);
                             }
                     }
             }
             break;	
		//for player 1
    	case EAST:
            if(!isPaused && !endGame) {
                    //makes sure we don't poll more than 3 times
                    if(p1Direction.size() < MAX_KEYINPUT) {
                            Control last = p1Direction.peekLast();
                            //checks that the most recent direction is either North or South
                            if(last != Control.WEST && last != Control.EAST) {
                                    p1Direction.addLast(Control.EAST);
                            }
                    }
            }
            break;

		} 
		
	}
	
	public void addP2Direction(Control direction){
		switch(direction){
		
		case NORTH:
			if(!isPaused && !endGame && !endRound) {
				//makes sure we don't poll more than 3 times
				if(p2Direction.size() < MAX_KEYINPUT) {
					//checks that the most recent direction is either EAST or WEST
					Control last = p2Direction.peekLast();
					if(last != Control.SOUTH && last != Control.NORTH) {
						p2Direction.addLast(Control.NORTH);
					}
				}
			}
			break;
		
		 case SOUTH:
             if(!isPaused && !endGame) {
                     //makes sure we don't poll more than 3 times
                     if(p2Direction.size() < MAX_KEYINPUT) {
                             Control last = p2Direction.peekLast();
                             //checks that the most recent direction is either EAST or WEST
                             if(last != Control.NORTH && last != Control.SOUTH) {
                                     p2Direction.addLast(Control.SOUTH);
                             }
                     }
             }
             break;	
			
		 case WEST:
             if(!isPaused && !endGame) {
                     //makes sure we don't poll more than 3 times
                     if(p2Direction.size() < MAX_KEYINPUT) {
                             Control last = p2Direction.peekLast();
                             //checks that the most recent direction is either North or South
                             if(last != Control.WEST && last != Control.EAST) {
                                     p2Direction.addLast(Control.WEST);
                             }
                     }
             }
             break;	
		//for player 1
    	case EAST:
            if(!isPaused && !endGame) {
                    //makes sure we don't poll more than 3 times
                    if(p2Direction.size() < MAX_KEYINPUT) {
                            Control last = p2Direction.peekLast();
                            //checks that the most recent direction is either North or South
                            if(last != Control.WEST && last != Control.EAST) {
                                    p2Direction.addLast(Control.EAST);
                            }
                    }
            }
            break;

		} 
		
	}
		
	public boolean endGame(){
		return this.endGame;
	}

	public void usePowerUp(Player player){ //TODO: fill in usePowerUp

	}
	public void obtainPowerUp(){ //TODO: fill in
	}
	
	public int[] getPixels(){
		return pixels;
	}
	
	public int getRoundNumber(){
		return roundNumber;
	}
}

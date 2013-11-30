package GameCore;

import Backend.User;

/**
 * This class is the Player object.
 * Each Player contains the current Coordinate, Control(which is the current direction), username, speed, powerup, and lastInput of the Player.
 * It also contains a boolean called hasCollided which tells if the player has made a collision.
 * This is used to keep track of the current stated of the each player during game play.
 */
public class Player {
	private Coordinate coord;
	private Control direction;
	private String username;
	private int speed;
	private char lastInput;
	private boolean hasCollided;

	public Player(Coordinate startingCoordinate, User user,
			Control startingDirection) {

		int startingSpeed = 1;
		this.coord = startingCoordinate;
		this.direction = startingDirection;
		this.username = convertFromUser(user);
		this.speed = startingSpeed;
		this.hasCollided = false;
	}
	
	/**
	 * Returns the username of a specified user
	 * @param user
	 * @returns username
	 */
	public String convertFromUser(User user) {
		return user.getUsername();
	}

	public Coordinate getPlayerLocation() {
		return coord;
	}

	public void setPlayerLocation(Coordinate coord) {
		this.coord = coord;
	}

	public Control getDirection() {
		return direction;
	}

	public void setDirection(Control direction) {
		this.direction = direction;
	}

	public int getPlayerSpeed() {
		return speed;
	}

	public void setPlayerSpeed(int speed) {
		this.speed = speed;
	}

	public char getLastInput() {
		return lastInput;
	}

	public void setLastInput(char lastInput) {
		this.lastInput = lastInput;
	}

	public boolean getCollision() {
		return hasCollided;
	}

	public void setCollision(boolean collision) {
		hasCollided = collision;
	}

	public String getUsername() {
		return this.username;
	}
}

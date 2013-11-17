package GameCore;

import Backend.User;

public class Player {
	private Coordinate coord;
	private Control direction;
	private String username;
	private int speed;
	private int powerUp;
	private char lastInput;
	private boolean hasCollided;

	public Player(Coordinate startingCoordinate, User user) {
		Control startingDirection = Control.RIGHT;
		int startingSpeed = 1; // TODO what is this used for? -Danielle Mustillo
		this.coord = startingCoordinate;
		this.direction = startingDirection;
		this.username = convertFromUser(user);
		this.speed = startingSpeed;
		this.powerUp = 0;
		this.hasCollided = false;
	}

	public String convertFromUser(User user) { // TODO: convert from User
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

	public int getPowerUp() {
		return powerUp;
	}

	public void setPowerUp(int powerUp) {
		this.powerUp = powerUp;
	}
	public char getLastInput() {
		return lastInput;
	}

	public void setLastInput(char lastInput) {
		this.lastInput = lastInput;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getCollision() {
		return hasCollided;
	}

	public void setCollision(boolean collision) {
		hasCollided = collision;
	}
}

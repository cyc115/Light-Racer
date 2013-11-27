package GameCore; 
/**
 * A coordinate representing a location in (x,y) coordinate spacee, specified in integer precision.
 * @author Joanna
 *
 */
public class Coordinate{
	private int x;
	private int y;
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x =x;
	}
	public void setY(int y){
		this.y =y;
	}
	
	/**
	 * Compares this coordinate to the specified coordinate 
	 * @param c2 to compare this coordinate against
	 * @returns true if they are equal, meaning they have the same x and same y values, false otherwise
	 */
	public boolean equals(Coordinate c2){
		if ((this.x == c2.x) && (this.y == c2.y)){
			return true;
		}
		else{
			return false;
		}
	}
}
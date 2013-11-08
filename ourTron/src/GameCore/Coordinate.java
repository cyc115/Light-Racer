package GameCore; 

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
	
	public boolean equals(Coordinate c2){
		if ((this.x == c2.x) && (this.y == c2.y)){
			return true;
		}
		else{
			return false;
		}
	}

	public static void main(String[] args) {
	        System.out.println("It works!");
	}
}
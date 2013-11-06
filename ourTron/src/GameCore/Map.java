import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;
	private int difficulty;
	private int height = 200;
	private int width = 200;
	private MapSign[][] mapArray = new MapSign[height][width];
	private String mapName;
	
	public Map(String location) {
		super();
		createMapFromFile(location);
	}
	
	public Map() { //initializes blank Map with just the edges filled in as WALL  
		this.difficulty = 1;
		this.mapName = "blankMap";
		initializeJustEdges();
	}
	public void initializeJustEdges(){
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				this.mapArray[i][j] = MapSign.EMPTY;
			}
		}
		for(int i=0; i<width; i++){
			this.mapArray[i][0] = MapSign.WALL;
			this.mapArray[i][height-1] = MapSign.WALL;
		}
		for(int j=0; j<height; j++){
			this.mapArray[0][j] = MapSign.WALL;
			this.mapArray[width-1][j] = MapSign.WALL;
		}
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) { //added a setter
		this.difficulty = difficulty;
	}
	public boolean isOccupied(Coordinate coordinate){
		int x = coordinate.getX();
		int y = coordinate.getY();
		if (mapArray[x][y] != MapSign.EMPTY){
			return true;
		}
		else{
			return false;
		}
	}
	public MapSign getOccupation(Coordinate coordinate){
		int x = coordinate.getX();
		int y = coordinate.getY();
		return mapArray[x][y];
	}
	public void setOccupation(Coordinate coordinate, MapSign attribute){
		int x = coordinate.getX();
		int y = coordinate.getY();
		switch (attribute){
			case WALL: mapArray[x][y] = MapSign.WALL;
			case player1Trail: mapArray[x][y] = MapSign.player1Trail;
			case player2Trail: mapArray[x][y] = MapSign.player2Trail;
			case EMPTY: mapArray[x][y] = MapSign.EMPTY;
				break;
			default: {
				throw new IllegalArgumentException("Can't handle " + attribute);
			}
		}
	}
	
	//TODO there is repetition of code here. Ask TA what to do
	@SuppressWarnings("unchecked")
	public void createMapFromFile(String filename) {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			setMap( (Map) in.readObject() );
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("The location does not have a valid file");
			c.printStackTrace();
			return;
		}
	}
	
	

	/**
	 * This method writes to a file in a serialized fashion. The location of
	 * output is defined in the instance of this object
	 */
	public void saveMapToFile(String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.printf("Your data has been saved in " + filename);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	public String getMapName(){ 
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	private void setMap(Map map) {
		this.difficulty = map.difficulty;
		this.mapArray = map.mapArray;
		this.mapName = map.mapName;
	}
}
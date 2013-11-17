package GameCore; 


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class Map implements Serializable {
	private static final long serialVersionUID = 1L;
	private int difficulty;
	private int size = 128;
	private int height = 128;
	private int width = 128 ;
	private MapSign[][] mapArray = new MapSign[width][height];
	//exact copy of mapArray but in 1D
	private MapSign[] convertedMapArray = new MapSign[mapArray.length * mapArray.length];
	private String mapName;
	public enum MapSign {
		WALL, player1Trail, player2Trail, power1, power2, EMPTY, player1Head,player2Head
	}
	
	public Map(String location) {
		super();
		loadMapFromFile(location);
	}
	
	public Map() { //initializes blank Map with just the edges filled in as WALL  
		this.difficulty = 1;
		this.mapName = "blankMap";
		initializeJustEdges();
		generateRandomWalls();
		convert2Dto1D();
		createMap(this, "defaultMap.map");
		
		
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
	/**
	 * This with generate random blocks of walls into the map.
	 * It makes between 3-11 blocks
	 */
	public void generateRandomWalls(){
		Random randomGenerator = new Random();
		int numberOfBlocks = randomGenerator.nextInt(3)+3;
		
		for(int i=0; i<numberOfBlocks; i++){
			int blockWidth = randomGenerator.nextInt(30)+1;
			int blockHeight = randomGenerator.nextInt(30)+1;
			int randomX = randomGenerator.nextInt(128-blockWidth)+1;
			int randomY = randomGenerator.nextInt(128-blockHeight)+1;
			for(int j=randomX; j<randomX+blockWidth; j++){
				for(int k=randomY; k<randomY+blockHeight; k++){
					this.mapArray[j][k] = MapSign.WALL;
//					this.mapArray[k][j] = MapSign.WALL;
				}
			}
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
		if(x >= 0 && x < size && y >= 0 && y < size){
			if (mapArray[x][y] != MapSign.EMPTY){
				return true;
			}
			else{
				return false;
			}
		}
		else
			return true;
	}
	public boolean isOccupied(int x, int y){
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
	
	
	public MapSign getOccupation1D(int i){
		return convertedMapArray[i];
	}
	public void setOccupation(Coordinate coordinate, String attribute){
		int x = coordinate.getX();
		int y = coordinate.getY();
		MapSign enumAttribute = MapSign.valueOf(attribute);
		switch (enumAttribute){
			case WALL: mapArray[x][y] = MapSign.WALL; break;
			case player1Trail: mapArray[x][y] = MapSign.player1Trail; break;
			case player2Trail: mapArray[x][y] = MapSign.player2Trail; break;
			case EMPTY: mapArray[x][y] = MapSign.EMPTY; break;
			case player1Head : mapArray[x][y] = MapSign.player1Head; break;
			case player2Head : mapArray[x][y] = MapSign.player2Head; break;
			default: {
				throw new IllegalArgumentException("Can't handle " + attribute);
			}
		}
		convert2Dto1D();
		
	}
	
	public void createMap(Map map, String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(map);
			out.close();
			fileOut.close();
			System.out.printf("Your map has been saved in " + filename);
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	//TODO there is repetition of code here. Ask TA what to do
	public void loadMapFromFile(String filename) { 
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			setMap( (Map) in.readObject() );
			in.close();
			fileIn.close();
			System.out.printf("The following mapfile has been loaded " + filename);
		} catch (ClassNotFoundException c) {
			System.err.println("The location does not have a valid file");
			c.printStackTrace();
			return;
		} catch (IOException i) {
			System.err.println("Something went wrong, exiting loading of map");
			i.printStackTrace();
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
	
	public MapSign[][] getMap(){
		return this.mapArray;
	}
	
	public int getMapSize(){
		return convertedMapArray.length;
	}
	
	public void convert2Dto1D() {
		for (int i = 0 ; i < mapArray.length ; i++){
			for( int j = 0 ; j < mapArray.length ; j++){
				convertedMapArray[(i * mapArray.length) + j] = mapArray[j][i];
			}
		}
		
	}
		
	
	
	
	
	
	
	
	
}

public class Map {
	private int difficulty;
	private int height = 500;
	private int width = 500;
	private MapSign[][] mapArray = new MapSign[height][width];
	private String mapName;
	
	public Map(String mapName) { //initializes blank Map with just the edges filled in as WALL
		super();
		this.mapName = mapName;
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
	public boolean isOccupied(int x, int y){
		if (mapArray[x][y] != MapSign.EMPTY){
			return true;
		}
		else{
			return false;
		}
	}
	public MapSign getOccupation(int x, int y){
		return mapArray[x][y];
	}
	public void setOccupation(int x, int y, MapSign attribute){
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
	public void createMapFromFile(String fileName){ //TODO: createMapFromFile
	}
	public void saveMapToFile(String mapName){ //TODO: saveMapToFile
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
}
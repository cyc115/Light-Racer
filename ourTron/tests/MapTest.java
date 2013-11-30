/**
 * 
 */

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import GameCore.Map;
import GameCore.Map.MapSign;

/**
 *
 */
public class MapTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link GameCore.Map#createMap(GameCore.Map, java.lang.String)}.
	 */
	@Test
	public void testCreateMap() {
		Map map = new Map();
		Map.createMap(map, "testMap.map");
		Map readmap = null;
		try {
			FileInputStream fileIn = new FileInputStream("testMap.map");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			readmap = (Map) in.readObject();
			in.close();
			fileIn.close();
		} catch (ClassNotFoundException i) {
			i.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} 
		if(readmap == null)
			fail("The map was not assigned");
		else {
			boolean equals = readmap.getDifficulty() == map.getDifficulty();
			equals = equals && readmap.getHeight() == map.getHeight();
			equals = equals && readmap.getWidth() == map.getWidth();
			MapSign[][] theEnums = readmap.getMap();
			MapSign[][] otherEnums = map.getMap();
			for(int i = 0; i < theEnums.length; i++)
				for(int j = 0; j < theEnums[0].length; j++)
					equals = equals && theEnums[i][j] == otherEnums[i][j];
			equals = equals && readmap.getMapName().equals(map.getMapName());
			assertTrue("The db's are not equal",equals);
		}
	}

	/**
	 * Test method for {@link GameCore.Map#loadMapFromFile(java.lang.String)}.
	 */
	@Test
	public final void testLoadMapFromFile() {
		Map map = new Map();
		Map map2 = new Map();
		Map.createMap(map, "testMap.map");
		map2.loadMapFromFile("testMap.map");

		boolean equals = map2.getDifficulty() == map.getDifficulty();
		equals = equals && map2.getHeight() == map.getHeight();
		equals = equals && map2.getWidth() == map.getWidth();
		MapSign[][] theEnums = map2.getMap();
		MapSign[][] otherEnums = map.getMap();
		for (int i = 0; i < theEnums.length; i++)
			for (int j = 0; j < theEnums[0].length; j++)
				equals = equals && theEnums[i][j] == otherEnums[i][j];
		equals = equals && map2.getMapName().equals(map.getMapName());
		assertTrue("The db's are not equal", equals);
	}

	/**
	 * Test method for {@link GameCore.Map#saveMapToFile(java.lang.String)}.
	 */
	@Test
	public final void testSaveMapToFile() {
		Map map = new Map();
		Map map2 = new Map();
		map.saveMapToFile("testMap.map");
		map2.loadMapFromFile("testMap.map");

		boolean equals = map2.getDifficulty() == map.getDifficulty();
		equals = equals && map2.getHeight() == map.getHeight();
		equals = equals && map2.getWidth() == map.getWidth();
		MapSign[][] theEnums = map2.getMap();
		MapSign[][] otherEnums = map.getMap();
		for (int i = 0; i < theEnums.length; i++)
			for (int j = 0; j < theEnums[0].length; j++)
				equals = equals && theEnums[i][j] == otherEnums[i][j];
		equals = equals && map2.getMapName().equals(map.getMapName());
		assertTrue("The db's are not equal", equals);
	}

}

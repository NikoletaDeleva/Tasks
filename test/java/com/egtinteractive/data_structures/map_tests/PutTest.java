package com.egtinteractive.data_structures.map_tests;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class PutTest extends TestMap {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void putKeyValueTest(final Map<Integer, String> map) {
	final int randSize = ThreadLocalRandom.current().nextInt(1,100);
	
	fillMap(map, randSize);
	
	for(int index = 0; index < randSize; index++) {
	    final String valueAtPosition = map.get(index);
	    assertEquals(valueAtPosition, index +"" );
	}
    }
}

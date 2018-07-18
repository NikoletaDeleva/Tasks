package com.egtinteractive.data_structures.map_tests;

import java.util.UUID;

import com.egtinteractive.data_structures.map.Map;

public class TestMap implements ITestMap {

    @Override
    public void fillMap(final Map<Integer, String> map,  int randSize) {
	for (int index = 0; index < randSize; index++) {
	    final String randValue = UUID.randomUUID().toString();
	    map.put(index, randValue);
	}
    }
}

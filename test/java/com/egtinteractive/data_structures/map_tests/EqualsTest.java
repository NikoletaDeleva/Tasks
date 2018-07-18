package com.egtinteractive.data_structures.map_tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.Map;

public class EqualsTest {
    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @Test(dataProvider = "maps")
    public void equalsTest(final Map<Integer, String> map) {
	
    }
}

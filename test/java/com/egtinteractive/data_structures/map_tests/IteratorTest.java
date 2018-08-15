package com.egtinteractive.data_structures.map_tests;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.egtinteractive.data_structures.map.HashMap;
import com.egtinteractive.data_structures.map.HashMap.Node;
import com.egtinteractive.data_structures.map.Map;

public class IteratorTest {
    private int size;

    @DataProvider(name = "maps")
    public Object[][] createData() {
	return new Object[][] { { new HashMap<>() } };
    }

    @BeforeClass
    public void setSize() {
	this.size = ThreadLocalRandom.current().nextInt(150, 500);
    }

    @Test(dataProvider = "maps")
    public void iteratorOne(Map<String, Integer> map) {

	StringBuilder sb = new StringBuilder();
	for (HashMap.Node<String, Integer> entry : map) {
	    sb.append(entry.getKey()).append(entry.getValue()).append(System.lineSeparator());
	}
	Assert.assertEquals(sb.toString(), "");
    }

    @Test(dataProvider = "maps")
    public void iteratorTwo(Map<String, Integer> map) {
	java.util.Map<String, Integer> jMap = new java.util.HashMap<>();

	for (int index = 0; index < this.size; index++) {
	    map.put(String.valueOf(index), index);
	    jMap.put(String.valueOf(index), index);
	}

	int counter = 0;

	for (HashMap.Node<String, Integer> entry : map) {
	    String key = entry.getKey();
	    Integer value = entry.getValue();
	    Assert.assertTrue(jMap.containsKey(key));
	    Assert.assertTrue(jMap.containsValue(value));
	    counter++;
	}
	Assert.assertEquals(counter, this.size);
    }

    @Test(dataProvider = "maps")
    public void removeTree(Map<String, Integer> map) {
	for (int index = 0; index < this.size; index++) {
	    map.put(String.valueOf(index), index);

	}
	Iterator<Node<String, Integer>> iterator = map.iterator();
	int pos = map.size();
	while (pos-- > 0) {
	    iterator.next();
	    iterator.remove();
	    Assert.assertEquals(map.size(), pos);
	}

    }

    @Test(dataProvider = "maps")
    public void iteratorFour(Map<String, Integer> map) {
	java.util.List<String> list = new java.util.ArrayList<>();

	for (int index = 0; index < 10; index++) {
	    map.put(String.valueOf(index), index);
	    list.add(String.valueOf(index));
	}
	int counter = 0;
	final Iterator<Node<String, Integer>> mapItr = map.iterator();
	final Iterator<String> listItr = list.iterator();
	int pos = map.size();
	while (pos-- > 0) {
	    Assert.assertTrue(Objects.equals(mapItr.next().getKey(), listItr.next()));
	    counter++;
	}
	Assert.assertEquals(counter, 10);
    }
}

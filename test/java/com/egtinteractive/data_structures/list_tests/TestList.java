package com.egtinteractive.data_structures.list_tests;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.egtinteractive.data_structures.list.List;

public class TestList {

    public void fillListWithIntegers(int size, List<Integer> list) {
	for (int index = 0; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
    }

    public void fillListWithStrings(int size, List<String> list) {
	for (int index = 0; index < size; index++) {
	    list.add(UUID.randomUUID().toString());
	}
    }
}

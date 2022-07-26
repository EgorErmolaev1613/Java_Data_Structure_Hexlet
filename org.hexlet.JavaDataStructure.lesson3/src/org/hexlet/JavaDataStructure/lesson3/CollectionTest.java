package org.hexlet.JavaDataStructure.lesson3;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CollectionTest {
    private static final int ELEMENTS_AMOUNT = 10000;


    @Benchmark
    public void testArrayListAddMethod() {
        final List<Integer> intList = new ArrayList();

        for (int i = 0; ++i < 1_000_000;)
            intList.add(i);
    }

    @Benchmark
    public void testLinkedListAddMethod() {
        final List<Integer> intList = new LinkedList<>();

        for (int i = 0; ++i < 1_000_000;)
            intList.add(i);
    }
}
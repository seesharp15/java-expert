package dev.expert.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public final class ParallelPitfall {
    private ParallelPitfall() {}

    public static List<Integer> incorrectParallelCollect(int n) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, n).parallel().forEach(list::add);
        return list;
    }

    public static List<Integer> fixedParallelCollect(int n) {
        throw new UnsupportedOperationException("TODO: implement thread-safe parallel collection");
    }
}

























































/*
ANSWER KEY:

public static List<Integer> fixedParallelCollect(int n) {
    return java.util.stream.IntStream.range(0, n)
        .parallel()
        .boxed()
        .collect(java.util.stream.Collectors.toCollection(java.util.concurrent.CopyOnWriteArrayList::new));
}
*/

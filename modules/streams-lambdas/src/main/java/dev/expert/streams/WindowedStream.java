package dev.expert.streams;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class WindowedStream {
    private WindowedStream() {}

    public static <T> Stream<List<T>> windowed(Stream<T> source, int size, int step) {
        throw new UnsupportedOperationException("TODO: implement sliding window stream");
    }
}

























































/*
ANSWER KEY:

public static <T> Stream<List<T>> windowed(Stream<T> source, int size, int step) {
    java.util.List<T> data = source.toList();
    java.util.List<List<T>> windows = new java.util.ArrayList<>();
    for (int i = 0; i + size <= data.size(); i += step) {
        windows.add(java.util.List.copyOf(data.subList(i, i + size)));
    }
    return windows.stream();
}
*/

package dev.expert.lang;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A small persistent map that keeps insertion order and is safe for covariance via mapping operations.
 * Each mutating method returns a new instance leaving the original unchanged.
 */
public final class TupleMap<K, V> {

    private final Map<K, V> backing;

    private TupleMap(Map<K, V> backing) {
        this.backing = backing;
    }

    public static <K, V> TupleMap<K, V> empty() {
        return new TupleMap<>(Collections.emptyMap());
    }

    public int size() {
        return backing.size();
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(backing.get(key));
    }

    public boolean containsKey(K key) {
        return backing.containsKey(key);
    }

    public Map<K, V> asUnmodifiableMap() {
        return Collections.unmodifiableMap(backing);
    }

    public TupleMap<K, V> put(K key, V value) {
        throw new UnsupportedOperationException("TODO: implement persistent put");
    }

    public TupleMap<K, V> remove(K key) {
        throw new UnsupportedOperationException("TODO: implement persistent remove");
    }

    public TupleMap<K, V> filterKeys(Predicate<? super K> predicate) {
        throw new UnsupportedOperationException("TODO: implement filterKeys");
    }

    public <K2> TupleMap<K2, V> mapKeys(Function<? super K, ? extends K2> mapper) {
        throw new UnsupportedOperationException("TODO: implement mapKeys");
    }

    public <V2> TupleMap<K, V2> mapValues(Function<? super V, ? extends V2> mapper) {
        throw new UnsupportedOperationException("TODO: implement mapValues");
    }

    private static <K, V> Map<K, V> copy(Map<K, V> source) {
        return new LinkedHashMap<>(source);
    }
}

























































/*
ANSWER KEY:

 * Problem: implement a tiny persistent (immutable) map that preserves insertion order.
 * Approach: copy the backing LinkedHashMap on write so callers see old versions unchanged.
 * Why: mirrors functional collections (Scala Map/Immutable) but in plain Java.

public TupleMap<K, V> put(K key, V value) {
    Map<K, V> copy = copy(backing);          // copy-on-write to keep previous instances immutable
    copy.put(key, value);
    return new TupleMap<>(copy);
}

public TupleMap<K, V> remove(K key) {
    if (!backing.containsKey(key)) return this; // no-op fast path
    Map<K, V> copy = copy(backing);             // duplicate before mutating
    copy.remove(key);
    return new TupleMap<>(copy);
}

public TupleMap<K, V> filterKeys(Predicate<? super K> predicate) {
    Map<K, V> copy = new LinkedHashMap<>();     // preserve insertion order
    for (var e : backing.entrySet()) {
        if (predicate.test(e.getKey())) copy.put(e.getKey(), e.getValue());
    }
    return new TupleMap<>(copy);
}

public <K2> TupleMap<K2, V> mapKeys(Function<? super K, ? extends K2> mapper) {
    Map<K2, V> copy = new LinkedHashMap<>();    // new key type, same values
    for (var e : backing.entrySet()) {
        copy.put(mapper.apply(e.getKey()), e.getValue());
    }
    return new TupleMap<>(copy);
}

public <V2> TupleMap<K, V2> mapValues(Function<? super V, ? extends V2> mapper) {
    Map<K, V2> copy = new LinkedHashMap<>();    // same keys, mapped values
    for (var e : backing.entrySet()) {
        copy.put(e.getKey(), mapper.apply(e.getValue()));
    }
    return new TupleMap<>(copy);
}
*/

package dev.expert.lang;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TupleMapTest {

    @Test
    void putIsPersistent() {
        var empty = TupleMap.<String, Integer>empty();
        var withA = empty.put("a", 1);
        var withB = withA.put("b", 2);

        assertThat(empty.size()).isZero();
        assertThat(withA.size()).isEqualTo(1);
        assertThat(withB.size()).isEqualTo(2);
        assertThat(withB.get("a").orElseThrow()).isEqualTo(1);
    }

    @Test
    void removalDoesNotMutateOriginal() {
        var base = TupleMap.<String, Integer>empty().put("x", 9).put("y", 8);
        var removed = base.remove("x");

        assertThat(base.containsKey("x")).isTrue();
        assertThat(removed.containsKey("x")).isFalse();
    }

    @Test
    void mapKeysAndValuesAreCovariantSafe() {
        var base = TupleMap.<String, Integer>empty()
            .put("one", 1)
            .put("four", 4);

        var lengths = base.mapKeys(String::length);
        var doubled = base.mapValues(v -> v * 2);

        assertThat(lengths.asUnmodifiableMap()).containsExactlyEntriesOf(Map.of(3, 1, 4, 4));
        assertThat(doubled.get("four").orElseThrow()).isEqualTo(8);
    }

    @Test
    void filterKeysKeepsOrder() {
        var base = TupleMap.<String, Integer>empty()
            .put("c", 3)
            .put("a", 1)
            .put("b", 2);

        var filtered = base.filterKeys(k -> !k.equals("a"));
        assertThat(filtered.asUnmodifiableMap().keySet()).containsExactly("c", "b");
    }

    @Test
    void cannotModifyUnmodifiableView() {
        var base = TupleMap.<String, Integer>empty().put("c", 3);
        var view = base.asUnmodifiableMap();
        assertThatThrownBy(() -> view.put("boom", 1)).isInstanceOf(UnsupportedOperationException.class);
    }
}

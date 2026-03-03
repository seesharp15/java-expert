package dev.expert.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnimalSerdeTest {

    @Test
    void serializesAndDeserializesDog() throws Exception {
        var dog = new AnimalSerde.Dog("fido", 3);
        var json = AnimalSerde.toJson(dog);
        var decoded = AnimalSerde.fromJson(json);
        assertThat(decoded).isEqualTo(dog);
    }

    @Test
    void serializesAndDeserializesCat() throws Exception {
        var cat = new AnimalSerde.Cat("luna", true);
        var json = AnimalSerde.toJson(cat);
        var decoded = AnimalSerde.fromJson(json);
        assertThat(decoded).isEqualTo(cat);
    }
}

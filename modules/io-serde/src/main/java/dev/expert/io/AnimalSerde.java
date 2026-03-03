package dev.expert.io;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;

public final class AnimalSerde {
    private static final ObjectMapper mapper = JsonMapper.builder().findAndAddModules().build();

    private AnimalSerde() {}

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    public sealed interface Animal permits Dog, Cat { }

    public record Dog(String name, int bones) implements Animal { }
    public record Cat(String name, boolean indoor) implements Animal { }

    public static String toJson(Animal animal) throws IOException {
        throw new UnsupportedOperationException("TODO: implement polymorphic serialization");
    }

    public static Animal fromJson(String json) throws IOException {
        throw new UnsupportedOperationException("TODO: implement polymorphic deserialization");
    }

    public static ObjectMapper mapper() {
        return mapper;
    }
}

























































/*
ANSWER KEY:

public static String toJson(Animal animal) throws IOException {
    return mapper.writeValueAsString(animal);
}

public static Animal fromJson(String json) throws IOException {
    return mapper.readValue(json, Animal.class);
}
*/

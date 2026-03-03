package dev.expert.hexagonal;

import java.util.UUID;

public record Quote(UUID id, String author, String body, long version) {
    public Quote {
        id = id == null ? UUID.randomUUID() : id;
        if (author == null || author.isBlank()) throw new IllegalArgumentException("author required");
        if (body == null || body.isBlank()) throw new IllegalArgumentException("body required");
    }
}

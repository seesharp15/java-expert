package dev.expert.hexagonal;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryQuoteRepository implements QuoteRepository {
    private final Map<UUID, Quote> store = new LinkedHashMap<>();

    @Override
    public Quote save(Quote quote) {
        throw new UnsupportedOperationException("TODO: implement save with optimistic versioning");
    }

    @Override
    public Optional<Quote> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Quote> findAll() {
        return List.copyOf(store.values());
    }
}

























































/*
ANSWER KEY:

@Override
public Quote save(Quote quote) {
    Quote existing = store.get(quote.id());
    if (existing != null && existing.version() != quote.version()) {
        throw new IllegalStateException("Optimistic lock failure");
    }
    Quote saved = new Quote(quote.id(), quote.author(), quote.body(), quote.version() + 1);
    store.put(saved.id(), saved);
    return saved;
}
*/

package dev.expert.hexagonal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class QuoteService {
    private final QuoteRepository repository;

    public QuoteService(QuoteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Quote create(String author, String body) {
        throw new UnsupportedOperationException("TODO: implement create with validation");
    }

    @Transactional(readOnly = true)
    public Quote get(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Quote> list() {
        return repository.findAll();
    }
}

























































/*
ANSWER KEY:

@Transactional
public Quote create(String author, String body) {
    if (author == null || author.isBlank()) throw new IllegalArgumentException("author required");
    if (body == null || body.isBlank()) throw new IllegalArgumentException("body required");
    Quote q = new Quote(null, author, body, 0);
    return repository.save(q);
}
*/

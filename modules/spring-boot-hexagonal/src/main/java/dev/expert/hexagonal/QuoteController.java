package dev.expert.hexagonal;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quotes")
@Validated
public class QuoteController {
    private final QuoteService service;

    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Quote> create(@RequestBody QuoteRequest request) {
        var quote = service.create(request.author(), request.body());
        return ResponseEntity.ok(quote);
    }

    @GetMapping("/{id}")
    public Quote byId(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<Quote> list() {
        return service.list();
    }

    public record QuoteRequest(String author, String body) { }
}

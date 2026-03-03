package dev.expert.hexagonal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QuoteController.class)
@Import({QuoteService.class, InMemoryQuoteRepository.class})
class QuoteControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    QuoteRepository repository;

    @Test
    void createsAndRetrievesQuote() throws Exception {
        var requestJson = mapper.writeValueAsString(new QuoteController.QuoteRequest("author", "body"));

        var postResponse = mvc.perform(post("/quotes").contentType("application/json").content(requestJson))
            .andExpect(status().isOk())
            .andReturn();

        Quote created = mapper.readValue(postResponse.getResponse().getContentAsByteArray(), Quote.class);
        assertThat(created.author()).isEqualTo("author");
        assertThat(created.body()).isEqualTo("body");

        var getResponse = mvc.perform(get("/quotes/" + created.id()))
            .andExpect(status().isOk())
            .andReturn();

        Quote fetched = mapper.readValue(getResponse.getResponse().getContentAsByteArray(), Quote.class);
        assertThat(fetched).isEqualTo(created);
    }
}

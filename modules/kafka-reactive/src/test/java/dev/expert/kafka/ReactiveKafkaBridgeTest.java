package dev.expert.kafka;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Requires Kafka Testcontainer; implement when ready")
class ReactiveKafkaBridgeTest {
    @Test
    void roundTripsMessage() {
        // TODO: spin up Kafka container, send/consume with backpressure
    }
}

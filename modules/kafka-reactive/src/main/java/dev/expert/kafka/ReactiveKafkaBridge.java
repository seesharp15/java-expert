package dev.expert.kafka;

import io.projectreactor.core.publisher.Flux;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collection;

public class ReactiveKafkaBridge<K, V> {
    private final KafkaProducer<K, V> producer;
    private final KafkaConsumer<K, V> consumer;

    public ReactiveKafkaBridge(KafkaProducer<K, V> producer, KafkaConsumer<K, V> consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    public Flux<ConsumerRecord<K, V>> consumeReactive(Collection<String> topics) {
        throw new UnsupportedOperationException("TODO: implement reactive consumer with backpressure-aware polling");
    }

    public Flux<Void> publishReactive(Flux<ProducerRecord<K, V>> records) {
        throw new UnsupportedOperationException("TODO: implement reactive producer using send async");
    }
}

























































/*
ANSWER KEY (simplified backpressure-aware approach):

 * Problem: bridge Kafka client APIs to Reactor Flux with backpressure.
 * Approach: poll in a generator for consuming; wrap async producer send in Flux.create.
 * Why: shows reactive wrappers over callback/poll APIs.

public Flux<ConsumerRecord<K, V>> consumeReactive(Collection<String> topics) {
    consumer.subscribe(topics);
    return Flux.generate(sink -> {
        var records = consumer.poll(Duration.ofMillis(100));
        if (records.isEmpty()) return;
        for (var rec : records) sink.next(rec);
    });
}

public Flux<Void> publishReactive(Flux<ProducerRecord<K, V>> records) {
    return records.flatMap(pr -> Flux.create(sink -> {
        producer.send(pr, (md, ex) -> {
            if (ex != null) sink.error(ex); else { sink.next(null); sink.complete(); }
        });
    }));
}
*/

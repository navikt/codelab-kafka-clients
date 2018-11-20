package no.nav.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class FizzbuzzConsumer {


    private final static Logger LOG = LogManager.getLogger();

    private final KafkaConsumer<String, String> consumer;

    public FizzbuzzConsumer(final String groupId, List<String> topics) {
        Objects.requireNonNull(groupId, "groupId cannot be empty or null");
        Objects.requireNonNull(topics, "topics cannot be null");
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(topics);
    }

    void poll() {
        LOG.info("Subscribing to " + consumer.toString());
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(30));
                for (ConsumerRecord<String, String> record : records) {
                    LOG.info("topic = {}, partition = {}, offset = {}, value= {}",
                        record.topic(), record.partition(), record.offset(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        FizzbuzzConsumer fizzbuzzConsumer = new FizzbuzzConsumer(null, Collections.singletonList("FizzBuzzNumber"));
        fizzbuzzConsumer.poll();
    }
}

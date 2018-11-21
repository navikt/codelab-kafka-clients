package no.nav.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class FizzBuzzProducer {


    private final static Logger LOG = LogManager.getLogger();
    private final KafkaProducer<String, String> producer;

    public FizzBuzzProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.producer = new KafkaProducer<>(props);
    }

    public RecordMetadata produce(final String topic, final Number message) {
        try {
            return producer.send(new ProducerRecord<>(topic, message.toString())).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new ProducerException(e);
        }
    }


    public static void main(String[] args) {
        FizzBuzzProducer producer = new FizzBuzzProducer();

        IntStream.range(1, 11).forEach( number -> {
                Integer value = new Random().nextInt(100);
                RecordMetadata metadata = producer.produce("FizzBuzzNumber", value);
                LOG.info("Produced value {}, to topic {}, partition {}, offset {}", value, metadata.topic(), metadata.partition(), metadata.offset());
            }

        );

    }

}


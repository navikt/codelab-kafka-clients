package no.nav.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

public class FizzBuzzCandidateProducer {


    private final static Logger LOG = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        final KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        IntStream.range(1, 11).forEach(n -> {
            final Number fizzBuzzCandidate = new Random().nextInt(100);
            //todo - partition key..
            final ProducerRecord<String, String> record = new ProducerRecord<>("FizzBuzzNumberEntered", fizzBuzzCandidate.toString());

            //produce record
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        LOG.info("Record send to Topic: " + metadata.topic() + " Partition: " + metadata.partition() + " Offset: " + metadata.offset());
                    } else {
                        LOG.error("Failed to send record to Kafka", exception);
                    }

                }
            });
        });

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();

    }

}


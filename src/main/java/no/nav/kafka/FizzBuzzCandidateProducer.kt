package no.nav.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.serialization.IntegerSerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.logging.log4j.LogManager
import java.util.*
import java.util.stream.IntStream

object FizzBuzzCandidateProducer {
    private val LOG = LogManager.getLogger()
    @JvmStatic
    fun main(args: Array<String>) {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = IntegerSerializer::class.java.name
        val producer = KafkaProducer<String, Int>(props)
        IntStream.range(1, 11).forEach { _ ->
            val fizzBuzzCandidate = Random().nextInt(100)
            val record = ProducerRecord<String, Int>("FizzBuzzNumberEntered", fizzBuzzCandidate)
            //produce record
            producer.send(record) { metadata: RecordMetadata, exception: Exception? ->
                if (exception == null) {
                    LOG.info("Record send to Topic: " + metadata.topic() + " Partition: " + metadata.partition() + " Offset: " + metadata.offset())
                } else {
                    LOG.error("Failed to send record to Kafka", exception)
                }
            }
        }
        // flush data
        producer.flush()
        // flush and close producer
        producer.close()
    }
}
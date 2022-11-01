package dev.ssouza.publish;

import dev.ssouza.domain.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaPublisher implements Publisher {
    private final KafkaProducer<String, Message> kafkaProducer;

    public KafkaPublisher() {
        final Properties kafkaProps = new Properties();

        String defaultClusterValue = "localhost:9092";

        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, defaultClusterValue);
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "0");


        this.kafkaProducer = new KafkaProducer<>(kafkaProps);
    }

    @Override
    public void publish(Message message) {
        kafkaProducer.send(new ProducerRecord<>("PACKETS", message));
    }
}

package dev.ssouza.consumer;

import dev.ssouza.domain.Message;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class KConsumer {
    public KConsumer() {
        final Properties kafkaProps = new Properties();

        final String defaultClusterValue = "localhost:9092";

        kafkaProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, defaultClusterValue);
        kafkaProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        kafkaProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, Message> kStream = streamsBuilder.stream("PACKETS");

        kStream.foreach((k, v) -> System.out.println(v));

        final Topology topology = streamsBuilder.build();
        KafkaStreams streams = new KafkaStreams(topology, kafkaProps);

        System.out.println("Starting streams...");
        streams.start();
    }
}

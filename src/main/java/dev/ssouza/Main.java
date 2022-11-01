package dev.ssouza;

import dev.ssouza.consumer.KConsumer;
import dev.ssouza.publish.KafkaPublisher;
import dev.ssouza.publish.Publisher;
import org.pcap4j.core.PcapNativeException;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws PcapNativeException, UnknownHostException {
        final Publisher publisher = new KafkaPublisher();
        final Sniffer sniffer = new Sniffer(publisher);

        sniffer.start();

        System.out.println("Starting Sniffer...");

        new KConsumer();
    }

}
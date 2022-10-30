package dev.ssouza;

import dev.ssouza.publish.ConsolePublisher;
import dev.ssouza.publish.Publisher;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws PcapNativeException, UnknownHostException, NotOpenException {
        final Publisher publisher = new ConsolePublisher();
        final Sniffer sniffer = new Sniffer(publisher);

        sniffer.start();
    }

}
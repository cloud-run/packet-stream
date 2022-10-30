package dev.ssouza;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

import java.net.UnknownHostException;

public class Main {
    private static final int COUNT = 5;

    public static void main(String[] args) throws PcapNativeException, UnknownHostException, NotOpenException {
        final Publisher publisher = new ConsolePublisher();
        final Sniffer sniffer = new Sniffer(publisher);

        sniffer.start();
    }

}
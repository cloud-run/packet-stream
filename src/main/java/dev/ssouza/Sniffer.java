package dev.ssouza;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;

import java.io.EOFException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

public class Sniffer {
    private final Publisher publisher;

    public Sniffer(final Publisher publisher) {
        this.publisher = publisher;
    }

    public void start() throws PcapNativeException, UnknownHostException {
        final InetAddress addr = InetAddress.getByName("192.168.15.4");
        final PcapNetworkInterface nif = Pcaps.getDevByAddress(addr);

        System.out.println(nif.getName() + "(" + nif.getDescription() + ")");

        try (final PcapHandle handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10)) {

            while (true) {
                try {
                    final Packet packet = handle.getNextPacketEx();
                    final Message message = new Message(packet.getHeader().toString(), packet.getPayload().toString());

                    publisher.publish(message);
                } catch (TimeoutException ignored) {
                } catch (EOFException e) {
                    e.printStackTrace();
                } catch (NotOpenException e) {
                    throw new RuntimeException(e);
                } catch (PcapNativeException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

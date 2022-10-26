package dev.ssouza;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.pcap4j.util.NifSelector;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws PcapNativeException {
        PcapNetworkInterface nif;
        try {
            nif = new NifSelector().selectNetworkInterface();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (nif == null) {
            return;
        }

        System.out.println(nif.getName() + "(" + nif.getDescription() + ")");

        final PcapHandle handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);

        PacketListener listener =
                new PacketListener() {
                    @Override
                    public void gotPacket(Packet packet) {
                        System.out.println(handle.getTimestamp());

                        System.out.println("start a heavy task");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {

                        }
                        System.out.println("done");
                    }
                };

        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            handle.loop(5, listener, pool); // This is better than handle.loop(5, listener);
            pool.shutdown();
        } catch (InterruptedException | NotOpenException e) {
            e.printStackTrace();
        }

        handle.close();
    }
}
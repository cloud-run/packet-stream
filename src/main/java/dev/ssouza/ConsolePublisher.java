package dev.ssouza;

public class ConsolePublisher implements Publisher{
    @Override
    public void publish(Message message) {
        System.out.println(message);
    }
}

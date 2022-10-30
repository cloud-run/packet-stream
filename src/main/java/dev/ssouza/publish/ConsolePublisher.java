package dev.ssouza.publish;

import dev.ssouza.domain.Message;

public class ConsolePublisher implements Publisher {
    @Override
    public void publish(Message message) {
        System.out.println(message);
    }
}

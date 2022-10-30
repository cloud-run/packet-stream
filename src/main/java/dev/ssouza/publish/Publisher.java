package dev.ssouza.publish;

import dev.ssouza.domain.Message;

public interface Publisher {
    void publish(Message message);
}

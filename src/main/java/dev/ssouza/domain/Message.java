package dev.ssouza.domain;

public class Message {
    private String header;
    private String payload;

    public Message(String header, String payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header='" + header + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}

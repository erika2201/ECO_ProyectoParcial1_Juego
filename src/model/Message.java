package model;

public class Message {
    private String key;

    public Message(String key) {
        this.key = key;
    }

    public Message() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
package Model;

import Model.CommunicationChannel;
import Model.Entity;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String content;
    private Entity sender;
    private Entity receiver;
    private CommunicationChannel communicationChannel;
    private LocalDateTime timestamp;

    public Message(int id, String content, Entity sender, Entity receiver, CommunicationChannel communicationChannel) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.communicationChannel = communicationChannel;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Entity getSender() {
        return sender;
    }

    public void setSender(Entity sender) {
        this.sender = sender;
    }

    public Entity getReceiver() {
        return receiver;
    }

    public void setReceiver(Entity receiver) {
        this.receiver = receiver;
    }

    public CommunicationChannel getCommunicationChannel() {
        return communicationChannel;
    }

    public void setCommunicationChannel(CommunicationChannel communicationChannel) {
        this.communicationChannel = communicationChannel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
package Model;

import Model.Entity;
import Model.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommunicationChannel {
    private int id;
    private String name;
    private boolean isPrivate;
    private List<Entity> participants;
    private List<Message> messages;

    public CommunicationChannel(int id, String name, boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.isPrivate = isPrivate;
        this.participants = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public List<Entity> getParticipants() {
        return participants;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addParticipant(Entity participant) {
        if (!isPrivate || !participants.contains(participant)) {
            participants.add(participant);
        }
    }

    public void removeParticipant(Entity participant) {
        if (!isPrivate || participants.contains(participant)) {
            participants.remove(participant);
        }
    }

    public void sendMessage(Message message) {
        messages.add(message);
        message.setCommunicationChannel(this);
        message.setTimestamp(LocalDateTime.now());
    }

    public void receiveMessage(Message message) {
        if (!messages.contains(message)) {
            messages.add(message);
        }
    }
}
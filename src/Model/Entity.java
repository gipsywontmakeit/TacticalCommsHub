package Model;

import Enums.Rank;

import java.util.List;

public class Entity {
    private int id;
    private String username;
    private String password;
    private Rank rank;
    private List<String> commsChannels;

    public Entity(int id, String username, String password, Rank rank) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Rank getRank() {
        return rank;
    }
    public List<String> getCommsChannels() {
        return commsChannels;
    }

    public void addCommsChannels(String channel) {
        commsChannels.add(channel);
    }

    public void removeCommsChannels(String channel) {
        commsChannels.remove(channel);
    }

    public void sendMessage(String receiver, String message) {

    }

    public void requestHighTask(String description) {

    }

    public void acceptHighTaskRequest (String description) {

    }

    public void receiveMessageSeriousIncident(String message) {

    }

    public void beNotified(String message) {

    }

    public String toString() {
        return "ID: " + id +
                ", Username: " + username;
           }
     
}

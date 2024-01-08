package Model;

public class Message {
    private int id;
    private String username;
    private String message;
    private boolean isNotified;

    public Message(int id, String username, String message, boolean isNotified) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.isNotified = isNotified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

     public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

     public boolean getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(boolean isNotified) {
        this.isNotified = isNotified;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", isNotified=" + isNotified +
                '}';
    }

}

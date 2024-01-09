package Model;

import Enums.RequestType;

import java.time.LocalDateTime;

public class Request {
    private int id;
    private String content;
    private Entity requester;
    private Entity recipient;
    private RequestType type;
    private LocalDateTime timestamp;

    public Request(int id, String content, Entity requester, Entity recipient, RequestType type) {
        this.id = id;
        this.content = content;
        this.requester = requester;
        this.recipient = recipient;
        this.type = type;
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

    public Entity getRequester() {
        return requester;
    }

    public void setRequester(Entity requester) {
        this.requester = requester;
    }

    public Entity getRecipient() {
        return recipient;
    }

    public void setRecipient(Entity recipient) {
        this.recipient = recipient;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

package Model;

import Enums.Rank;
import Model.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String message;
    private NotificationType type;
    private Entity notificationRecipient;
    private List<Entity> notificationGroup;
    private boolean isSeriousIncident;

    public Notification(String message, NotificationType type, Entity recipient, List<Entity> group, boolean isSeriousIncident) {
        this.id = UUID.randomUUID();
        this.message = message;
        this.type = type;
        this.notificationRecipient = recipient;
        this.notificationGroup = group;
        this.isSeriousIncident = isSeriousIncident;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public boolean isSeriousIncident() {
        return isSeriousIncident;
    }

    public boolean isRelevantForProfile(Entity profile) {
        if (notificationRecipient.equals(profile)) {
            return true;
        }

        if (notificationGroup.contains(profile)) {
            return true;
        }

        if (isSeriousIncident && profile.getRank().equals(Rank.Tenente)) {
            return true;
        }

        return false;
    }

    public enum NotificationType {
        INFO, WARNING, ERROR
    }

}

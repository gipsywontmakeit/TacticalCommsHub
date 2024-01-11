package Server;

import Model.Entity;
import Model.Notification;

import java.util.List;

public class NotificationManager {

    public void sendMulticastNotification(String message, List<Entity> group) {
        Notification notification = new Notification(message, Notification.NotificationType.INFO, null, group, false);
        for (Entity entity : group) {
            entity.receiveNotification(notification);
        }
        System.out.println("Multicast notification sent to group: " + group.size() + " recipients");
    }

    public void sendBroadcastNotification(String message, List<Entity> allEntities) {
        Notification notification = new Notification(message, Notification.NotificationType.INFO, null, allEntities, false);
        for (Entity entity : allEntities) {
            entity.receiveNotification(notification);
        }
        System.out.println("Broadcast notification sent to all entities: " + allEntities.size() + " recipients");
    }
}

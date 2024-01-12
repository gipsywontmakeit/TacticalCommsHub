    package Model;

    import Enums.Rank;
    import Model.Notification;
    import java.util.ArrayList;
    import java.util.List;

    public class Entity {
        private int id;
        private String username;
        private String password;
        private Rank rank;
        private List<String> commsChannels;
        private List<Message> receivedMessages;
        private List<Notification> notifications;

        public Entity(){

        }

        public Entity(int id, String username, String password, Rank rank) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.rank = rank;
            this.commsChannels = new ArrayList<>();
            this.receivedMessages = new ArrayList<>();
            this.notifications = new ArrayList<>();
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

        public boolean isTenente() {
            return Rank.Tenente.equals(rank);
        }
        public List<String> getCommsChannels() {
            return commsChannels;
        }

        public void addCommsChannels(String channel) {
            commsChannels.add(channel);
        }

        public void removeCommsChannel(String channel) {
            commsChannels.remove(channel);
        }

        public void sendMessage(Entity receiver, String messageContent) {
            int messageId = receivedMessages.size() + 1;
            Message message = new Message(messageId, messageContent, this, receiver, null);
            receiver.receiveMessage(message);
        }

        public void receiveMessage(Message message) {
            receivedMessages.add(message);
            System.out.println("Message received from " + message.getSender().getUsername() + ": " + message.getContent());
        }

        public void joinCommunicationChannel(String channel) {
            if (!commsChannels.contains(channel)) {
                commsChannels.add(channel);
                System.out.println(username + " entrou no canal: " + channel);
            } else {
                System.out.println(username + " já está no canal: " + channel);
            }
        }

        public void leaveCommunicationChannel(String channel) {
            if (commsChannels.contains(channel)) {
                commsChannels.remove(channel);
                System.out.println(username + " saiu do canal: " + channel);
            } else {
                System.out.println(username + " não está no canal: " + channel);
            }
        }

        public void requestHighTask(String description) {
            if (this.rank == Rank.Soldado) {
                System.out.println("Soldado " + username + " não tem permissão para realizar tarefas complexas.");
                return;
            }
            // Lógica para enviar solicitação de tarefa complexa
            System.out.println("Solicitação de tarefa complexa enviada por " + username + ": " + description);
        }

        public void acceptHighTaskRequest(Entity requester, String description) {
            if (this.rank == Rank.Tenente && requester.getRank() != Rank.Sargento) {
                System.out.println("Tenente " + username + " aceitou a solicitação de " + requester.getUsername() + " para a tarefa: " + description);
                // Lógica para aceitar a solicitação de tarefa complexa
            } else {
                System.out.println(username + " não tem permissão para aceitar solicitações de tarefas complexas.");
            }
        }

        public void receiveNotification(Notification notification) {
            notifications.add(notification);
        }

        public void processNotifications() {
            for (Notification notification : notifications) {
                if (notification.isRelevantForProfile(this)) {
                    System.out.println("Notification: " + notification.getMessage());

                    switch (notification.getType()) {
                        case INFO:
                            handleInfoNotification(notification);
                            break;
                        case WARNING:
                            handleWarningNotification(notification);
                            break;
                        case ERROR:
                            handleErrorNotification(notification);
                            break;
                        default:
                            handleDefaultNotification(notification);
                            break;
                    }

                    if (notification.isSeriousIncident()) {
                        System.out.println("This is a serious incident: " + notification.getMessage());
                        // Faça algo para lidar com o incidente sério
                    }
                }
            }
        }

        public String toString() {
            return "ID: " + id +
                    ", Username: " + username;
               }

        private void handleInfoNotification(Notification notification) {
            System.out.println("Info Notification: " + notification.getMessage());
        }

        private void handleWarningNotification(Notification notification) {
            System.out.println("Warning Notification: " + notification.getMessage());
        }

        private void handleErrorNotification(Notification notification) {
            System.out.println("Error Notification: " + notification.getMessage());
        }

        private void handleDefaultNotification(Notification notification) {
            System.out.println("Default Notification: " + notification.getMessage());
        }

        public void emitNotification(Notification notification, Entity recipient) {
            recipient.receiveNotification(notification);
        }

        public void emitNotificationToChannel(Notification notification, String channel, List<Entity> allEntities) {
            for (Entity entity : allEntities) {
                if (entity.getCommsChannels().contains(channel)) {
                    entity.receiveNotification(notification);
                }
            }
        }

    }

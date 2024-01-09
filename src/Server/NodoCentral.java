package Server;

import Enums.Rank;
import Enums.RequestType;
import Model.Entity;
import Model.Request;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class NodoCentral {
    private List<Entity> users;
    private Map<Entity, Integer> requestsCount;
    private Map<Entity, Integer> authorizationsCount;
    private Map<LocalDateTime, Integer> activeUsersCount;


    public NodoCentral() {
        users = new ArrayList<>();
        requestsCount = new HashMap<>();
        authorizationsCount = new HashMap<>();
        scheduleReportingTask();
    }

    public void processRequest(Request request) {
        if (request.getType() == RequestType.LAUNCH_MISSILE) {
            if (request.getRequester().getRank() == Rank.SOLDADO &&
                    request.getRecipient().getRank() == Rank.TENENTE) {
                System.out.println("Pedido de lançamento de míssil recebido!");
                acceptRequest(request);
            } else {
                System.out.println("Pedido de lançamento de míssil não autorizado devido à hierarquia.");
            }
        } else {
            System.out.println("Pedido não reconhecido ou não suportado.");
        }

        updateRequestsCount(request.getRequester());
    }

    private void acceptRequest(Request request) {
        System.out.println("Pedido aceite: " + request.getContent());

        updateAuthorizationsCount(request.getRecipient());
    }

    private void updateRequestsCount(Entity requester) {
        if (requestsCount.containsKey(requester)) {
            int count = requestsCount.get(requester);
            requestsCount.put(requester, count + 1);
        } else {
            requestsCount.put(requester, 1);
        }
    }

    private void updateAuthorizationsCount(Entity recipient) {
        if (authorizationsCount.containsKey(recipient)) {
            int count = authorizationsCount.get(recipient);
            authorizationsCount.put(recipient, count + 1);
        } else {
            authorizationsCount.put(recipient, 1);
        }
    }

    private void scheduleReportingTask() {
        Timer timer = new Timer();
        long delay = 0;
        long interval = Duration.ofMinutes(2).toMillis();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                reportActiveUsersToHighestRank();
            }
        }, delay, interval);
    }

    private void reportActiveUsersToHighestRank() {
        Rank highestRank = getHighestRank();

        for (Entity user : users) {
            if (user.getRank() == highestRank) {
                sendActiveUsersReport(user, calculateActiveUsersCount());
            }
        }
    }

    private Rank getHighestRank() {
        Rank highestRank = Rank.SOLDADO;
        for (Entity user : users) {
            if (user.getRank().ordinal() > highestRank.ordinal()) {
                highestRank = user.getRank();
            }
        }
        return highestRank;
    }

    private int calculateActiveUsersCount() {
        return users.size();
    }

    private void sendActiveUsersReport(Entity user, int activeUsersCount) {
        System.out.println("Sending active users report to " + user.getUsername() + ": " + activeUsersCount);
    }

    private void reportCountsToUsers() {
        String requestsReport = generateReport(requestsCount, "Solicitações Realizadas");
        String authorizationsReport = generateReport(authorizationsCount, "Autorizações Concedidas");

        for (Entity user : users) {
            sendReportToUser(user, requestsReport + "\n" + authorizationsReport);
        }
    }

    private String generateReport(Map<Entity, Integer> countMap, String title) {
        StringBuilder report = new StringBuilder();
        report.append("=== ").append(title).append(" ===\n");
        for (Map.Entry<Entity, Integer> entry : countMap.entrySet()) {
            Entity user = entry.getKey();
            int count = entry.getValue();
            report.append(user.getUsername()).append(": ").append(count).append("\n");
        }
        return report.toString();
    }

    private void sendReportToUser(Entity user, String report) {
        // Implement logic to send report to the user (e.g., display on UI, send message, etc.)
        // Example: user.receiveReport(report);
        System.out.println("Sending report to " + user.getUsername() + ":\n" + report);
    }

}

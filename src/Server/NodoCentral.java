package Server;

import Enums.Rank;
import Enums.RequestType;
import Model.Entity;
import Model.Request;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A classe NodoCentral é responsável por gerir os utilizadores do sistema
 */
public class NodoCentral {
    private List<Entity> users;


    /**
     * Construtor da classe NodoCentral
     */
    public NodoCentral() {
        users = new ArrayList<>();
        scheduleReportingTask();
    }

    /**
     * Agenda uma tarefa para reportar o número de utilizadores ativos no sistema
     */
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

    /**
     * Envia um report dos utilizadores ativos para o rank mais alto
     */
    private void reportActiveUsersToHighestRank() {
        Rank highestRank = getHighestRank();

        for (Entity user : users) {
            if (user.getRank() == highestRank && user.isTenente()) {
                sendActiveUsersReport(user, calculateActiveUsersCount());
            }
        }
    }

    /**
     * Método get para o rank mais alto
      * @return Rank
     */
    private Rank getHighestRank() {
        Rank highestRank = Rank.Soldado;
        for (Entity user : users) {
            if (user.getRank().ordinal() > highestRank.ordinal()) {
                highestRank = user.getRank();
            }
        }
        return highestRank;
    }

    /**
     * Calcula o número de utilizadores ativos no sistema
     *
     * @return
     */
    private int calculateActiveUsersCount() {
        return users.size();
    }

    /**
     * Envia uma notificação para todos os utilizadores do sistema
     *
     * @param user
     * @param activeUsersCount
     */
    private void sendActiveUsersReport(Entity user, int activeUsersCount) {
        System.out.println("Sending active users report to " + user.getUsername() + ": " + activeUsersCount);
    }

}

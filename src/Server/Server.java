package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static final int PORT = 5000;
    private static NotificationManager notificationManager = new NotificationManager();

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                Worker worker = new Worker(clientSocket);
                worker.start();

                // Enviar uma notificação para todos os clientes quando um novo cliente se conecta
                //notificationManager.sendBroadcastNotification("Novo cliente conectado", );
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }
}

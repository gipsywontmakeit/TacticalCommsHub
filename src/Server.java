package projetosd;

/**
 *
 * @author vitinha
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
    private Set<Entity> connectedUsers;
    private ServerSocket serverSocket;

    public Server() {
        connectedUsers = new CopyOnWriteArraySet<>();
        try {
            serverSocket = new ServerSocket(5555);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                EntityHandler clientHandler = new EntityHandler(clientSocket, connectedUsers, new ArrayList<>(), new ArrayList<>());
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}

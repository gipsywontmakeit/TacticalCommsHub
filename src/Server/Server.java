package Server;

import Model.SynchronizedArrayList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private static SynchronizedArrayList<Worker> workers = new SynchronizedArrayList<>(new ArrayList<>());
    private static ServerSocket serverSocket;

    private static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);
        } catch (IOException e) {
            System.out.println("Could not listen on port " + PORT);
            System.exit(-1);
        }

    }
}

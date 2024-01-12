package Server;

import Model.SynchronizedArrayList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    boolean listening = true;
    private ServerSocket serverSocket = null;
    private SynchronizedArrayList<Worker> entities;
    private NodoCentral nodoCentral;

    public ServerThread(boolean listening, ServerSocket serverSocket, SynchronizedArrayList<Worker> entities, NodoCentral nodoCentral) {
        this.listening = listening;
        this.serverSocket = serverSocket;
        this.entities = entities;
        this.nodoCentral = nodoCentral;
    }

    @Override
    public void run() {
        this.nodoCentral = new NodoCentral();
        while (listening) {
            Worker entitie = null;
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                entitie = new Worker(socket);
                entities.add(entitie);
                entitie.start();
                System.out.println("Cliente conectado:  " + socket.getInetAddress().getHostAddress() + " " + socket.getPort());

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

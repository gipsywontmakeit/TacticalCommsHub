package Server;

import Model.SynchronizedArrayList;

import java.net.DatagramSocket;

public class BroadcastSender extends Thread {

    private final String BROADCAST_ADRESS = "230.0.0.1";
    private final int PORT = 3000;

    private boolean listening = true;
    private DatagramSocket broadcastSocket;
    private SynchronizedArrayList<Worker> list;


}

package Server;

import Model.SynchronizedArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BroadcastSender extends Thread {

    private final String BROADCAST_ADDRESS = "230.0.0.1"; // Endereço de broadcast multicast
    private final int PORT = 3000;

    private boolean listening = true;
    private SynchronizedArrayList<Worker> workerList;

    public BroadcastSender(SynchronizedArrayList<Worker> workerList) {
        this.workerList = workerList;
    }

    @Override
    public void run() {
        System.out.println("BroadcastSender running");
        while (listening) {
            if (!workerList.isEmpty()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    String serverMsg = "Server is running";
                    sendBroadcast(serverMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendBroadcast(String message) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            socket.setBroadcast(true);
            InetAddress broadcastAddress = InetAddress.getByName(BROADCAST_ADDRESS);
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcastAddress, PORT);

            for (Worker worker : workerList.get()) {
                packet.setAddress(worker.getSocket().getInetAddress());
                packet.setPort(worker.getSocket().getPort());
                socket.send(packet);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

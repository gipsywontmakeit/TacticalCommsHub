package Server;

import java.io.IOException;
import java.net.*;

public class MulticastSender extends Thread {
    private DatagramSocket datagramSocket;
    private String message;
    private String groupIP;

    public MulticastSender(String message, String groupIP) {
        try {
            this.datagramSocket = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.message = message;
        this.groupIP = groupIP;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        byte[] buf = message.getBytes();
        InetAddress group = null;
        try {
            group = InetAddress.getByName(groupIP);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
        try {
            this.datagramSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.datagramSocket.close();
    }
}

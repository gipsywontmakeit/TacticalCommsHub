package Server;

import Model.SynchronizedArrayList;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker extends Thread {
    private int id;

    @Override
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Socket socket = null;
    protected PrintWriter out = null;
    protected BufferedReader in = null;
    private SynchronizedArrayList<Worker> list;

}

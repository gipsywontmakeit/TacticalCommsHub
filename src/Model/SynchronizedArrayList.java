package Model;

import java.util.ArrayList;

public class SynchronizedArrayList<T> {

    private ArrayList<T> list;

    public SynchronizedArrayList(ArrayList<T> list) {
        this.list = new ArrayList<>();
    }

    public synchronized ArrayList<T> get() {
        return this.list;
    }

    public synchronized void add(T object) {
        this.list.add(object);
    }

    public synchronized boolean remove (T object) {
        return this.list.remove(object);
    }

    public synchronized int getSize() {
        return this.list.size();
    }

    public synchronized T getIndex(int index) {
        return this.list.get(index);

    }

    public synchronized boolean isEmpty() {
        return this.list.isEmpty();
    }
}

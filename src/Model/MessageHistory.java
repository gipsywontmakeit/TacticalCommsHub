package Model;

import java.util.ArrayList;

public class MessageHistory {
    private ArrayList<Message> history;

    public MessageHistory() {
        this.history = new ArrayList<>();
    }

    public ArrayList<Message> getHistory() {
        return this.history;
    }

    public int size() {
        return this.history.size();
    }


}

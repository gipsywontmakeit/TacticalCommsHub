package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ChatPage extends JFrame {

    private JComboBox<String> userComboBox;
    private JTextArea messageArea;
    private JTextField inputMessage;
    private JButton sendButton;
    private JButton backButton;

    private String currentUser;

    private static final String USERS_FILE = "UserData.txt";
    private static final String MESSAGES_FILE = "Messages.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ChatPage("Utilizador").setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public ChatPage(String currentUser) throws IOException {
        this.currentUser = currentUser;
        initializeComponents();
        defineListeners();
        defineLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Chat - " + currentUser);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        this.userComboBox = new JComboBox<>(loadUsers());
        this.messageArea = new JTextArea();
        this.inputMessage = new JTextField();
        this.sendButton = new JButton("Enviar");
        this.backButton = new JButton("Voltar");
    }

    private void defineListeners() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToTacticalCommsHub();
            }
        });
    }

    private void defineLayout() {
        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 4));
        topPanel.add(new JLabel("Para:"));
        topPanel.add(userComboBox);
        topPanel.add(sendButton);
        topPanel.add(backButton);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        chatPanel.add(topPanel, BorderLayout.NORTH);
        chatPanel.add(scrollPane, BorderLayout.CENTER);
        chatPanel.add(inputMessage, BorderLayout.SOUTH);

        add(chatPanel);
    }

    private void sendMessage() {
        String selectedUser = (String) userComboBox.getSelectedItem();
        String messageText = inputMessage.getText().replaceAll("\\s+", "").trim();
    
        if (!selectedUser.isEmpty() && !messageText.isEmpty()) {
            displayMessage(currentUser + ": " + messageText + "\n");
            inputMessage.setText("");
    
            saveMessageToFile(currentUser, selectedUser, messageText);
        }
    }

    private void saveMessageToFile(String sender, String receiver, String messageText) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MESSAGES_FILE, true))) {
            writer.println("Sender: " + sender);
            writer.println("Receiver: " + receiver);
            writer.println("Message: " + messageText);
            writer.println("-----------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMessage(String message) {
        messageArea.append(message);
    }

    private void backToTacticalCommsHub() {
        // Implemente a lógica para voltar para TacticalCommsHub
        dispose(); // Fecha a janela atual
    }

    private String[] loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            return reader.lines().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}

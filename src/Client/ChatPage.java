package Client;

import Model.Entity;
import Model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChatPage extends JFrame {

    private JComboBox<String> userComboBox;
    private JTextArea messageArea;
    private JTextField inputMessage;
    private JButton sendButton;
    private JButton backButton;

    private TacticalCommsHub tacticalCommsHub;

    private Entity actualUser;

    private static final String USERS_FILE = "UserData.txt";
    private static final String MESSAGES_FILE = "Messages.txt";
    private static final int LARGURA_JANELA = 400;
    private static final int ALTURA_JANELA = 400;
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Entity user = new Entity();
            TacticalCommsHub tacticalCommsHub = new TacticalCommsHub(user);
            ChatPage chatPage = new ChatPage(user, tacticalCommsHub);
        });
    }

    public ChatPage(Entity actualUser, TacticalCommsHub tacticalCommsHub) {
        this.actualUser = actualUser;
        this.tacticalCommsHub = tacticalCommsHub;
        initializeComponents();
        defineListeners();
        defineLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Chat - " + actualUser.getUsername());
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
                backToTacticalCommsHub(tacticalCommsHub);
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
        String messageText = messageArea.getText().trim();
        if (!selectedUser.isEmpty() && !messageText.isEmpty()) {
            displayMessage("Mensagem enviada com sucesso ao " + selectedUser + "!\n");
            inputMessage.setText("");
            saveMessageToFile(actualUser.getUsername(), selectedUser, messageText);
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
        JOptionPane.showMessageDialog(this, message);
    }

    private void backToTacticalCommsHub(TacticalCommsHub tacticalCommsHub) {
        
        if (tacticalCommsHub != null) {
            tacticalCommsHub.setLoggedUser(actualUser); 
            tacticalCommsHub.setVisible(true);
            dispose(); 
        } else {
          
            try {
                new TacticalCommsHub(actualUser).setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String[] loadUsers() {
        List<String> usersList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("-----------")) {
                    
                    String nextLine = reader.readLine();
                    if (nextLine != null) {
                        usersList.add(nextLine);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        return usersList.toArray(new String[0]);
    }
}

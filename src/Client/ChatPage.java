package Client;

<<<<<<< HEAD
=======
import Model.Message;

>>>>>>> master
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatPage extends JFrame {

    private JComboBox<String> userComboBox;
    private JTextArea messageArea;
    private JTextField inputMessage;
    private JButton sendButton;
    private JButton backButton;

    private String currentUser;

<<<<<<< HEAD
    private static final String USERS_FILE = "UserData.txt";
    private static final String MESSAGES_FILE = "Messages.txt";
=======
    private static final int LARGURA_JANELA = 400;
    private static final int ALTURA_JANELA = 400;
    private static final String MESSAGES_FILE = "Messages.txt"; // Arquivo para armazenar mensagens
>>>>>>> master

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
<<<<<<< HEAD
        initializeComponents();
        defineListeners();
        defineLayout();
=======
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
>>>>>>> master
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

<<<<<<< HEAD
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
        try {
            new TacticalCommsHub().setVisible(true); // Abre a TacticalCommsHub
        } catch (Exception ex) {
=======
        if (!receiver.isEmpty() && !messageText.isEmpty()) {
            // Criar a mensagem
            //Message message = new Message(messageHistory.size() + 1, currentUser, messageText, false);

            // Adicionar mensagem ao histórico e exibir na área de mensagens
            //messageHistory.getHistory().add(message);
            //exibirMensagem(message);

            // Limpar o campo de entrada de mensagem
            inputMessage.setText("");

            // Salvar a mensagem no arquivo
            //salvarMensagemNoArquivo(message);
        }
    }

    private void exibirMensagem(Message message) {
       // messageArea.append(message.getUsername() + ": " + message.getMessage() + "\n");
    }

    private void carregarHistoricoMensagens() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MESSAGES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 4) {
                    String username = parts[1].trim();
                    String messageText = parts[2].trim();
                    boolean isNotified = Boolean.parseBoolean(parts[3].trim());

                    //Message message = new Message(messageHistory.size() + 1, username, messageText, isNotified);
                    //messageHistory.getHistory().add(message);
                    //exibirMensagem(message);
                }
            }
        } catch (IOException ex) {
>>>>>>> master
            ex.printStackTrace();
        }
    }

<<<<<<< HEAD
    private String[] loadUsers() {
        List<String> usersList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("-----------")) {
                    // Lê a próxima linha após "-----------" e adiciona à lista
                    String nextLine = reader.readLine();
                    if (nextLine != null) {
                        usersList.add(nextLine);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converte a lista para um array
        return usersList.toArray(new String[0]);
    }
=======
//    private void salvarMensagemNoArquivo(Message message) {
//        try (PrintWriter writer = new PrintWriter(MESSAGES_FILE)) {
//            //for (Message msg : messageHistory.getHistory()) {
//               // writer.println("ID: " + msg.getId());
//                //writer.println("Username: " + msg.getUsername());
//                //writer.println("Message: " + msg.getMessage());
//                writer.println("IsNotified: " + msg.getIsNotified());
//                writer.println("-----------");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
>>>>>>> master
}

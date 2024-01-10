package Client;

import Model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;

public class ChatPage extends JFrame {

    private JComboBox<String> userComboBox;
    private JTextArea messageArea;
    private JTextField inputMessage;
    private JButton sendButton;

    private String currentUser;
    private String selectedUser;

    private static final int LARGURA_JANELA = 400;
    private static final int ALTURA_JANELA = 400;
    private static final String USERS_FILE = "UserData.txt"; // Arquivo para armazenar usuários
    private static final String MESSAGES_FILE = "Messages.txt"; // Arquivo para armazenar mensagens

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
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setTitle("Chat - " + currentUser);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        this.userComboBox = new JComboBox<>(carregarUsuarios());
        this.messageArea = new JTextArea();
        this.inputMessage = new JTextField();
        this.sendButton = new JButton("Enviar");
    }

    private void definirOuvintes() {
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensagem();
            }
        });
    }

    private void definirLayout() {
        JPanel painelChat = new JPanel();
        painelChat.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topPanel.add(new JLabel("Para:"));
        topPanel.add(userComboBox);
        topPanel.add(sendButton);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        painelChat.add(topPanel, BorderLayout.NORTH);
        painelChat.add(scrollPane, BorderLayout.CENTER);
        painelChat.add(inputMessage, BorderLayout.SOUTH);

        add(painelChat);
    }

    private void enviarMensagem() {
        selectedUser = (String) userComboBox.getSelectedItem();
        String messageText = inputMessage.getText();
    
        if (!selectedUser.isEmpty() && !messageText.isEmpty()) {
            // Exibir a mensagem na área de mensagens
            exibirMensagem(currentUser + ": " + messageText + "\n");
    
            // Limpar o campo de entrada de mensagem
            inputMessage.setText("");
    
            // Salvar a mensagem no arquivo
            salvarMensagemNoArquivo(currentUser, selectedUser, messageText);
            System.out.println("Mensagem enviada e salva no arquivo.");
        }
    }

    private void salvarMensagemNoArquivo(String sender, String receiver, String messageText) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MESSAGES_FILE, true))) {
            writer.println("Sender: " + sender);
            writer.println("Receiver: " + receiver);
            writer.println("Message: " + messageText);
            writer.println("-----------");
            System.out.println("Mensagem enviada e salva no arquivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
       

    private void exibirMensagem(String message) {
        messageArea.append(message);
    }

    private String[] carregarUsuarios() {
        ArrayList<String> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userList.add(line.trim());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return userList.toArray(new String[0]);
    }
}

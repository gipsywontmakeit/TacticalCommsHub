package Client;

import Model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ChatPage extends JFrame {

    private JTextField inputReceiver;
    private JTextArea messageArea;
    private JTextField inputMessage;
    private JButton sendButton;

    private String currentUser;
    private String receiver;

    private static final int LARGURA_JANELA = 400;
    private static final int ALTURA_JANELA = 400;
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
        this.inputReceiver = new JTextField();
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
        topPanel.add(inputReceiver);
        topPanel.add(sendButton);

        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        painelChat.add(topPanel, BorderLayout.NORTH);
        painelChat.add(scrollPane, BorderLayout.CENTER);
        painelChat.add(inputMessage, BorderLayout.SOUTH);

        add(painelChat);

        // Carregar histórico de mensagens ao iniciar
        carregarHistoricoMensagens();
    }

    private void enviarMensagem() {
        String receiver = inputReceiver.getText();
        String messageText = inputMessage.getText();

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
            ex.printStackTrace();
        }
    }

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
}

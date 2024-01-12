package Client;

import Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MensagensRecebidas extends JFrame {

    private JList<String> mensagensList;
    private JButton apagarMensagemButton;
    private JButton voltarButton;
    private Entity actualUser;

    private static final String USERS_FILE = "UserData.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Entity user = new Entity();
                MensagensRecebidas mensagensRecebidas = new MensagensRecebidas(user);
                mensagensRecebidas.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public MensagensRecebidas(Entity actualUser) throws IOException {
        this.actualUser = actualUser;
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Mensagens Recebidas - " + actualUser.getUsername());
        setLocationRelativeTo(null);

        carregarMensagensRecebidas();
    }

    private void inicializarComponentes() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        mensagensList = new JList<>(listModel);
        apagarMensagemButton = new JButton("Apagar Mensagem");
        voltarButton = new JButton("Voltar");
    }

    private void definirOuvintes() {
        apagarMensagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apagarMensagem();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTacticalCommsHub();
            }
        });

    }

    private void definirLayout() {
        JPanel painelMensagens = new JPanel();
        painelMensagens.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(mensagensList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel botoesPanel = new JPanel(new GridLayout(1, 2));
        botoesPanel.add(apagarMensagemButton);
        botoesPanel.add(voltarButton);

        painelMensagens.add(scrollPane, BorderLayout.CENTER);
        painelMensagens.add(botoesPanel, BorderLayout.SOUTH);

        add(painelMensagens);
    }

    private void carregarMensagensRecebidas() throws IOException {
        String currentUser = actualUser.getUsername();
        carregarMensagensDoArquivo(currentUser);
    }

    private void carregarMensagensDoArquivo(String receiver) throws IOException {
        DefaultListModel<String> listModel = (DefaultListModel<String>) mensagensList.getModel();
        listModel.clear(); 
    
        String fileName = "Messages.txt"; 
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String sender = null;
            String message = null;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Sender: ")) {
                    sender = line.substring("Sender: ".length());
                }
                else if (line.startsWith("Receiver: ")) {
                    String currentReceiver = line.substring("Receiver: ".length());
                    if (currentReceiver.equals("Utilizador: " + receiver)) {
                        if (sender != null && message != null && !sender.equals(receiver)) {
                            listModel.addElement("Sender: " + sender);
                            listModel.addElement("Message: " + message);
                            listModel.addElement("-----------");
                        }
                    }
                }
                else if (line.startsWith("Message: ")) {
                    message = line.substring("Message: ".length());
                }
            }
    
            if (sender != null && message != null && !sender.equals(receiver)) {
                listModel.addElement("Sender: " + sender);
                listModel.addElement("Message: " + message);
                listModel.addElement("-----------");
            }
        }
    }
    
    
    

    private void apagarMensagem() {
        if (!mensagensList.isSelectionEmpty()) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) mensagensList.getModel();
            int selectedIndex = mensagensList.getSelectedIndex();

            if (selectedIndex != -1) {
                String mensagemSelecionada = listModel.getElementAt(selectedIndex);

                listModel.remove(selectedIndex);

                try {
                    String fileName = "Messages.txt";
                    List<String> linhas = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

                    linhas.remove(mensagemSelecionada);

                    Files.write(Paths.get(fileName), linhas, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void voltarParaTacticalCommsHub() {
        dispose(); 
        try {
            new TacticalCommsHub(actualUser).setVisible(true); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    

}


    


package Client;

import Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MensagensRecebidas extends JFrame {

    private JList<String> mensagensList;
    private JButton apagarMensagemButton;
    private JButton voltarButton;
    private static Entity actualUser;

    private static final String USERS_FILE = "UserData.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MensagensRecebidas mensagensRecebidas = new MensagensRecebidas(actualUser);
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
        setTitle("Mensagens Recebidas");
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

        mensagensList.addListSelectionListener(e -> exibirMensagemCompleta());
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
        String currentUser = "Utilizador"; // Substitua pelo usuário atual
        carregarMensagensDoArquivo(currentUser);
    }

    private void carregarMensagensDoArquivo(String receiver) throws IOException {
        DefaultListModel<String> listModel = (DefaultListModel<String>) mensagensList.getModel();
        listModel.clear(); // Limpa a lista antes de recarregar as mensagens

        // Carrega as mensagens do arquivo de mensagens
        String fileName = "Messages.txt"; // Nome do arquivo de mensagens

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Receiver: " + receiver)) {
                    // Adiciona a mensagem ao modelo da lista
                    listModel.addElement(line);
                }
            }
        }
    }

    private void apagarMensagem() {
        if (!mensagensList.isSelectionEmpty()) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) mensagensList.getModel();
            int selectedIndex = mensagensList.getSelectedIndex();
    
            if (selectedIndex != -1) {
                // Obtém a mensagem selecionada
                String mensagemSelecionada = listModel.getElementAt(selectedIndex);
    
                // Remove a mensagem do modelo da lista
                listModel.remove(selectedIndex);
    
                // Lógica para apagar a mensagem selecionada no arquivo Messages.txt
                try {
                    String fileName = "Messages.txt";
                    List<String> linhas = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
    
                    // Remove a linha correspondente à mensagem selecionada
                    linhas.remove(mensagemSelecionada);
    
                    // Atualiza o arquivo com as linhas restantes
                    Files.write(Paths.get(fileName), linhas, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                    // Lidar com exceções de IO, se necessário
                }
            }
        }
    }
    

    private void voltarParaTacticalCommsHub() {
        dispose(); // Fecha a janela atual
        try {
            new TacticalCommsHub(actualUser).setVisible(true); // Abre a TacticalCommsHub
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void exibirMensagemCompleta() {
        if (!mensagensList.isSelectionEmpty()) {
            String selectedMessage = mensagensList.getSelectedValue();
            JOptionPane.showMessageDialog(this, selectedMessage, "Mensagem Completa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

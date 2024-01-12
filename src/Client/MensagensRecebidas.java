package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MensagensRecebidas extends JFrame {

    private JTextArea mensagensTextArea;
    private JButton verMensagemButton;
    private JButton apagarMensagemButton;
    private JButton voltarButton;

    private static final String USERS_FILE = "UserData.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MensagensRecebidas mensagensRecebidas = new MensagensRecebidas();
                mensagensRecebidas.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public MensagensRecebidas() throws IOException {
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
        mensagensTextArea = new JTextArea();
        mensagensTextArea.setEditable(false);

        verMensagemButton = new JButton("Ver Mensagem");
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

//        voltarButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                voltarParaTacticalCommsHub();
//            }
//        });
    }

    private void definirLayout() {
        JPanel painelMensagens = new JPanel();
        painelMensagens.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(mensagensTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel botoesPanel = new JPanel(new GridLayout(1, 3));
        botoesPanel.add(verMensagemButton);
        botoesPanel.add(apagarMensagemButton);
        botoesPanel.add(voltarButton);

        painelMensagens.add(scrollPane, BorderLayout.CENTER);
        painelMensagens.add(botoesPanel, BorderLayout.SOUTH);

        add(painelMensagens);
    }

    private void carregarMensagensRecebidas() throws IOException {
        String currentUser = "Utilizador"; // Substitua pelo usuário atual
        mensagensTextArea.setText(carregarMensagensDoArquivo(currentUser));
    }

    private String carregarMensagensDoArquivo(String receiver) throws IOException {
        // Carrega as mensagens do arquivo de mensagens
        String fileName = "Messages.txt"; // Nome do arquivo de mensagens
        StringBuilder mensagens = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Receiver: " + receiver)) {
                    // Adiciona a mensagem ao StringBuilder
                    mensagens.append(line).append("\n");
                }
            }
        }

        return mensagens.toString();
    }

    private void apagarMensagem() {
        // Lógica para apagar a mensagem selecionada
        // Pode envolver a remoção da mensagem do arquivo
        // ou a marcação da mensagem como "lida", dependendo dos requisitos
    }

//    private void voltarParaTacticalCommsHub() {
//        dispose(); // Fecha a janela atual
//        try {
//            new TacticalCommsHub().setVisible(true); // Abre a TacticalCommsHub
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}

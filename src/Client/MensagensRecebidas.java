package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MensagensRecebidas extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                List<String> mensagens = new ArrayList<>();
                mensagens.add("Remetente 1: Conteúdo da Mensagem 1");
                mensagens.add("Remetente 2: Conteúdo da Mensagem 2");
                new MensagensRecebidas(mensagens).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private List<String> mensagens;

    public MensagensRecebidas(List<String> mensagens) {
        this.mensagens = mensagens;
        inicializarComponentes();
        definirLayout();
        definirOuvintes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Mensagens Recebidas");
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JLabel tituloLabel = new JLabel("Mensagens Recebidas");

        // Use um componente JTextArea para exibir várias mensagens
        JTextArea mensagensTextArea = new JTextArea();
        mensagensTextArea.setEditable(false);
        mensagensTextArea.setLineWrap(true);
        mensagensTextArea.setWrapStyleWord(true);

        // Adicione as mensagens ao componente JTextArea
        for (String mensagem : mensagens) {
            mensagensTextArea.append(mensagem + "\n\n");
        }

        JButton voltarButton = new JButton("Voltar");

        add(tituloLabel);
        add(new JScrollPane(mensagensTextArea));  // Usamos um JScrollPane para permitir rolar as mensagens se necessário
        add(voltarButton);
    }

    private void definirLayout() {
        setLayout(new GridLayout(3, 1));
    }

    private void definirOuvintes() {
        JButton voltarButton = (JButton) getContentPane().getComponent(2); // Índice do botão "Voltar"

<<<<<<< HEAD
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTacticalCommsHub();
            }
        });
    }

=======
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
    

    private void verMensagem() {
        // Lógica para exibir a mensagem selecionada
        // Você pode abrir uma nova janela, caixa de diálogo, etc.
        // e exibir o conteúdo da mensagem selecionada
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
>>>>>>> 2b58f1a391958d4ef85f3af1d17fd877cdde01aa
    private void voltarParaTacticalCommsHub() {
        // Lógica para voltar à interface "TacticalCommsHub" ou realizar outras ações necessárias
        dispose(); // Fecha a janela atual
        try {
            new TacticalCommsHub().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

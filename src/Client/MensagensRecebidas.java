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

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTacticalCommsHub();
            }
        });
    }

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

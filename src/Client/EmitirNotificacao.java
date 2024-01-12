package Client;

import Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EmitirNotificacao extends JFrame {

    private JComboBox<String> destinatarioComboBox;
    private JTextArea mensagemArea;

    private static Entity actualUser;
    public EmitirNotificacao(Entity actualUser) {
        this.actualUser = actualUser;
        inicializarComponentes();
        definirLayout();
        definirOuvintes();
        carregarDestinatarios();  // Carregar os destinatários ao iniciar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Emitir Notificação");
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {

        JLabel destinatarioLabel = new JLabel("Destinatário:");
        destinatarioComboBox = new JComboBox<>();

        JLabel mensagemLabel = new JLabel("Mensagem:");
        mensagemArea = new JTextArea();

        JButton enviarButton = new JButton("Enviar");
        JButton voltarButton = new JButton("Voltar");

        add(destinatarioLabel);
        add(destinatarioComboBox);
        add(mensagemLabel);
        add(new JScrollPane(mensagemArea));
        add(enviarButton);
        add(voltarButton);
    }

    private void definirLayout() {
        setLayout(new GridLayout(3, 2));
    }

    private void definirOuvintes() {
        JButton enviarButton = (JButton) getContentPane().getComponent(4);
        JButton voltarButton = (JButton) getContentPane().getComponent(5);

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarNotificacao();
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTacticalCommsHub();
            }
        });
    }

    private void carregarDestinatarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CanaisComunicacao.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("-----------")) {
                    // Lê a próxima linha após "-----------", que contém o canal de comunicação
                    String canal = reader.readLine();
                    destinatarioComboBox.addItem(canal);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarNotificacao() {
        String canal = (String) destinatarioComboBox.getSelectedItem();
        String mensagem = mensagemArea.getText();
    
        if (canal.isEmpty() || mensagem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }
    
        // Lógica para salvar a notificação no arquivo Notificacoes.txt
        salvarNotificacao(canal, mensagem);
    
        // Feedback para o utilizador
        JOptionPane.showMessageDialog(this, "Notificação enviada com sucesso!");
    
        // Limpar campos
        mensagemArea.setText("");
    }
    
    private void salvarNotificacao(String canal, String mensagem) {
        try (FileWriter writer = new FileWriter("Notificacoes.txt", true)) {
            writer.write("Canal: " + canal);
            writer.write(System.lineSeparator());
            writer.write("Mensagem: " + mensagem);
            writer.write(System.lineSeparator());
            writer.write("-----------");
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Entity actualUser = new Entity();
            EmitirNotificacao emitirNotificacao = new EmitirNotificacao(actualUser);
            emitirNotificacao.setVisible(true);
        });
    }
    
}


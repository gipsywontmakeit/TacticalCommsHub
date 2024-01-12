package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CriarCanal extends JFrame {

    private JTextField novoCanalTextField;
    private JButton criarCanalButton;
    private JButton voltarButton;

    private static final int LARGURA_JANELA = 400;
    private static final int ALTURA_JANELA = 300;
    private static final String CANAIS_FILE = "CanaisComunicacao.txt"; // Arquivo para armazenar os canais

    public CriarCanal() {
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setTitle("Criar Canal");
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        novoCanalTextField = new JTextField();
        criarCanalButton = new JButton("Criar Canal");
        voltarButton = new JButton("Voltar");
    }

    private void definirOuvintes() {
        criarCanalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarNovoCanal();
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
        setLayout(new BorderLayout());

        JPanel painelCanal = new JPanel();
        painelCanal.setLayout(new GridLayout(3, 1));
        painelCanal.add(new JLabel("Novo Canal:"));
        painelCanal.add(novoCanalTextField);
        painelCanal.add(criarCanalButton);

        add(painelCanal, BorderLayout.CENTER);
        add(voltarButton, BorderLayout.SOUTH);
    }

    private void criarNovoCanal() {
        String novoCanal = novoCanalTextField.getText().trim();

        if (!novoCanal.isEmpty()) {
            // Salvar o novo canal no arquivo
            salvarCanalNoArquivo(novoCanal);

            // Limpar o campo de texto
            novoCanalTextField.setText("");
        }
    }

    private void salvarCanalNoArquivo(String novoCanal) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CANAIS_FILE, true))) {
            writer.println(novoCanal);
            System.out.println("Novo canal criado e salvo no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

//    private void voltarParaTacticalCommsHub() {
//        // Lógica para voltar para a página TacticalCommsHub
//        dispose(); // Fecha a janela atual
//
//        // Abre a página TacticalCommsHub ou realiza outras ações necessárias
//        try {
//            new TacticalCommsHub().setVisible(true);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CriarCanal().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

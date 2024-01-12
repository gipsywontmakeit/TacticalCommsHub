package Client;

import Model.Entity;
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
    private static final String CANAIS_FILE = "CanaisComunicacao.txt"; 
    private static Entity actualUser;

    public CriarCanal(Entity actualUser) {
        this.actualUser = actualUser;
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

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltar();
            }
        });
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
            salvarCanalNoArquivo(novoCanal);

            novoCanalTextField.setText("");
        }
    }

    private void salvarCanalNoArquivo(String novoCanal) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CANAIS_FILE, true))) {
            writer.println(novoCanal);
            writer.println("-----------");
            
            System.out.println("Novo canal criado e guardado no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    private void voltar() {
    try {
        TacticalCommsHub tacticalCommsHub = new TacticalCommsHub(actualUser);
        tacticalCommsHub.setVisible(true);
        dispose(); 
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new CriarCanal(actualUser).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

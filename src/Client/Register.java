package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Register extends JFrame {

    private JButton registar, entrar;
    private JTextField inputNome, inputPass, inputUtilizador;
    private JComboBox<String> comboBoxOpcoes;

    private static final int LARGURA_JANELA = 600;
    private static final int ALTURA_JANELA = 550;
    private static final String CAMINHO_FICHEIRO_UTILIZADORES = "UserData.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Register().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Register() throws IOException {
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        inicializarDadosUtilizador();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setTitle("Registo de Conta");
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                guardarDadosUtilizadorEmFicheiro();
                System.exit(0);
            }
        });
    }

    private void inicializarComponentes() {
        this.registar = new JButton("Submeter registo!");
        this.entrar = new JButton("Efetuar Login");
        this.inputNome = new JTextField();
        this.inputPass = new JTextField();
        this.inputUtilizador = new JTextField();

        // Adiciona uma caixa de seleção com três opções
        String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"};
        this.comboBoxOpcoes = new JComboBox<>(opcoes);
    }

    private void definirOuvintes() {
        entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login paginaEntrada = new Login();
                paginaEntrada.setVisible(true);
                Register.this.dispose(); // Fecha a janela de registo
            }
        });

        registar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarRegisto();
            }
        });
    }

    private void definirLayout() {
        JPanel painelRegisto = new JPanel();
        painelRegisto.setLayout(new GridLayout(10, 2));
        painelRegisto.add(new JLabel("Faça o seu registo"));
        painelRegisto.add(new JLabel());
        painelRegisto.add(new JLabel("Utilizador"));
        painelRegisto.add(inputUtilizador);
        painelRegisto.add(new JLabel("Nome"));
        painelRegisto.add(inputNome);
        painelRegisto.add(new JLabel("Senha"));
        painelRegisto.add(inputPass);
        painelRegisto.add(new JLabel("Selecione uma opção"));
        painelRegisto.add(comboBoxOpcoes);
        painelRegisto.add(registar);
        painelRegisto.add(new JLabel());
        painelRegisto.add(new JLabel("Já possui conta?"));
        painelRegisto.add(entrar);

        add(painelRegisto);
    }

    private void inicializarDadosUtilizador() {
        File ficheiro = new File(CAMINHO_FICHEIRO_UTILIZADORES);
        if (ficheiro.exists()) {
            // Carregar dados do ficheiro .txt
        }
    }

     // Guardar dados no ficheiro
     // Encriptar as senhas antes de guardar
    private void guardarDadosUtilizadorEmFicheiro() {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(CAMINHO_FICHEIRO_UTILIZADORES, true))) {
            escritor.println("Utilizador:" + inputUtilizador.getText());
            escritor.println("Nome:" + inputNome.getText());
            escritor.println("Senha:" + inputPass.getText());
            escritor.println("Opcao:" + comboBoxOpcoes.getSelectedItem());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processarRegisto() {
        if (inputUtilizador.getText().isEmpty() || inputNome.getText().isEmpty() || inputPass.getText().isEmpty()) {
            mostrarMensagem(1);
        } else {
            if (verificarUtilizadorExistente(inputUtilizador.getText())) {
                mostrarMensagem(2);
            } else {
                mostrarMensagem(3);
                inputPass.setText("");
                inputNome.setText("");
                inputUtilizador.setText("");
                guardarDadosUtilizadorEmFicheiro();
            }
        }
    }

     // Adicionar lógica para verificar se o utilizador já existe no ficheiro
     // Retorna true se existir, false se não existir
    private boolean verificarUtilizadorExistente(String utilizador) {
        return false; 
    }
    

    public void mostrarMensagem(int op) {
        JOptionPane.showMessageDialog(this, obterMensagem(op), obterTituloMensagem(op), JOptionPane.INFORMATION_MESSAGE);
    }

    private String obterMensagem(int op) {
        switch (op) {
            case 1:
                return "Tem campos inválidos";
            case 2:
                return "Utilizador já existente";
            case 3:
                return "Conta registada com sucesso, podes efetuar login!";
            default:
                return "";
        }
    }

    private String obterTituloMensagem(int op) {
        switch (op) {
            case 1:
            case 2:
                return "Erro";
            case 3:
                return "Sucesso";
            default:
                return "";
        }
    }
}
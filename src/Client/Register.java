package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Register extends JFrame {

    private boolean isWindowClosedByUser = false;

    private JButton registar, entrar;
    private JTextField inputNome, inputPass, inputUtilizador;
    private JComboBox<String> comboBoxOpcoes;

    private static final int LARGURA_JANELA = 600;
    private static final int ALTURA_JANELA = 550;
    private static final String CAMINHO_FICHEIRO_UTILIZADORES = "UserData.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Register registerFrame = new Register();
                registerFrame.setVisible(true);
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
                isWindowClosedByUser = true;
                   fecharJanela();
            }
        });
    }

    private void fecharJanela() {
        if (isWindowClosedByUser) {
            guardarDadosUtilizadorEmFicheiro();
        }
        dispose();
    }

    private void inicializarComponentes() {
        this.registar = new JButton("Submeter registo!");
        this.entrar = new JButton("Efetuar Login");
        this.inputNome = new JTextField();
        this.inputPass = new JTextField();
        this.inputUtilizador = new JTextField();

        // Adiciona uma caixa de seleção com três opções
        String[] opcoes = {"Soldado", "Sargento", "Tenente"};
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
        String utilizador = inputUtilizador.getText();
        String nome = inputNome.getText();
        String senha = inputPass.getText();
    
        // Verifica se os ficheiros não estão vazios
        if (!utilizador.isEmpty() && !nome.isEmpty() && !senha.isEmpty()) {
            try (PrintWriter escritor = new PrintWriter(new FileWriter(CAMINHO_FICHEIRO_UTILIZADORES, true))) {
                String senhaCriptografada = criptografarSenha(senha);
                
                escritor.println("Utilizador: " + utilizador);
                escritor.println("Nome: " + nome);
                escritor.println("Senha: " + senhaCriptografada);
                escritor.println("Opcao: " + comboBoxOpcoes.getSelectedItem());
                escritor.println("-----------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private String criptografarSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            
            // Converte o hash para uma representação hexadecimal
            StringBuilder hexHash = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexHash.append('0');
                hexHash.append(hex);
            }
            
            return hexHash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            
            return null;
        }
    }


    private void processarRegisto() {
        String utilizador = inputUtilizador.getText();
        String nome = inputNome.getText();
        String senha = inputPass.getText();

        if (utilizador.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
            mostrarMensagem(1);
        } else {
            if (verificarUtilizadorExistente(utilizador)) {
                mostrarMensagem(2);
            } else {
                guardarDadosUtilizadorEmFicheiro(); // Save data during registration
                mostrarMensagem(3);

                inputPass.setText("");
                inputNome.setText("");
                inputUtilizador.setText("");

                return;
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
package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends JFrame {

    private JButton login, criarConta;
    private JTextField inputUtilizador;
    private JPasswordField inputPass; 

    private static final int LARGURA_JANELA = 400;
    private static final int ALTURA_JANELA = 250;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Login().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(LARGURA_JANELA, ALTURA_JANELA);
        setTitle("Login");
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        this.login = new JButton("Login");
        this.criarConta = new JButton("Criar Conta");
        this.inputUtilizador = new JTextField();
        this.inputPass = new JPasswordField();  // Alteração aqui
    }

    private void definirOuvintes() {
        criarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPaginaRegistro();
            }
        });
    
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicionar lógica de login conforme necessário
                String utilizadorDigitado = inputUtilizador.getText();
                char[] senhaChars = inputPass.getPassword();
                String senhaDigitada = new String(senhaChars);
        
                // Verificar se os campos estão preenchidos
                if (!utilizadorDigitado.isEmpty() && senhaChars.length > 0) {
                    // Verificar se os dados correspondem ao arquivo de usuários
                    if (verificarCredenciais(utilizadorDigitado, senhaDigitada)) {
                        JOptionPane.showMessageDialog(Login.this, "Login realizado com sucesso!");
                        // Limpar a senha após o login
                        inputPass.setText("");
        
                        // Abrir a interface TacticalCommsHub após o login bem-sucedido
                        abrirTacticalCommsHub();
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "Credenciais inválidas. Tente novamente.");
                        // Limpar a senha em caso de credenciais inválidas
                        inputPass.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Preencha todos os campos.");
                }
            }
        });
    }


    private boolean verificarCredenciais(String utilizador, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Utilizador")) {
                    String utilizadorSalvo = line.split(":")[1].trim();

                    line = reader.readLine();
                    String nome = line.split(":")[1].trim();

                    line = reader.readLine();
                    String senhaSalva = line.split(":")[1].trim();

                    line = reader.readLine();
                    String opcao = line.split(":")[1].trim();

                    String senhaDigitadaEncriptada = hashPassword(senha);

                    if (utilizador.equalsIgnoreCase(utilizadorSalvo) && senhaDigitadaEncriptada.equals(senhaSalva)) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Exception message: " + ex.getMessage());
        }
        return false;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

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

    private void definirLayout() {
        JPanel painelLogin = new JPanel();
        painelLogin.setLayout(new GridLayout(5, 2));
        painelLogin.add(new JLabel("Faça o seu login"));
        painelLogin.add(new JLabel());
        painelLogin.add(new JLabel("Utilizador"));
        painelLogin.add(inputUtilizador);
        painelLogin.add(new JLabel("Senha"));
        painelLogin.add(inputPass);
        painelLogin.add(login);
        painelLogin.add(new JLabel());
        painelLogin.add(new JLabel("Ainda não tem conta?"));
        painelLogin.add(criarConta);

        add(painelLogin);
    }

    private void abrirPaginaRegistro() {
        try {
            Register paginaRegistro = new Register();
            paginaRegistro.setVisible(true);
            dispose(); // Fecha a janela de login
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirTacticalCommsHub() {
        try {
            TacticalCommsHub tacticalCommsHub = new TacticalCommsHub();
            tacticalCommsHub.setVisible(true);
            dispose(); // Fecha a janela de login
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

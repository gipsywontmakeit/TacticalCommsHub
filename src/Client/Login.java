package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
                char[] senhaChars = inputPass.getPassword();  // Obter a senha como um array de caracteres
                String senha = new String(senhaChars);  // Converter para uma string segura
                // Aqui você pode usar a senha para autenticação

                JOptionPane.showMessageDialog(Login.this, "Login realizado com sucesso!");
                // Limpar a senha após o login
                inputPass.setText("");
            }
        });
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
}

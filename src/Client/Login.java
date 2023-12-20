package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class Login extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private static final int BROADCAST_PORT = 3000;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private JButton btnRegist, btnLogin;
    private JLabel lblTitulo, lblUsername, lblPassword;
    private JTextField username;
    private JPasswordField password;

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
        initializeComponents();
        setListeners();
        setLayout();
        initializeNetworking();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Tactical Comms Hub!");
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        btnLogin = new JButton("Login");
        btnRegist = new JButton("Criar Conta");
        lblTitulo = new JLabel("Tactical Comms Hub!");
        lblUsername = new JLabel("UserName: ");
        lblPassword = new JLabel("Password: ");
        username = new JTextField();
        password = new JPasswordField();
    }

    private void setListeners() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a lógica de login aqui
            }
        });

        btnRegist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a lógica de registro aqui
            }
        });
    }

    private void setLayout() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(lblTitulo);
        panel.add(new JLabel()); // Espaço em branco
        panel.add(lblUsername);
        panel.add(username);
        panel.add(lblPassword);
        panel.add(password);
        panel.add(btnLogin);
        panel.add(btnRegist);

        add(panel, BorderLayout.CENTER);
    }

    private void initializeNetworking() {
        try {
            this.socket = new Socket("localhost", 2048);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Register register) {
    }
}

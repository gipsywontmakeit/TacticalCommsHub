package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Register extends JFrame {

    private JLabel titleContent, textName, textPassword, textUsername, textTemConta;
    private JTextField inputName, inputPass, inputUserName;
    private JButton register, login;
    private JPanel registerPanel;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 550;

    private PrintWriter out;
    private BufferedReader in;

    private Socket socket;

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
        this.socket = new Socket("localhost", 2049);
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeComponents();
        setListeners();
        initializeLabels();
        setLayout();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Registo de Conta");
        setLocationRelativeTo(null);

        // Adiciona o evento de fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    out.println("OFF");
                    socket.close();
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initializeComponents() {
        this.register = new JButton("Submeter registo!");
        this.login = new JButton("Efetuar Login");

        this.inputName = new JTextField();
        this.inputPass = new JTextField();
        this.inputUserName = new JTextField();
    }

    private void setListeners() {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginPage = new Login();
                try {
                    loginPage.start(Register.this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputUserName.getText().isEmpty() || inputName.getText().isEmpty() || inputPass.getText().isEmpty()) {
                    displayMSG(1);
                } else {
                    out.println("REGISTAR;" + inputUserName.getText() + ";" + inputName.getText() + ";" +
                            inputPass.getText() + ";" + ";");

                    int i = 0;
                    String inputLine = null;

                    try {
                        while ((inputLine = in.readLine()) != null) {
                            i++;
                            System.out.println(inputLine);
                            if (i == 1) {
                                break;
                            }
                        }

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    if (inputLine.equals("0")) {
                        displayMSG(2);
                    } else {
                        displayMSG(3);
                        inputPass.setText("");
                        inputName.setText("");
                        inputUserName.setText("");
                    }
                }
            }
        });
    }

    private void initializeLabels() {
        titleContent = new JLabel("Bem vindo ao Registo de Conta");
        titleContent.setForeground(new Color(255, 0, 0));
        titleContent.setFont(new Font("Arial", Font.PLAIN, 30));

        textTemConta = new JLabel("Já possui conta?");
        textTemConta.setFont(new Font("Arial", Font.PLAIN, 15));

        textName = new JLabel("Nome");
        textName.setFont(new Font("Arial", Font.PLAIN, 15));

        textPassword = new JLabel("Password");
        textPassword.setFont(new Font("Arial", Font.PLAIN, 15));

        textUsername = new JLabel("UserName");
        textUsername.setFont(new Font("Arial", Font.PLAIN, 15));

    }

    private void setLayout() {
        registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(9, 2));
        registerPanel.add(titleContent);
        registerPanel.add(new JLabel());
        registerPanel.add(textUsername);
        registerPanel.add(inputUserName);
        registerPanel.add(textName);
        registerPanel.add(inputName);
        registerPanel.add(textPassword);
        registerPanel.add(inputPass);
        registerPanel.add(register);
        registerPanel.add(new JLabel());
        registerPanel.add(textTemConta);
        registerPanel.add(login);

        add(registerPanel);
    }

    public void displayMSG(int op) {
        JOptionPane.showMessageDialog(this, getMessage(op), getDialogTitle(op), JOptionPane.INFORMATION_MESSAGE);
    }

    private String getMessage(int op) {
        switch (op) {
            case 1:
                return "Tem campos inválidos";
            case 2:
                return "Username já existente";
            case 3:
                return "Conta registada com sucesso, pode efetuar login!";
            default:
                return "";
        }
    }

    private String getDialogTitle(int op) {
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

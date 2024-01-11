package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Autorizacao extends JFrame {

    private JComboBox<String> tipoAutorizacaoComboBox;
    private JTextField outroTipoField;
    private JComboBox<String> tenentesComboBox;
    private JButton solicitarAutorizacaoButton;
    private JButton voltarButton;

    private static final String USERS_FILE = "UserData.txt";
    private static final String[] TIPOS_AUTORIZACAO = {"Lançamento de mísseis", "Acesso à área restrita", "Outra autorização específica"};
    private static final String USER_DATA = "UserData.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Autorizacao autorizacao = new Autorizacao();
                autorizacao.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Autorizacao() throws IOException {
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Solicitar Autorização");
        setLocationRelativeTo(null);

        carregarTiposAutorizacao();
        carregarTenentes();
    }

    private void inicializarComponentes() {
        this.tipoAutorizacaoComboBox = new JComboBox<>(TIPOS_AUTORIZACAO);
        this.outroTipoField = new JTextField();
        this.tenentesComboBox = new JComboBox<>();
        this.solicitarAutorizacaoButton = new JButton("Solicitar Autorização");
        this.voltarButton = new JButton("Voltar");
    }

    private void definirOuvintes() {
        tipoAutorizacaoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCampoOutroTipo();
            }
        });
    
        solicitarAutorizacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarAutorizacao();
            }
        });
    
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTacticalCommsHub();
            }
        });
    }

    private void definirLayout() {
        JPanel painelAutorizacao = new JPanel();
        painelAutorizacao.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        topPanel.add(new JLabel("Tipo de Autorização:"));
        topPanel.add(tipoAutorizacaoComboBox);
        topPanel.add(new JLabel("Outro Tipo:"));
        topPanel.add(outroTipoField);
        topPanel.add(new JLabel("Enviar para Tenente:"));
        topPanel.add(tenentesComboBox);
        topPanel.add(solicitarAutorizacaoButton);
        topPanel.add(voltarButton);

        painelAutorizacao.add(topPanel, BorderLayout.NORTH);

        add(painelAutorizacao);
    }

    private void carregarTiposAutorizacao() {
        // Carregar tipos de autorização, se necessário
    }

    private void carregarTenentes() {
        // Carregar tenentes do arquivo "Tenentes.txt"
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tenentesComboBox.addItem(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void atualizarCampoOutroTipo() {
        String tipoSelecionado = (String) tipoAutorizacaoComboBox.getSelectedItem();
        if ("Outra autorização específica".equals(tipoSelecionado)) {
            outroTipoField.setEnabled(true);
        } else {
            outroTipoField.setEnabled(false);
            outroTipoField.setText("");
        }
    }

    private void solicitarAutorizacao() {
        String tipoSelecionado = (String) tipoAutorizacaoComboBox.getSelectedItem();
        String outroTipo = outroTipoField.getText().trim();
        String tenenteSelecionado = (String) tenentesComboBox.getSelectedItem();
    
        // Lógica para solicitar autorização, verificar patente, etc.
        // Neste exemplo, apenas imprimimos as informações no console
        System.out.println("Tipo de Autorização: " + tipoSelecionado);
        if (!outroTipo.isEmpty()) {
            System.out.println("Outro Tipo: " + outroTipo);
        }
        System.out.println("Enviado para Tenente: " + tenenteSelecionado);
    
        // Lógica para salvar a solicitação em um arquivo, enviar notificação, etc.
        System.out.println("Solicitação de autorização enviada ao Tenente.");
    }
    

    private boolean verificarPatente() {
        // Lógica para verificar se o usuário é um "Tenente"
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(":");
                if (userData.length == 5) {
                    String username = userData[1].trim();
                    String rank = userData[4].trim();
                    if ("Tenente".equals(rank) && username.equals("Utilizador")) {
                        return true; // O usuário é um "Tenente"
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // O usuário não é um "Tenente"
    }

    private void voltarParaTacticalCommsHub() {
    dispose(); // Fecha a janela atual
    try {
        new TacticalCommsHub().setVisible(true); // Abre a TacticalCommsHub
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
}


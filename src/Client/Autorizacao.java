package Client;

import Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class Autorizacao extends JFrame {

    private Entity actualUser;

    private JComboBox<String> tipoAutorizacaoComboBox;
    private JTextField outroTipoField;
    private JComboBox<String> tenentesComboBox;
    private JButton solicitarAutorizacaoButton;
    private JButton voltarButton;

    private static final String USERS_FILE = "UserData.txt";
    private static final String MESSAGES_FILE = "Messages.txt";
    private static final String[] TIPOS_AUTORIZACAO = {"Lançamento de mísseis", "Acesso à área restrita", "Outra autorização específica"};

    private static final List<String> historicoSolicitacoes = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Entity user = new Entity();
            Autorizacao autorizacao = new Autorizacao(user);
            autorizacao.setVisible(true);
        });
    }

    public Autorizacao(Entity actualUser) {
        this.actualUser = actualUser;
        TacticalCommsHub tacticalCommsHub = new TacticalCommsHub(actualUser);
        tacticalCommsHub.setLoggedUser(actualUser);
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

    /**
     * Carrega os Tenentes registados no sistema.
     */
    private void carregarTenentes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("-----------")) {
                    line = reader.readLine();
                    if (line != null && line.startsWith("Utilizador:")) {
                        String username = line.substring("Utilizador:".length()).trim();
                        line = reader.readLine(); // Nome
                        line = reader.readLine(); // Senha
                        line = reader.readLine(); // Opcao
                        if (line != null && line.contains("Tenente")) {
                            tenentesComboBox.addItem(username);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Atualiza o campo "Outro Tipo" conforme o tipo de autorização selecionado.
     */
    private void atualizarCampoOutroTipo() {
        String tipoSelecionado = (String) tipoAutorizacaoComboBox.getSelectedItem();
        if ("Outra autorização específica".equals(tipoSelecionado)) {
            outroTipoField.setEnabled(true);
        } else {
            outroTipoField.setEnabled(false);
            outroTipoField.setText("");
        }
    }

    /**
     * Solicita autorização ao Tenente selecionado.
     */
    private void solicitarAutorizacao() {
        String tipoSelecionado = (String) tipoAutorizacaoComboBox.getSelectedItem();
        String outroTipo = outroTipoField.getText().trim();
        String tenenteSelecionado = (String) tenentesComboBox.getSelectedItem();

        if (tipoSelecionado.isEmpty() || (tipoSelecionado.equals(TIPOS_AUTORIZACAO[2]) && outroTipo.isEmpty()) || tenenteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String mensagemSolicitacao = "Tipo de Autorização: " + tipoSelecionado;
        if (!outroTipo.isEmpty()) {
            mensagemSolicitacao += "\nOutro Tipo: " + outroTipo;
        }
        mensagemSolicitacao += "\nEnviado para Tenente: " + tenenteSelecionado;

        int confirmacao = JOptionPane.showConfirmDialog(this, mensagemSolicitacao, "Confirmar Solicitação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Solicitação de autorização enviada ao Tenente.", "Autorização Enviada", JOptionPane.INFORMATION_MESSAGE);
            historicoSolicitacoes.add(mensagemSolicitacao);
        }
    }

    /**
     * Retorna o histórico de solicitações de autorização.
     * @return
     */
    static List<String> getHistoricoSolicitacoes() {
        return historicoSolicitacoes;
    }

    /**
     * Volta para a TacticalCommsHub.
     */
    private void voltarParaTacticalCommsHub() {
        dispose();
        try {
            TacticalCommsHub tacticalCommsHub = new TacticalCommsHub(actualUser);
            tacticalCommsHub.setLoggedUser(actualUser);
            tacticalCommsHub.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

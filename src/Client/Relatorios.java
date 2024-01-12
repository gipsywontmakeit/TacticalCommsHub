package Client;

import Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Relatorios extends JFrame {

    private JButton detalhesSolicitacoesButton;
    private JButton detalhesAutorizacoesButton;
    private JButton voltarButton;

    private static Entity actualUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Relatorios relatorios = new Relatorios(actualUser);
                relatorios.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Relatorios(Entity actualUser) throws IOException {
        this.actualUser = actualUser;
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Relatórios");
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        detalhesSolicitacoesButton = new JButton("Detalhes sobre Solicitações");
        detalhesAutorizacoesButton = new JButton("Detalhes sobre Autorizações");
        voltarButton = new JButton("Voltar");
    }

    private void definirOuvintes() {
        detalhesSolicitacoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetalhesSolicitacoes();
            }
        });

        detalhesAutorizacoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetalhesAutorizacoes();
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
        JPanel painelRelatorios = new JPanel();
        painelRelatorios.setLayout(new GridLayout(3, 1));
        painelRelatorios.add(detalhesSolicitacoesButton);
        painelRelatorios.add(detalhesAutorizacoesButton);
        painelRelatorios.add(voltarButton);

        add(painelRelatorios);
    }

    private void mostrarDetalhesSolicitacoes() {
        JOptionPane.showMessageDialog(this, "Detalhes sobre Solicitações");
    }

    private void mostrarDetalhesAutorizacoes() {
        JOptionPane.showMessageDialog(this, "Detalhes sobre Autorizações");
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
}


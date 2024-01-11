package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Relatorios extends JFrame {

    private JButton detalhesSolicitacoesButton;
    private JButton detalhesAutorizacoesButton;
    private JButton voltarButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Relatorios relatorios = new Relatorios();
                relatorios.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Relatorios() throws IOException {
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
        // Lógica para mostrar detalhes sobre solicitações
        JOptionPane.showMessageDialog(this, "Detalhes sobre Solicitações");
    }

    private void mostrarDetalhesAutorizacoes() {
        // Lógica para mostrar detalhes sobre autorizações
        JOptionPane.showMessageDialog(this, "Detalhes sobre Autorizações");
    }

    private void voltar() {
        // Lógica para voltar (abrir a interface TacticalCommsHub)
        try {
            TacticalCommsHub tacticalCommsHub = new TacticalCommsHub();
            tacticalCommsHub.setVisible(true);
            dispose(); // Fecha a janela atual (Relatorios)
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


package Client;

import Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Notificacoes extends JFrame {

    private JTextArea mensagemRecebidaArea;
    private JTextArea pedidoAutorizacaoArea;
    private JTextArea notificacaoRelatorioArea;
    private JTextArea solicitacaoCanalArea;

    private static Entity actualUser;

    public Notificacoes(Entity actualUser) {
        this.actualUser = actualUser;
        inicializarComponentes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setTitle("Notificações");
        setLocationRelativeTo(null);
        carregarNotificacoes();
    }

    private void inicializarComponentes() {
        mensagemRecebidaArea = new JTextArea(5, 30);
        pedidoAutorizacaoArea = new JTextArea(5, 30);
        notificacaoRelatorioArea = new JTextArea(5, 30);
        solicitacaoCanalArea = new JTextArea(5, 30);

        mensagemRecebidaArea.setEditable(false);
        pedidoAutorizacaoArea.setEditable(false);
        notificacaoRelatorioArea.setEditable(false);
        solicitacaoCanalArea.setEditable(false);

        JScrollPane scrollMensagemRecebida = new JScrollPane(mensagemRecebidaArea);
        JScrollPane scrollPedidoAutorizacao = new JScrollPane(pedidoAutorizacaoArea);
        JScrollPane scrollNotificacaoRelatorio = new JScrollPane(notificacaoRelatorioArea);
        JScrollPane scrollSolicitacaoCanal = new JScrollPane(solicitacaoCanalArea);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTacticalCommsHub();
            }
        });

        add(new JLabel("Notificação Recebida:"));
        add(scrollMensagemRecebida);
        add(new JLabel("Pedido de Autorização Recebido:"));
        add(scrollPedidoAutorizacao);
        add(new JLabel("Notificação de Relatório:"));
        add(scrollNotificacaoRelatorio);
        add(new JLabel("Solicitação de Canal:"));
        add(scrollSolicitacaoCanal);
        add(voltarButton);
    }

    private void definirLayout() {
        setLayout(new GridLayout(6, 2));
    }

    private void carregarNotificacoes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Notificacoes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Destinatário: Utilizador: " + actualUser.getUsername())) {
                    String mensagem = reader.readLine();
                    adicionarMensagemRecebida(mensagem);
                } else {
                    reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void voltarParaTacticalCommsHub() {
        try {
            TacticalCommsHub tacticalCommsHub = new TacticalCommsHub(actualUser);
            tacticalCommsHub.setVisible(true);
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void adicionarMensagemRecebida(String mensagem) {
        mensagemRecebidaArea.append(mensagem + "\n");
    }

    public void adicionarPedidoAutorizacao(String pedido) {
        pedidoAutorizacaoArea.append(pedido + "\n");
    }

    public void adicionarNotificacaoRelatorio(String notificacao) {
        notificacaoRelatorioArea.append(notificacao + "\n");
    }

    public void adicionarSolicitacaoCanal(String solicitacao) {
        solicitacaoCanalArea.append(solicitacao + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Notificacoes(actualUser).setVisible(true);
        });
    }
}
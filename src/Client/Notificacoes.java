package Client;

import Model.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notificacoes extends JFrame {

    private JTextArea mensagemRecebidaArea;
    private JTextArea pedidoAutorizacaoArea;
    private JTextArea notificacaoRelatorioArea;
    private JTextArea solicitacaoCanalArea;
    private JTextArea mensagemBroadcastArea;

    private static Entity actualUser;

    public Notificacoes(Entity actualUser) {
        this.actualUser = actualUser;
        inicializarComponentes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setTitle("Notificações");
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        mensagemRecebidaArea = new JTextArea(5, 30);
        pedidoAutorizacaoArea = new JTextArea(5, 30);
        notificacaoRelatorioArea = new JTextArea(5, 30);
        solicitacaoCanalArea = new JTextArea(5, 30);
        mensagemBroadcastArea = new JTextArea(5, 30);

        mensagemRecebidaArea.setEditable(false);
        pedidoAutorizacaoArea.setEditable(false);
        notificacaoRelatorioArea.setEditable(false);
        solicitacaoCanalArea.setEditable(false);
        mensagemBroadcastArea.setEditable(false);

        JScrollPane scrollMensagemRecebida = new JScrollPane(mensagemRecebidaArea);
        JScrollPane scrollPedidoAutorizacao = new JScrollPane(pedidoAutorizacaoArea);
        JScrollPane scrollNotificacaoRelatorio = new JScrollPane(notificacaoRelatorioArea);
        JScrollPane scrollSolicitacaoCanal = new JScrollPane(solicitacaoCanalArea);
        JScrollPane scrollMensagemBroadcast = new JScrollPane(mensagemBroadcastArea);

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTacticalCommsHub();
            }
        });

        add(new JLabel("Mensagem Recebida:"));
        add(scrollMensagemRecebida);
        add(new JLabel("Pedido de Autorização Recebido:"));
        add(scrollPedidoAutorizacao);
        add(new JLabel("Notificação de Relatório:"));
        add(scrollNotificacaoRelatorio);
        add(new JLabel("Solicitação de Canal:"));
        add(scrollSolicitacaoCanal);
        add(new JLabel("Mensagem de Broadcast:"));
        add(scrollMensagemBroadcast);
        add(voltarButton);
    }

    private void definirLayout() {
        setLayout(new GridLayout(6, 2));
    }

    private void voltarParaTacticalCommsHub() {
        try {
            TacticalCommsHub tacticalCommsHub = new TacticalCommsHub(actualUser);
            tacticalCommsHub.setVisible(true);
            dispose(); // Fecha a janela atual (Notificações)
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

    public void adicionarMensagemBroadcast(String mensagem) {
        mensagemBroadcastArea.append(mensagem + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Notificacoes(actualUser).setVisible(true);
        });
    }
}

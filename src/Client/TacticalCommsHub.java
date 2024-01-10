package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class TacticalCommsHub extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new TacticalCommsHub().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TacticalCommsHub() {
        inicializarComponentes();
        definirLayout();
        definirOuvintes();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Tactical Comms Hub");
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JButton enviarMensagemButton = new JButton("Enviar Mensagem");
        JButton definirCanalButton = new JButton("Definir Canal");
        JButton pedirAutorizacaoButton = new JButton("Pedir Autorização");
        JButton notificacoesButton = new JButton("Notificações");
        JButton mensagensRecebidasButton = new JButton("Mensagens Recebidas");
        JButton emitirNotificacoesButton = new JButton("Emitir Notificações");
        JButton relatoriosButton = new JButton("Relatórios");
        JButton logoutButton = new JButton("Logout");

        add(enviarMensagemButton);
        add(definirCanalButton);
        add(pedirAutorizacaoButton);
        add(notificacoesButton);
        add(mensagensRecebidasButton);
        add(emitirNotificacoesButton);
        add(relatoriosButton);
        add(logoutButton);
    }

    private void definirLayout() {
        setLayout(new GridLayout(4, 2));
    }    
        
    private void definirOuvintes() {
        JButton enviarMensagemButton = (JButton) getContentPane().getComponent(0); // Índice do botão "Enviar Mensagem"

        enviarMensagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir a página "ChatPage" ou realizar outras ações necessárias
                dispose(); // Fecha a janela atual

                // Aqui, você pode abrir a página "ChatPage" ou realizar outras ações necessárias
                try {
                    new ChatPage("Utilizador").setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        

        JButton logoutButton = (JButton) getContentPane().getComponent(7); // Índice do botão "Logout"

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para logout (redirecionar para a página de login ou realizar outras ações)
                dispose(); // Fecha a janela atual

                // Aqui, você pode abrir a página de login ou realizar outras ações necessárias
                try {
                    new Login().setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        

}
}


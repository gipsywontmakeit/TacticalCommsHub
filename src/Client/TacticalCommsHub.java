package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        JButton pedirAutorizacaoButton = (JButton) getContentPane().getComponent(2); // Índice do botão "Pedir Autorização"

pedirAutorizacaoButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        abrirAutorizacao();
    }
});

        JButton definirCanalButton = (JButton) getContentPane().getComponent(1); // Índice do botão "Definir Canal"

        definirCanalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirListaCanais();
            }
        });

        JButton enviarMensagemButton = (JButton) getContentPane().getComponent(0); // Índice do botão "Enviar Mensagem"

        enviarMensagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirChatPage();
            }
        });

        JButton relatoriosButton = (JButton) getContentPane().getComponent(6); // Índice do botão "Relatórios"

        relatoriosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEAbrirRelatorios();
            }
        });

        JButton logoutButton = (JButton) getContentPane().getComponent(7); // Índice do botão "Logout"

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogout();
            }
        });
    }

    private void abrirAutorizacao() {
        // Lógica para abrir a página "Autorizacao" ou realizar outras ações necessárias
        dispose(); // Fecha a janela atual
        try {
            new Autorizacao().setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                }


    private void abrirChatPage() {
        // Lógica para abrir a página "ChatPage" ou realizar outras ações necessárias
        dispose(); // Fecha a janela atual

        // Aqui, você pode abrir a página "ChatPage" ou realizar outras ações necessárias
        try {
            new ChatPage("Utilizador").setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void abrirListaCanais() {
        // Lógica para abrir a página "ListaCanais" ou realizar outras ações necessárias
        dispose(); // Fecha a janela atual
    
        // Aqui, você pode abrir a página "ListaCanais" ou realizar outras ações necessárias
        try {
            new ListaCanais().setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                }


    private void verificarEAbrirRelatorios() {
        if (verificarPermissaoTenente()) {
            // Lógica para abrir a interface "Relatorios" ou realizar outras ações necessárias
            dispose(); // Fecha a janela atual

            // Aqui, você pode abrir a interface "Relatorios" ou realizar outras ações necessárias
            try {
                new Relatorios().setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem permissão para acessar Relatórios.");
        }
    }

    private void realizarLogout() {
        // Lógica para logout (redirecionar para a página de login ou realizar outras ações)
        dispose(); // Fecha a janela atual

        // Aqui, você pode abrir a página de login ou realizar outras ações necessárias
        try {
            new Login().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean verificarPermissaoTenente() {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
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
}

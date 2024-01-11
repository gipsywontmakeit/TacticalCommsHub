package Client;

import javax.swing.*;

import Model.Entity;

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

    private JLabel activeUsersReportLabel;

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

        JButton notificacoesButton = (JButton) getContentPane().getComponent(3); // Índice do botão "Notificações"

        notificacoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNotificacoes();
            }
        });

        JButton emitirNotificacaoButton = (JButton) getContentPane().getComponent(5); // Índice do botão "Emitir Notificações"
        emitirNotificacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEmitirNotificacao();
            }
        });

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

    private void abrirNotificacoes() {
        // Lógica para abrir a interface "Notificacoes" ou realizar outras ações necessárias
        dispose(); // Fecha a janela atual (TacticalCommsHub)
        try {
            Notificacoes notificacoes = new Notificacoes();
            notificacoes.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void abrirEmitirNotificacao() {
        // Lógica para abrir a interface "Emitir Notificacao" ou realizar outras ações necessárias
        dispose(); // Fecha a janela atual (TacticalCommsHub)
        try {
            new EmitirNotificacao().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Você não tem permissão para acessar Relatórios.");
                    }
                }
                
                private boolean verificarPermissaoTenente() {
                    try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.equals("-----------")) {
                                // Lê as próximas três linhas após "-----------"
                                for (int i = 0; i < 3; i++) {
                                    reader.readLine();
                                }
                
                                // Lê a linha "Opcao"
                                String opcaoLine = reader.readLine();
                                if (opcaoLine != null && opcaoLine.trim().startsWith("Opcao:")) {
                                    // Extrai o valor da opção (Sargento, Tenente, Soldado, etc.)
                                    String opcao = opcaoLine.trim().substring("Opcao:".length()).trim();
                                    // Verifica se a opção é "Tenente"
                                    if ("Tenente".equals(opcao)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
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

   
}
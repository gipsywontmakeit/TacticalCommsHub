package Client;

import Model.Entity;

import javax.swing.*;

import Model.Entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TacticalCommsHub extends JFrame {

    private Entity actualUser;
    private JLabel loggedUser;

    private ChatPage chatPage;

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                new TacticalCommsHub(actualUser).setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public void setLoggedUser(Entity user) {
        this.actualUser = user;
    }

<<<<<<< HEAD
=======
    public TacticalCommsHub(Entity actualUser) {
        this.actualUser = actualUser;
    }

>>>>>>> 2b58f1a391958d4ef85f3af1d17fd877cdde01aa
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
        loggedUser = new JLabel("Utilizador: " + actualUser.getUsername());

        add(loggedUser);
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

<<<<<<< HEAD
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
=======
        JButton pedirAutorizacaoButton = (JButton) getContentPane().getComponent(3); // Índice do botão "Pedir Autorização"
>>>>>>> 2b58f1a391958d4ef85f3af1d17fd877cdde01aa

        JButton notificacoesButton = (JButton) getContentPane().getComponent(4); // Índice do botão "Notificações"

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

        pedirAutorizacaoButton.addActionListener(new ActionListener() {
    @Override
     public void actionPerformed(ActionEvent e) {
        abrirAutorizacao();
    }
});

        JButton definirCanalButton = (JButton) getContentPane().getComponent(2); // Índice do botão "Definir Canal"

        definirCanalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirListaCanais();
            }
        });

        JButton enviarMensagemButton = (JButton) getContentPane().getComponent(1); // Índice do botão "Enviar Mensagem"

        enviarMensagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirChatPage();
            }
        });

        JButton relatoriosButton = (JButton) getContentPane().getComponent(7); // Índice do botão "Relatórios"

        relatoriosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEAbrirRelatorios(actualUser);
            }
        });

        JButton logoutButton = (JButton) getContentPane().getComponent(8); // Índice do botão "Logout"

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
<<<<<<< HEAD
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
=======
>>>>>>> 2b58f1a391958d4ef85f3af1d17fd877cdde01aa
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
        Autorizacao autorizacao = new Autorizacao(actualUser);
        autorizacao.setVisible(true);
        dispose();
    }


    private void abrirChatPage() {
        if(chatPage == null) {
            chatPage = new ChatPage(actualUser, this);
            chatPage.setVisible(true);
        } else {
            chatPage.setVisible(true);
            chatPage.toFront();
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

                private void verificarEAbrirRelatorios(Entity entity) {
                    if (verificarPermissaoTenente(entity)) {
                        dispose();
                        try {
                            new Relatorios(actualUser).setVisible(true);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Você não tem permissão para acessar Relatórios.");
                    }
                }
                
                private boolean verificarPermissaoTenente(Entity entity) {
                    try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.equals("-----------")) {
                                // Lê as próximas três linhas após "-----------"
                                String username = reader.readLine();
                                if(username != null && username.trim().startsWith("Utilizador:")) {
                                    // Extrai o nome de usuário
                                    username = username.trim().substring("Utilizador:".length()).trim();
                                    // Verifica se o nome de usuário corresponde ao usuário atual
                                    if (!username.equals(entity.getUsername())) {
                                        reader.readLine();
                                        reader.readLine();
                                        reader.readLine();
                                        continue;
                                    }
                                }
                                reader.readLine();
                                reader.readLine();

                                String opcaoLine = reader.readLine();
                                if (opcaoLine != null && opcaoLine.trim().startsWith("Opcao:")) {
                                    // Extrai o valor da opção (Sargento, Tenente, Soldado, etc.)
                                    String opcao = opcaoLine.trim().substring("Opcao:".length()).trim();
                                    // Verifica se a opção é "Tenente"
                                    return entity.isTenente() && "Tenente".equals(opcao);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }

                

    private void realizarLogout() {
        dispose(); // Fecha a janela atual

        // Aqui, você pode abrir a página de login ou realizar outras ações necessárias
        try {
            new Login().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        actualUser = null;
        loggedUser.setText("Utilizador: ");
    }

   
}
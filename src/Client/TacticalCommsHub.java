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

    public void setLoggedUser(Entity user) {
        this.actualUser = user;
    }
    private JLabel activeUsersReportLabel;

    public TacticalCommsHub(Entity actualUser) {
        this.actualUser = actualUser;
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


        JButton emitirNotificacaoButton = (JButton) getContentPane().getComponent(6); 
        emitirNotificacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEmitirNotificacao();
            }
        });

        JButton pedirAutorizacaoButton = (JButton) getContentPane().getComponent(3); 
        JButton notificacoesButton = (JButton) getContentPane().getComponent(4); 

        notificacoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNotificacoes();
            }
        });
        pedirAutorizacaoButton.addActionListener(new ActionListener() {
    @Override
     public void actionPerformed(ActionEvent e) {
        abrirAutorizacao();
    }
});

        JButton definirCanalButton = (JButton) getContentPane().getComponent(2); 

        definirCanalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirListaCanais();
            }
        });

        JButton enviarMensagemButton = (JButton) getContentPane().getComponent(1); 

        enviarMensagemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirChatPage();
            }
        });

        JButton mensagensRecebidasButton = (JButton) getContentPane().getComponent(5);

        mensagensRecebidasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirMensagensRecebidas();
            }
        });

        JButton relatoriosButton = (JButton) getContentPane().getComponent(7); 

        relatoriosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEAbrirRelatorios(actualUser);
            }
        });

        JButton logoutButton = (JButton) getContentPane().getComponent(8); 

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogout();
            }
        });
    }

    private void abrirNotificacoes() {
       
        dispose(); 
        try {
            Notificacoes notificacoes = new Notificacoes(actualUser);
            notificacoes.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void abrirEmitirNotificacao() {
        dispose(); 
        try {
            new EmitirNotificacao(actualUser).setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void abrirMensagensRecebidas() {
        try {
        MensagensRecebidas mensagensRecebidas = new MensagensRecebidas(actualUser);
        mensagensRecebidas.setVisible(true);
        dispose();
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
        dispose(); 
    
        try {
            new ListaCanais(actualUser).setVisible(true);
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
                        JOptionPane.showMessageDialog(this, "Não tens permissão para aceder a Relatórios.");
                    }
                }
                
                private boolean verificarPermissaoTenente(Entity entity) {
                    try (BufferedReader reader = new BufferedReader(new FileReader("UserData.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.equals("-----------")) {
                                String username = reader.readLine();
                                if(username != null && username.trim().startsWith("Utilizador:")) {
                                    username = username.trim().substring("Utilizador:".length()).trim();
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
                                    String opcao = opcaoLine.trim().substring("Opcao:".length()).trim();
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
        dispose(); 

        try {
            new Login().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        actualUser = null;
        loggedUser.setText("Utilizador: ");
    }

   
}
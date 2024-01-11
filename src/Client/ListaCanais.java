package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListaCanais extends JFrame {

    private JComboBox<String> canaisComboBox;
    private JButton entrarButton;
    private JButton voltarButton;

    private static final String CANAIS_FILE = "CanaisComunicacao.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ListaCanais listaCanais = new ListaCanais();
                listaCanais.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public ListaCanais() throws IOException {
        inicializarComponentes();
        definirOuvintes();
        definirLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Lista de Canais");
        setLocationRelativeTo(null);

        carregarCanais();
    }

    private void inicializarComponentes() {
        this.canaisComboBox = new JComboBox<>();
        this.entrarButton = new JButton("Entrar");
        this.voltarButton = new JButton("Voltar");
    }

    private void definirOuvintes() {
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entrarNoCanal();
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
        JPanel painelListaCanais = new JPanel();
        painelListaCanais.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(canaisComboBox);
        topPanel.add(entrarButton);

        painelListaCanais.add(topPanel, BorderLayout.NORTH);
        painelListaCanais.add(voltarButton, BorderLayout.SOUTH);

        add(painelListaCanais);
    }

    private void carregarCanais() throws IOException {
        ArrayList<String> canaisList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CANAIS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                canaisList.add(line.trim());
            }
        }

        canaisComboBox.setModel(new DefaultComboBoxModel<>(canaisList.toArray(new String[0])));
    }

    private void entrarNoCanal() {
        String canalSelecionado = (String) canaisComboBox.getSelectedItem();
        if (canalSelecionado != null) {
            // Lógica para entrar no canal
            System.out.println("Entrando no canal: " + canalSelecionado);
        }
    }

    private void voltar() {
        // Lógica para voltar (fechar a janela atual, por exemplo)
        dispose();
    }
}

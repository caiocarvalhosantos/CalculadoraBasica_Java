/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadora_swing;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Calc_Swing extends JFrame {

    private JTextField txtValor1;
    private JTextField txtValor2;
    private JLabel lblResultado;

    public Calc_Swing() {
        setTitle("Calculadora");
        setSize(390, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corVinho = new Color(110, 0, 0);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 15, 6, 15);

        // Título
        JLabel lblTitulo = new JLabel("Calculadora");
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 32));
        lblTitulo.setForeground(corVinho);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(lblTitulo, gbc);

        // Rótulo e campo Valor 1
        JLabel lblValor1 = new JLabel("Valor 1");
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 15, 2, 15);
        painel.add(lblValor1, gbc);

        txtValor1 = new JTextField();
        txtValor1.setPreferredSize(new Dimension(250, 28));
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 15, 8, 15);
        painel.add(txtValor1, gbc);

        // Rótulo e campo Valor 2
        JLabel lblValor2 = new JLabel("Valor 2");
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(6, 15, 2, 15);
        painel.add(lblValor2, gbc);

        txtValor2 = new JTextField();
        txtValor2.setPreferredSize(new Dimension(250, 28));
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 15, 14, 15);
        painel.add(txtValor2, gbc);

        // Botões de operação (grade 2x2)
        JButton btnSomar = criarBotao("somar", corVinho);
        JButton btnSubtrair = criarBotao("subtrair", corVinho);
        JButton btnMultiplicar = criarBotao("multiplicar", corVinho);
        JButton btnDividir = criarBotao("dividir", corVinho);
        JButton btnLimpar = criarBotao("Limpar", corVinho);

        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 15, 6, 15);

        gbc.gridx = 0;
        gbc.gridy = 5;
        painel.add(btnSomar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        painel.add(btnSubtrair, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        painel.add(btnMultiplicar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        painel.add(btnDividir, gbc);

        // Botão Limpar centralizado
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 15, 8, 15);
        painel.add(btnLimpar, gbc);

        // Rótulo de Resultado
        lblResultado = new JLabel(" ");
        lblResultado.setFont(new Font("SansSerif", Font.PLAIN, 13));
        gbc.gridy = 8;
        gbc.insets = new Insets(4, 15, 10, 15);
        painel.add(lblResultado, gbc);

        // Eventos dos botões
        btnSomar.addActionListener(e -> calcular('+'));
        btnSubtrair.addActionListener(e -> calcular('-'));
        btnMultiplicar.addActionListener(e -> calcular('*'));
        btnDividir.addActionListener(e -> calcular('/'));
        btnLimpar.addActionListener(e -> {
            txtValor1.setText("");
            txtValor2.setText("");
            lblResultado.setText(" ");
            txtValor1.requestFocus();
        });

        add(painel);
    }

    private JButton criarBotao(String texto, Color corTexto) {
        JButton botao = new JButton(texto);
        botao.setForeground(corTexto);
        botao.setPreferredSize(new Dimension(110, 28));
        return botao;
    }

    private void calcular(char operacao) {
        try {
            double v1 = Double.parseDouble(txtValor1.getText().replace(",", "."));
            double v2 = Double.parseDouble(txtValor2.getText().replace(",", "."));
            double resultado = 0;

            switch (operacao) {
                case '+':
                    resultado = v1 + v2;
                    break;
                case '-':
                    resultado = v1 - v2;
                    break;
                case '*':
                    resultado = v1 * v2;
                    break;
                case '/':
                    if (v2 == 0) {
                        lblResultado.setText("Erro: divisão por zero");
                        return;
                    }
                    resultado = v1 / v2;
                    break;
            }

            lblResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException ex) {
            lblResultado.setText("Digite números válidos");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> new Calc_Swing().setVisible(true));
    }
}

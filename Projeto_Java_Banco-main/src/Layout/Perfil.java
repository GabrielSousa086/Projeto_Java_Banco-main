package Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.BancoDeDados;

public class Perfil implements ActionListener {
    private JButton jButton, jButton2, jButton3;
    private JFrame frame;
    private JLabel nameLabel, cpfLabel, saldoLabel;
    private String cpfUsuario;

    public Perfil(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;

        frame = new JFrame("Perfil do Usuário");

        nameLabel = new JLabel("Nome: ");
        nameLabel.setBounds(50, 50, 250, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(nameLabel);

        cpfLabel = new JLabel("CPF: ");
        cpfLabel.setBounds(50, 100, 250, 30);
        cpfLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(cpfLabel);

        saldoLabel = new JLabel("Saldo: ");
        saldoLabel.setBounds(50, 150, 250, 30);
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(saldoLabel);

        jButton = new JButton("Saque");
        jButton.setBounds(50, 230, 170, 50);
        jButton.setFont(new Font("Arial", Font.BOLD, 20));
        jButton.addActionListener(this);
        frame.add(jButton);

        jButton2 = new JButton("Transferir");
        jButton2.setBounds(250, 230, 170, 50);
        jButton2.setFont(new Font("Arial", Font.BOLD, 20));
        jButton2.addActionListener(this);
        frame.add(jButton2);

        jButton3 = new JButton("Depositar");
        jButton3.setBounds(450, 230, 170, 50);
        jButton3.setFont(new Font("Arial", Font.BOLD, 20));
        jButton3.addActionListener(this);
        frame.add(jButton3);

        carregarInformacoesUsuario();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton) {
            System.out.println("Botão Saque clicado!");
            frame.dispose();
            new Saque(cpfUsuario);

        } else if (e.getSource() == jButton2) {
            System.out.println("Botão Depósito clicado!");
            frame.dispose();
            new Transferir();
        }else if (e.getSource() == jButton3){
            frame.dispose();
            new Deposito(cpfUsuario);
        }

        }

    private void carregarInformacoesUsuario() {
        BancoDeDados bancoDeDados = null;
        try {
            bancoDeDados = new BancoDeDados();
            ResultSet resultSet = bancoDeDados.obterInformacoesUsuario(cpfUsuario);
            if (resultSet.next()) {
                nameLabel.setText("Nome: " + resultSet.getString("nome"));
                cpfLabel.setText("CPF: " + resultSet.getString("cpf"));
                saldoLabel.setText("Saldo: R$ " + resultSet.getDouble("saldo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erro ao carregar informações do usuário.");
        } finally {
            if (bancoDeDados != null) {
                try {
                    bancoDeDados.fecharConexao();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        // Para testar, você pode passar um CPF diretamente
        new Perfil("12345678900");
    }
}

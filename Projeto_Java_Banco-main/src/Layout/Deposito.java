package Layout;

import application.ContaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Deposito implements ActionListener {
    private JFrame frame;
    private JButton depositoButton, cancelarButton;
    private JTextField valorTextField;
    private String numeroConta; // Adicione isso para identificar a conta

    public Deposito(String numeroConta) { // Recebe o número da conta como parâmetro
        this.numeroConta = numeroConta;

        frame = new JFrame("Deposito");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Deposito");
        titleLabel.setBounds(350, 30, 100, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel);

        JLabel valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(300, 100, 60, 30);
        frame.add(valorLabel);

        valorTextField = new JTextField();
        valorTextField.setBounds(350, 100, 150, 30);
        frame.add(valorTextField);

        depositoButton = new JButton("Deposito");
        depositoButton.setBounds(250, 200, 170, 50);
        depositoButton.setFont(new Font("Arial", Font.BOLD, 20));
        depositoButton.addActionListener(this);
        frame.add(depositoButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(460, 200, 170, 50);
        cancelarButton.setFont(new Font("Arial", Font.BOLD, 20));
        cancelarButton.addActionListener(this);
        frame.add(cancelarButton);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositoButton) {
            try {
                depositar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao realizar depósito: " + ex.getMessage());
            }
        } else if (e.getSource() == cancelarButton) {
            frame.dispose(); 
            new Perfil(numeroConta);
        }
    }

    private void depositar() throws SQLException {
        
        double valor = Double.parseDouble(valorTextField.getText());

        
        ContaDAO contaDAO = new ContaDAO();
        contaDAO.depositar("996953", valor);

        
        JOptionPane.showMessageDialog(frame, "Depósito de R$ " + valor + " realizado com sucesso.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Deposito("12345"); // Passe um número de conta válido
            }
        });
    }
}
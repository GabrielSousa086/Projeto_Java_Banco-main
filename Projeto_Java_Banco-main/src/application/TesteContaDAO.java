package application;

import java.sql.SQLException;

public class TesteContaDAO {
    public static void main(String[] args) {
        try {
            ContaDAO contaDAO = new ContaDAO();

   

            // Carregando as contas
            ContaBancaria contaCarregadaCorrente = contaDAO.carregar("123456");
           

            System.out.println("Saldo inicial da conta corrente: " + contaCarregadaCorrente.getSaldo());
           

            contaDAO.depositar("123456", 500.0);
            System.out.println("Saldo da conta corrente após depósito: " + contaDAO.carregar("123456").getSaldo());

            // Realizando um saque na conta poupança
            contaDAO.sacar("123456", 300.0);
            System.out.println("Saldo da conta poupança após saque: " + contaDAO.carregar("67890").getSaldo());

            // Transferindo saldo entre contas
            
        } catch (SQLException | SaldoInsuficienteException e) {
            e.printStackTrace();
        }
    }
}
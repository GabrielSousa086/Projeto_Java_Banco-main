package application;

import java.sql.SQLException;

public class Login {
    private String cpf;
    private String senha;

    public Login(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean realizarLogin(String cpfDigitado, String senhaDigitada) {
        try {
            BancoDeDados bancoDeDados = new BancoDeDados();
            boolean loginValido = bancoDeDados.verificarCredenciais(cpfDigitado, senhaDigitada);
            bancoDeDados.fecharConexao();
            return loginValido;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Login login = new Login("09263615371", "3524");
        boolean resultado = login.realizarLogin("09263615371", "3524");
        System.out.println("Login bem-sucedido: " + resultado);
    }
}

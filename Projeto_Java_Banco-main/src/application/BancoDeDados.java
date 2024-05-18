package application;

import java.sql.*;

public class BancoDeDados {
    private Connection connection;

    public BancoDeDados() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bankingxl";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
    }

    public boolean verificarCredenciais(String cpf, String senha) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE cpf = ? AND senha = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cpf);
        statement.setString(2, senha);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public void registrarUsuario(String nome, String cpf, String dataNascimento, String senha, String tipoConta) throws SQLException {
        String query = "INSERT INTO usuarios (nome, cpf, datanascimento, senha, tipo_conta) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nome);
        statement.setString(2, cpf);
        statement.setString(3, dataNascimento);
        statement.setString(4, senha);
        statement.setString(5, tipoConta);
        statement.executeUpdate();
    }

    public ResultSet obterInformacoesUsuario(String cpf) throws SQLException {
        String query = "SELECT u.nome, u.cpf, c.saldo FROM usuarios u JOIN contas c ON u.id = c.id_usuario WHERE u.cpf = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cpf);
        return statement.executeQuery();
    }

    public void fecharConexao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public boolean verificarExistenciaUsuario(String cpf) throws SQLException {
        String query = "SELECT 1 FROM usuarios WHERE cpf = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, cpf);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public void definirSaldoInicial(String cpf, double saldoInicial) throws SQLException {
        String query = "UPDATE contas SET saldo = ? WHERE cpf_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, saldoInicial);
        statement.setString(2, cpf);
        statement.executeUpdate();
    }
}

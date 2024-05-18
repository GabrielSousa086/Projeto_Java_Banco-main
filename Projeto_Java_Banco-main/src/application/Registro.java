package application;

import java.sql.*;

public class Registro {
    private String Nome;
    private String CPF;
    private String DataNascimento;
    private String Senha;
    private String TipoConta;

    public Registro(String nome, String cpf, String DataNascimento, String senha, String tipoConta) {
        this.Nome = nome;
        this.CPF = cpf;
        this.DataNascimento = DataNascimento; 
        this.Senha = senha;
        this.TipoConta = tipoConta;
    }

    public void registrarUsuario() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingxl", "root", "")) {
            String sqlUsuarios = "INSERT INTO usuarios (nome, cpf, DataNascimento, senha, tipo_conta) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUsuarios, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, Nome);
                preparedStatement.setString(2, CPF);
                String formattedDataNascimento = formatDataNascimento(DataNascimento);
                preparedStatement.setString(3, formattedDataNascimento);
                preparedStatement.setString(4, Senha);
                preparedStatement.setString(5, TipoConta);

                int linhasAfetadas = preparedStatement.executeUpdate();
                if (linhasAfetadas > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idUsuario = generatedKeys.getInt(1);
                        String sqlContas = "INSERT INTO contas (id_usuario, tipo_conta) VALUES (?, ?)";
                        try (PreparedStatement preparedStatementContas = connection.prepareStatement(sqlContas)) {
                            preparedStatementContas.setInt(1, idUsuario);
                            preparedStatementContas.setString(2, TipoConta);
                            int linhasAfetadasContas = preparedStatementContas.executeUpdate();
                            if (linhasAfetadasContas > 0) {
                                System.out.println("Usuário registrado com sucesso!");
                            } else {
                                System.out.println("Erro ao registrar o usuário.");
                            }
                        }
                    }
                } else {
                    System.out.println("Erro ao registrar o usuário.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String formatDataNascimento(String DataNascimento) {
        return DataNascimento;
    }

    public boolean realizarLogin(String cpfDigitado, String senhaDigitada) {
        return false;
    }
}

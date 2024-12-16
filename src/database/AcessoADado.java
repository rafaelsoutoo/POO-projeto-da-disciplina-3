package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AcessoADado {

    private static final String URL = "jdbc:postgresql://localhost:5432/banco_de_dados"; 
    private static final String USUARIO = "user"; 
    private static final String SENHA = "si250350"; 
    // Método para obter a conexão com o banco
    private Connection obterConexao() throws SQLException {
        try {
            // Carregar o driver JDBC
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }

    // Método para cadastrar uma nova conta
    public String cadastrar_conta(String numero, float saldo) {
        String sql = "INSERT INTO contas (numero, saldo) VALUES (?, ?)";

        try (Connection conn = obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numero);
            stmt.setFloat(2, saldo);
            stmt.executeUpdate();
            return "Conta " + numero + " cadastrada com saldo " + saldo;
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao cadastrar conta.";
        }
    }

    // Método para cadastrar uma conta normal
    public String cadastrar_conta_normal(String numero) {
        return cadastrar_conta(numero, 0.0f);
    }

    // Método para remover uma conta
    public String remover_conta(String numero) {
        String sql = "DELETE FROM contas WHERE numero = ?";

        try (Connection conn = obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numero);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return "Conta " + numero + " removida com sucesso.";
            } else {
                return "Conta não encontrada.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao remover conta.";
        }
    }

    // Método para atualizar o saldo de uma conta
    public String atualizar_saldo(String numero, double saldo) {
        String sql = "UPDATE contas SET saldo = ? WHERE numero = ?";

        try (Connection conn = obterConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, saldo);
            stmt.setString(2, numero);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                return "Saldo da conta " + numero + " atualizado para " + saldo;
            } else {
                return "Conta não encontrada.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar saldo.";
        }
    }
}
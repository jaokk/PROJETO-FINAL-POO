package ucb.br;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Classe responsável pela gestão da conexão com o banco de dados
public class DatabaseManager {
    private static Connection connection; // Variável estática para manter a conexão com o banco de dados

    // Método para conectar ao banco de dados SQLite
    public static void connect() {
        try {
            String url = "jdbc:sqlite:empresa.db"; // URL de conexão ao banco de dados SQLite
            connection = DriverManager.getConnection(url); // Estabelece a conexão com o banco de dados
            initialize(); // Chama o método para inicializar o banco de dados (criar tabelas, etc.)
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage()); // Trata erros de conexão
        }
    }

    // Método privado para inicializar a estrutura do banco de dados (como tabelas)
    private static void initialize() {
        try (Statement stmt = connection.createStatement()) {
            // SQL para criar a tabela Funcionario se ela não existir
            String sql = "CREATE TABLE IF NOT EXISTS Funcionario (" +
                         "cpf TEXT PRIMARY KEY, " + // Coluna 'cpf' como chave primária
                         "nome TEXT NOT NULL, " +  // Coluna 'nome'
                         "cargo TEXT NOT NULL, " + // Coluna 'cargo'
                         "salario REAL, " +        // Coluna 'salario' do tipo real
                         "departamentoGerenciado TEXT);"; // Coluna 'departamentoGerenciado' para gerentes
            stmt.execute(sql); // Executa o comando SQL para criar a tabela
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage()); // Trata possíveis erros na criação da tabela
        }
    }

    // Método público para obter a conexão com o banco de dados
    public static Connection getConnection() {
        return connection; // Retorna a conexão com o banco de dados
    }
}

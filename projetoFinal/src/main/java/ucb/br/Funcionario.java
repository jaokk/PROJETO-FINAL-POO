package ucb.br;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe Funcionario que estende a classe abstrata Pessoa
public class Funcionario extends Pessoa {
    protected String cargo; // Atributo para armazenar o cargo do funcionário
    protected double salario; // Atributo para armazenar o salário do funcionário

    // Construtor da classe Funcionario
    public Funcionario(String nome, String cpf, String cargo, double salario) {
        super(nome, cpf); // Chama o construtor da superclasse Pessoa
        this.cargo = cargo; // Inicializa o cargo do funcionário
        this.salario = salario; // Inicializa o salário do funcionário
    }

    // Método sobrescrito para cadastrar um funcionário no banco de dados
    @Override
    public void cadastrar() {
        // Comando SQL para inserção de dados na tabela Funcionario
        String sql = "INSERT INTO Funcionario (cpf, nome, cargo, salario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            // Preparação dos dados para inserção
            pstmt.setString(1, this.cpf);
            pstmt.setString(2, this.nome);
            pstmt.setString(3, this.cargo);
            pstmt.setDouble(4, this.salario);
            pstmt.executeUpdate(); // Executa a inserção
        } catch (SQLException e) {
            // Tratamento de erros SQL
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    // Método sobrescrito para consultar um funcionário no banco de dados
    @Override
    public Funcionario consultar(String cpf) {
        // Comando SQL para buscar um funcionário pelo CPF
        String sql = "SELECT * FROM Funcionario WHERE cpf = ?";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, cpf); // Define o CPF para a consulta
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta
            if (rs.next()) {
                // Se encontrar o funcionário, cria um novo objeto Funcionario com os dados
                // obtidos
                String nome = rs.getString("nome");
                String cargo = rs.getString("cargo");
                double salario = rs.getDouble("salario");
                return new Funcionario(nome, cpf, cargo, salario);
            }
        } catch (SQLException e) {
            // Tratamento de erros SQL
            System.out.println("Erro ao consultar funcionário: " + e.getMessage());
        }
        return null; // Retorna null se o funcionário não for encontrado
    }

    // Método sobrescrito para atualizar um funcionário no banco de dados
    @Override
    public void atualizar(String cpf, String nome) {
        // Comando SQL para atualizar o nome do funcionário com base no CPF
        String sql = "UPDATE Funcionario SET nome = ? WHERE cpf = ?";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, nome); // Define o novo nome
            pstmt.setString(2, cpf); // Define o CPF
            pstmt.executeUpdate(); // Executa a atualização
        } catch (SQLException e) {
            // Tratamento de erros SQL
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // Método sobrescrito para excluir um funcionário do banco de dados
    @Override
    public void excluir(String cpf) {
        // Comando SQL para excluir um funcionário com base no CPF
        String sql = "DELETE FROM Funcionario WHERE cpf = ?";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, cpf); // Define o CPF
            pstmt.executeUpdate(); // Executa a exclusão
        } catch (SQLException e) {
            // Tratamento de erros SQL
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
}

package ucb.br;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe Gerente, que estende a classe Funcionario
public class Gerente extends Funcionario {
    private String departamentoGerenciado; // Atributo adicional para armazenar o departamento gerenciado pelo gerente

    // Construtor da classe Gerente
    public Gerente(String nome, String cpf, String cargo, double salario, String departamentoGerenciado) {
        super(nome, cpf, cargo, salario); // Chama o construtor da classe pai (Funcionario)
        this.departamentoGerenciado = departamentoGerenciado; // Inicializa o departamento gerenciado
    }

    // Sobrescreve o método cadastrar da classe pai para incluir o departamento gerenciado
    @Override
    public void cadastrar() {
        String sql = "INSERT INTO Funcionario (cpf, nome, cargo, salario, departamentoGerenciado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, this.cpf); // Define o CPF do gerente
            pstmt.setString(2, this.nome); // Define o nome do gerente
            pstmt.setString(3, this.cargo); // Define o cargo do gerente
            pstmt.setDouble(4, this.salario); // Define o salário do gerente
            pstmt.setString(5, this.departamentoGerenciado); // Define o departamento gerenciado
            pstmt.executeUpdate(); // Executa a inserção no banco de dados
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar gerente: " + e.getMessage()); // Trata possíveis erros de SQL
        }
    }

    // Sobrescreve o método consultar da classe pai para buscar informações específicas de gerentes
    @Override
    public Gerente consultar(String cpf) {
        String sql = "SELECT * FROM Funcionario WHERE cpf = ?";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, cpf); // Define o CPF para consulta
            ResultSet rs = pstmt.executeQuery(); // Executa a consulta
            if (rs.next()) {
                // Recupera os dados do gerente a partir do ResultSet
                String nome = rs.getString("nome");
                String cargo = rs.getString("cargo");
                double salario = rs.getDouble("salario");
                String departamento = rs.getString("departamentoGerenciado");
                return new Gerente(nome, cpf, cargo, salario, departamento); // Retorna um novo objeto Gerente
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar gerente: " + e.getMessage()); // Trata possíveis erros de SQL
        }
        return null; // Retorna null se o gerente não for encontrado
    }

    // Sobrescreve o método atualizar da classe pai para atualizar informações específicas de gerentes
    @Override
    public void atualizar(String cpf, String nome) {
        String sql = "UPDATE Funcionario SET nome = ?, departamentoGerenciado = ? WHERE cpf = ?";
        try (PreparedStatement pstmt = DatabaseManager.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, nome); // Define o novo nome
            pstmt.setString(2, this.departamentoGerenciado); // Usa o valor atual de departamentoGerenciado
            pstmt.setString(3, cpf); // Define o CPF
            pstmt.executeUpdate(); // Executa a atualização no banco de dados
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar gerente: " + e.getMessage()); // Trata possíveis erros de SQL
        }
    }

    // Utiliza o método excluir da classe pai, pois o processo de exclusão é o mesmo para Funcionários e Gerentes
    @Override
    public void excluir(String cpf) {
        super.excluir(cpf); // Chama o método excluir da classe base Funcionario
    }

    // Método específico da classe Gerente para gerenciar o departamento
    public void gerenciarDepartamento() {
        System.out.println("Gerenciando o Departamento: " + this.departamentoGerenciado);
        System.out.println("Realizando atividades administrativas para o departamento " + this.departamentoGerenciado);
    }

    // Método getter para acessar o valor de departamentoGerenciado
    public String getDepartamentoGerenciado() {
        return departamentoGerenciado;
    }
}

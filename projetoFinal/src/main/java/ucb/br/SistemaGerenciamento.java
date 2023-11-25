package ucb.br;

import java.util.Scanner;

// Classe principal para o sistema de gerenciamento de funcionários e gerentes
public class SistemaGerenciamento {

    private Scanner scanner; // Scanner para entrada de dados do usuário
    private DatabaseManager dbManager; // Gerenciador do banco de dados

    // Construtor da classe SistemaGerenciamento
    public SistemaGerenciamento() {
        scanner = new Scanner(System.in); // Inicializa o scanner
        dbManager = new DatabaseManager(); // Inicializa o gerenciador de banco de dados
    }

    // Método para iniciar o sistema de gerenciamento
    public void iniciar() {
        DatabaseManager.connect(); // Estabelece conexão com o banco de dados

        // Loop infinito para manter o programa rodando
        while (true) {
            // Imprime o menu de opções
            System.out.println("Sistema de Gerenciamento de Funcionários e Gerentes");
            System.out.println("1. Adicionar Funcionário");
            System.out.println("2. Visualizar Funcionário");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Excluir Funcionário");
            System.out.println("5. Adicionar Gerente");
            System.out.println("6. Visualizar Gerente");
            System.out.println("7. Atualizar Gerente");
            System.out.println("8. Excluir Gerente");
            System.out.println("9. Sair");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt(); // Lê a opção do usuário

            // Switch para tratar a opção escolhida pelo usuário
            switch (opcao) {
                case 1:
                    adicionarFuncionario(); // Adiciona um funcionário
                    break;
                case 2:
                    visualizarFuncionario(); // Visualiza um funcionário
                    break;
                case 3:
                    atualizarFuncionario(); // Atualiza um funcionário
                    break;
                case 4:
                    excluirFuncionario(); // Exclui um funcionário
                    break;
                case 5:
                    adicionarGerente(); // Adiciona um gerente
                    break;
                case 6:
                    visualizarGerente(); // Visualiza um gerente
                    break;
                case 7:
                    atualizarGerente(); // Atualiza um gerente
                    break;
                case 8:
                    excluirGerente(); // Exclui um gerente
                    break;
                case 9:
                    System.out.println("Encerrando sistema..."); // Encerra o programa
                    return;
                default:
                    System.out.println("Opção inválida!"); // Mensagem para opção inválida
            }
        }
    }

    // Método para adicionar um novo funcionário ao sistema
    private void adicionarFuncionario() {
        System.out.println("Adicionar novo Funcionário");
        // Obtém dados do funcionário do usuário
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Cargo: ");
        String cargo = scanner.next();
        System.out.print("Salário: ");
        double salario = scanner.nextDouble();

        // Cria um objeto Funcionario e o adiciona ao banco de dados
        Funcionario funcionario = new Funcionario(nome, cpf, cargo, salario);
        funcionario.cadastrar();
        System.out.println("Funcionário adicionado com sucesso.");
    }

    // Método para visualizar os dados de um funcionário específico
    private void visualizarFuncionario() {
        System.out.println("Visualizar Funcionário");
        System.out.print("CPF: ");
        String cpf = scanner.next();

        // Consulta o funcionário pelo CPF
        Funcionario funcionario = new Funcionario("", cpf, "", 0);
        Funcionario result = funcionario.consultar(cpf);

        // Mostra os dados do funcionário ou informa se não foi encontrado
        if (result != null) {
            System.out.println("Nome: " + result.nome + ", Cargo: " + result.cargo + ", Salário: " + result.salario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private void atualizarFuncionario() {
        System.out.println("Atualizar Funcionário");
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Novo Nome: ");
        String nome = scanner.next();

        Funcionario funcionario = new Funcionario("", cpf, "", 0);
        funcionario.atualizar(cpf, nome);
        System.out.println("Funcionário atualizado com sucesso.");
    }

    // Método para excluir um funcionário do sistema
    private void excluirFuncionario() {
        System.out.println("Excluir Funcionário");
        System.out.print("CPF: ");
        String cpf = scanner.next();

        // Exclui o funcionário com base no CPF
        Funcionario funcionario = new Funcionario("", cpf, "", 0);
        funcionario.excluir(cpf);
        System.out.println("Funcionário excluído com sucesso.");
    }

    // Método para adicionar um novo gerente ao sistema
    private void adicionarGerente() {
        System.out.println("Adicionar novo Gerente");
        // Obtém dados do gerente do usuário
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Cargo: ");
        String cargo = scanner.next();
        System.out.print("Salario: ");
        Double salario = scanner.nextDouble(); 
        System.out.print("Departamento: ");
        String departamento = scanner.next();

        // Cria um objeto Gerente e o adiciona ao banco de dados
        Gerente gerente = new Gerente(nome, cpf, cargo, salario, departamento);
        gerente.cadastrar();
        System.out.println("Gerente adicionado com sucesso.");
    }

    // Método para visualizar os dados de um gerente específico
    private void visualizarGerente() {
        // Obtém CPF, novo nome e novo departamento do usuário
        System.out.println("Visualizar Gerente");
        System.out.print("CPF: ");
        String cpf = scanner.next();
        // Consulta o gerente pelo CPF e exibe suas informações
        Gerente funcionario = new Gerente("", cpf, "", 0, "");
        Gerente result = funcionario.consultar(cpf);

        if (result != null && result instanceof Gerente) {
            Gerente gerente = (Gerente) result;
            System.out.println("Nome: " + gerente.nome + ", Cargo: " + gerente.cargo + ", Salário: " + gerente.salario
                    + ", Departamento Gerenciado: " + gerente.getDepartamentoGerenciado());
        } else {
            System.out.println("Gerente não encontrado.");
        }
    }

    private void atualizarGerente() {
        // Obtém CPF, novo nome e novo departamento do usuário
        System.out.println("Atualizar Gerente");
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Novo Nome: ");
        String nome = scanner.next();
        System.out.print("Novo Departamento Gerenciado: ");
        String departamento = scanner.next();
        // Atualiza o gerente no banco de dados
        Gerente gerente = new Gerente("", cpf, "", 0, departamento);
        gerente.atualizar(cpf, nome);
        System.out.println("Gerente atualizado com sucesso.");
    }

    // Método para excluir um gerente do sistema
    private void excluirGerente() {
        System.out.println("Excluir Gerente");
        System.out.print("CPF: ");
        String cpf = scanner.next();
        // Exclui o gerente com base no CPF
        Gerente gerente = new Gerente("", cpf, "", 0, "");
        gerente.excluir(cpf);
        System.out.println("Gerente excluído com sucesso.");
    }

    public static void main(String[] args) {
        SistemaGerenciamento sistema = new SistemaGerenciamento();
        sistema.iniciar();
    }
}

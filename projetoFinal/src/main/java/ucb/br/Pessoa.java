package ucb.br;

// Classe abstrata Pessoa que servirá como base para outras classes
public abstract class Pessoa {
    protected String nome; // Atributo protegido para o nome da pessoa
    protected String cpf; // Atributo protegido para o CPF da pessoa

    // Construtor para inicializar um objeto Pessoa com nome e cpf
    public Pessoa(String nome, String cpf) {
        this.nome = nome; // Atribui o nome fornecido ao atributo nome
        this.cpf = cpf; // Atribui o CPF fornecido ao atributo cpf
    }

    // Método abstrato cadastrar - deve ser implementado nas subclasses
    // Responsável por cadastrar a pessoa em algum tipo de armazenamento (ex: banco
    // de dados)
    public abstract void cadastrar();

    // Método abstrato consultar - deve ser implementado nas subclasses
    // Deve retornar uma Pessoa com base no cpf fornecido
    public abstract Pessoa consultar(String cpf);

    // Método abstrato atualizar - deve ser implementado nas subclasses
    // Responsável por atualizar os dados da pessoa com base no cpf
    public abstract void atualizar(String cpf, String nome);

    // Método abstrato excluir - deve ser implementado nas subclasses
    // Responsável por excluir a pessoa com base no cpf
    public abstract void excluir(String cpf);
}

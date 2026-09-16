package sistema.pessoas

import enumeradores.Setor
import pessoas.Funcionario
import repositorio.JPA
import validacoes.cpfValido
import validacoes.lerBigDecimalValido
import validacoes.lerIntValido

fun cadastrarFuncionario() {

    println("Digite o nome do funcionário: ")
    val nome = readln()

    // do-while: fica repetindo esse bloco ATE o cpf digitado ser valido
    var cpf: String
    do {
        println("Digite o CPF (somente números, 11 dígitos): ")
        cpf = readln()
        if (!cpfValido(cpf)) {   // "!" na frente inverte: "!cpfValido(cpf)" = "SE NAO for valido"
            println("CPF inválido! Precisa ter exatamente 11 números, sem ponto e sem traço.")
        }
    } while (!cpfValido(cpf))

    val idade = lerIntValido("Digite a idade: ", 0, 120)

    val salario = lerBigDecimalValido("Digite o salário: ")

    println("Escolha o setor: ")
    Setor.entries.forEach { setor ->
        println("${setor.ordinal} - ${setor.name}")
    }
    val numeroSetor = lerIntValido("Escolha o número do setor: ", 0, Setor.entries.size - 1)
    val setor = Setor.entries[numeroSetor]

    val funcionario = Funcionario(
        nome = nome,
        cpf = cpf,
        idade = idade,
        salario = salario,
        setor = setor
    )

    val jpa = JPA()               // abre a "ponte" com o banco
    jpa.salvarFuncionario(funcionario)   // salva o funcionário criado
}

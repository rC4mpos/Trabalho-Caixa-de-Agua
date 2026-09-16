package sistema.pessoas

import pessoas.Fornecedor
import repositorio.JPA
import validacoes.cpfValido

fun cadastrarFornecedor() {

    println("Digite o nome do fornecedor: ")
    val nome = readln()

    var cpf: String
    do {
        println("Digite o CPF/CNPJ (somente números, 11 dígitos): ")
        cpf = readln()
        if (!cpfValido(cpf)) {
            println("Inválido! Precisa ter exatamente 11 números.")
        }
    } while (!cpfValido(cpf))

    println("Digite a idade: ")
    val idade = readln().toIntOrNull() ?: 0

    println("O que esse fornecedor fornece? ")
    val materialFornecido = readln()

    val fornecedor = Fornecedor(
        nomeFornecedor = nome,
        cpfFornecedor = cpf,
        idadeFornecedor = idade,
        materialFornecido = materialFornecido
    )

    val jpa = JPA()
    jpa.salvarFornecedor(fornecedor)
}

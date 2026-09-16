package sistema.pessoas

import pessoas.Cliente
import repositorio.JPA
import validacoes.cpfValido
import validacoes.lerBigDecimalValido
import validacoes.lerIntValido
import java.math.BigDecimal

fun cadastrarCliente() {

    println("Digite o nome do cliente: ")
    val nome = readln()

    var cpf: String
    do {
        println("Digite o CPF (somente números, 11 dígitos): ")
        cpf = readln()
        if (!cpfValido(cpf)) {
            println("CPF inválido! Precisa ter exatamente 11 números.")
        }
    } while (!cpfValido(cpf))

    val idade = lerIntValido("Digite a idade: ", 0, 120)

    println("O cliente tem dívidas em aberto? (s/n): ")
    // .equals(..., ignoreCase = true) compara o texto sem se importar com maiuscula/minuscula
    val dividasAbertas = readln().equals("s", ignoreCase = true)

    val parcelas = mutableListOf<BigDecimal>()   // lista vazia, vai ser preenchida so se precisar

    if (dividasAbertas) {   // so pergunta as parcelas SE o cliente tiver dividas
        val quantidade = lerIntValido("Quantas parcelas em aberto? ", 1, 100)

        for (i in 1..quantidade) {   // repete "quantidade" vezes, pedindo cada parcela
            parcelas.add(lerBigDecimalValido("Valor da parcela $i: "))
        }
    }

    val cliente = Cliente(
        nomeCliente = nome,
        cpfCliente = cpf,
        idadeCliente = idade,
        dividasAbertas = dividasAbertas,
        parcelasAPagar = parcelas
    )

    val jpa = JPA()
    jpa.salvarCliente(cliente)
}

package sistema.financeiro

import pessoas.Pessoa
import repositorio.JPA

fun registrarMovimentacao() {

    println("Nome de quem PAGOU: ")
    val nomePagador = readln()                                    // le o texto digitado
    val pagador = Pessoa(nome = nomePagador, cpf = "", idade = 0)  // cria uma Pessoa so com o nome, por enquanto

    println("Nome de quem RECEBEU: ")
    val nomeRecebedor = readln()
    val recebedor = Pessoa(nome = nomeRecebedor, cpf = "", idade = 0)

    println("Nome do RESPONSAVEL pela transação: ")
    val nomeResponsavel = readln()
    val responsavel = Pessoa(nome = nomeResponsavel, cpf = "", idade = 0)

    println("Valor: ")
    val valor = readln().toBigDecimal()   // transforma o texto digitado num numero decimal

    println("Motivo: ")
    val motivo = readln()

    // aqui é o momento EXATO em que a Movimentacao nasce:
    // chama receberConta() no objeto "recebedor", que decide como se comportar
    val movimentacao = recebedor.receberConta(
        valor = valor,
        pagador = pagador,
        motivo = motivo,
        responsavel = responsavel
    )

    val jpa = JPA()                          // abre a "ponte" com o banco
    jpa.salvarMovimentacao(movimentacao)     // salva a movimentacao criada
}

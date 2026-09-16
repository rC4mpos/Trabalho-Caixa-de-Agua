package sistema.financeiro

import financeiro.CaixaEmpresa
import pessoas.Pessoa
import repositorio.JPA
import validacoes.lerBigDecimalValido
import validacoes.lerIntValido

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

    val valor = lerBigDecimalValido("Valor: ")

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

    // Além de guardar o histórico, essa movimentação também precisa
    // refletir no caixa da empresa (é o que "controla o fluxo de caixa").
    val direcao = lerIntValido(
        "Esse valor ENTROU ou SAIU do caixa da empresa? (1-Entrou / 2-Saiu): ", 1, 2
    )

    if (direcao == 1) {
        CaixaEmpresa.caixa.depositar(valor)
    } else {
        CaixaEmpresa.caixa.sacar(valor)
    }

    println("Saldo atual do caixa: R$ ${CaixaEmpresa.caixa.saldo}")
}

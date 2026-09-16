package financeiro

import pessoas.Pessoa
import java.math.BigDecimal
import java.time.LocalDateTime

// Uma movimentação financeira: quem pagou, quem recebeu, o motivo,
// quando aconteceu e quem foi o responsável por registrar a operação.
class Movimentaçao(
    val valor: BigDecimal,
    val pagador: Pessoa,
    val recebedor: Pessoa,
    val motivo: String,
    val responsavel: Pessoa,
    val dataHora: LocalDateTime = LocalDateTime.now()
)

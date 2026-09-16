package pessoas

import financeiro.Movimentaçao
import java.math.BigDecimal

class Cliente(
    nomeCliente: String,
    cpfCliente: String,
    idadeCliente: Int,
    val dividasAbertas: Boolean,
    val parcelasAPagar : MutableList<BigDecimal>
) : Pessoa(
    nome = nomeCliente,
    cpf = cpfCliente,
    idade = idadeCliente){

    // Outro comportamento diferente do padrão: se o Cliente tem dívida em
    // aberto, avisa isso antes de registrar a movimentação normalmente.
    override fun receberConta(valor: BigDecimal, pagador: Pessoa, motivo: String, responsavel: Pessoa): Movimentaçao {
        if (dividasAbertas && parcelasAPagar.isNotEmpty()) {
            println("Atenção: $nome tem dívidas em aberto. Esse valor deveria quitar as parcelas antes de outra coisa.")
        }
        return super.receberConta(valor, pagador, motivo, responsavel)
    }
}

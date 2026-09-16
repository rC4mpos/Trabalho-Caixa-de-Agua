package pessoas

import financeiro.Movimentaçao
import java.math.BigDecimal

open class Pessoa (
    val nome: String,
    val cpf: String,
    val idade: Int
)//Criação da classe-mãe via Construtor
{
    // Comportamento padrão: qualquer Pessoa pode simplesmente receber um valor.
    // As subclasses podem sobrescrever isso com regras próprias (polimorfismo).
    open fun receberConta(valor: BigDecimal, pagador: Pessoa, motivo: String, responsavel: Pessoa): Movimentaçao {
        return Movimentaçao(
            valor = valor,
            pagador = pagador,
            recebedor = this,
            motivo = motivo,
            responsavel = responsavel
        )
    }
}

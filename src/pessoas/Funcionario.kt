package pessoas

import enumeradores.Setor
import financeiro.Movimentaçao
import java.math.BigDecimal

// Fica ENTRE Pessoa e Instalador na hierarquia.
// Se um dia precisar de um novo tipo de funcionário (ex: Estagiario),
// basta criar "class Estagiario(...) : Funcionario(...)" -- não precisa
// mexer em Pessoa nem nas outras classes que já existem.
open class Funcionario(
    nome: String,
    cpf: String,
    idade: Int,
    val salario: BigDecimal,
    val setor: Setor
) : Pessoa(nome, cpf, idade) {

    // Comportamento diferente do padrão de Pessoa: todo Funcionario que
    // recebe um valor, avisa de qual setor ele é. Isso é POLIMORFISMO:
    // mesmo nome de método (receberConta), comportamento diferente.
    override fun receberConta(valor: BigDecimal, pagador: Pessoa, motivo: String, responsavel: Pessoa): Movimentaçao {
        println("$nome (setor ${setor.name}) recebeu um pagamento de R$ $valor.")
        return super.receberConta(valor, pagador, motivo, responsavel)
    }
}

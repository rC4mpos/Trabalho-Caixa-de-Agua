package financeiro

import java.math.BigDecimal

class Caixa(
    saldoInicial: BigDecimal = BigDecimal.ZERO
) {
    // "private set" = qualquer código pode LER o saldo (caixa.saldo),
    // mas só esta classe pode ALTERAR ele. É isso que é encapsulamento:
    // ninguém de fora consegue fazer "caixa.saldo = -500" na unha.
    var saldo: BigDecimal = saldoInicial
        private set

    fun depositar(valor: BigDecimal) {
        require(valor > BigDecimal.ZERO) { "O valor precisa ser positivo." }
        saldo += valor
    }

    fun sacar(valor: BigDecimal): Boolean {
        require(valor > BigDecimal.ZERO) { "O valor precisa ser positivo." }

        if (valor > saldo) {
            println("Saldo insuficiente. Saldo atual: R$ $saldo")
            return false
        }

        saldo -= valor
        return true
    }
}

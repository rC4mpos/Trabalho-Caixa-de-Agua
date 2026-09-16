package validacoes

import java.math.BigDecimal

// Valida se um CPF tem exatamente 11 numeros, sem ponto e sem traco.
// Regex explicada simbolo por simbolo:
//   ^        -> marca o INICIO do texto
//   \d       -> significa "um digito de 0 a 9"
//   \d{11}   -> "exatamente 11 digitos seguidos"
//   $        -> marca o FIM do texto
// Ou seja: só aceita um texto que, do começo ao fim, tenha exatamente 11 números.
// Se tiver letra, ponto, traço, ou mais/menos de 11 números, dá falso.
fun cpfValido(cpf: String): Boolean {
    val regexCpf = Regex("^\\d{11}$")
    return regexCpf.matches(cpf)
}

// Fica pedindo o valor de novo ate o usuario digitar um numero decimal valido.
// Usa "OrNull" em vez de deixar o programa quebrar quando vem texto invalido.
fun lerBigDecimalValido(mensagem: String): BigDecimal {
    var valor: BigDecimal?
    do {
        println(mensagem)
        valor = readln().toBigDecimalOrNull()
        if (valor == null) {
            println("Valor inválido! Digite um número (ex: 10.50).")
        }
    } while (valor == null)
    return valor
}

// Igual a lerBigDecimalValido, mas para numeros com casas decimais tipo largura/altura.
fun lerDoubleValido(mensagem: String): Double {
    var valor: Double?
    do {
        println(mensagem)
        valor = readln().toDoubleOrNull()
        if (valor == null) {
            println("Valor inválido! Digite um número (ex: 1.5).")
        }
    } while (valor == null)
    return valor
}

// Fica pedindo um numero inteiro dentro de [min, max] ate o usuario acertar.
// Usado pra escolher opcoes de listas (cor, material, setor) sem estourar o indice.
fun lerIntValido(mensagem: String, min: Int, max: Int): Int {
    var valor: Int?
    do {
        println(mensagem)
        valor = readln().toIntOrNull()
        if (valor == null || valor < min || valor > max) {
            println("Opção inválida! Digite um número entre $min e $max.")
        }
    } while (valor == null || valor < min || valor > max)
    return valor
}

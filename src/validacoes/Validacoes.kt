package validacoes

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

package pessoas

import enumeradores.Habilidade
import enumeradores.Setor
import enumeradores.Turno
import java.math.BigDecimal

class Instalador (
    nome : String,
    cpf : String,
    idade : Int,
    salario : BigDecimal,
    val turno : Turno,
    val habilidade : Habilidade
) : Funcionario(
    nome = nome,
    cpf = cpf,
    idade = idade,
    salario = salario,
    setor = Setor.INSTALACAO
)
// Não precisa mais sobrescrever receberConta() aqui: o Instalador já
// herda o comportamento de Funcionario (que avisa o setor). Antes essa
// classe repetia o mesmo código de Pessoa à toa.

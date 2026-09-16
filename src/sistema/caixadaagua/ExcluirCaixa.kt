package sistema.caixadaagua

import repositorio.JPA
import validacoes.lerIntValido

fun excluirCaixa(){
    val jpa = JPA()
    jpa.listar()

    val id = lerIntValido("Digite o ID que deseja excluir: ", 1, Int.MAX_VALUE)
    jpa.excluir(id)

}

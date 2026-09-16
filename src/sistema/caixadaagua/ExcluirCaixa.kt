package sistema.caixadaagua

import repositorio.JPA

fun excluirCaixa(){
    val jpa = JPA()
    jpa.listar()

    println("Digite o ID que deseja excluir: ")
    val id = readln().toInt()
    jpa.excluir(id)

}

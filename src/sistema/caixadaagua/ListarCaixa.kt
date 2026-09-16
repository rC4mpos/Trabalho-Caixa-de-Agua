package sistema.caixadaagua

import repositorio.JPA

fun listarCaixa(){
    val jpa = JPA()   // abre a "ponte" com o banco
    jpa.listar()       // o JPA ja sabe buscar e imprimir todas as caixas cadastradas
}

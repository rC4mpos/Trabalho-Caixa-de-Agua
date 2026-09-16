package sistema.caixadaagua

import enumeradores.Cor
import enumeradores.Material
import produto.CaixaDaAgua
import repositorio.JPA
import validacoes.lerBigDecimalValido
import validacoes.lerDoubleValido
import validacoes.lerIntValido

fun editarCaixa() {

    val jpa = JPA()
    jpa.listar(
    )
    //Aqui é so um exemplo de um item
    //Depois faça de todos
    val id = lerIntValido("Digite uma caixa que deseje alterar: ", 1, Int.MAX_VALUE)


    println("digite a NOVA marca")
    val marca = readln()
    println("Digite o NOVO modelo")
    val modelo = readln()
    println("Digite o NOVO formato")
    val formato = readln()
    println("Digite o NOVO dimensão")
    val largura = lerDoubleValido("Digite o NOVO largura")
    val profundidade = lerDoubleValido("Digite o NOVO profundidade")
    val altura = lerDoubleValido("Digite o NOVO altura")
    val dimensao = mutableListOf(largura, altura, profundidade)

    println("Escolha a cor: ")
    Cor.entries.forEach { cor ->
        println("${cor.ordinal} - ${cor.name}")
    }
    val cor = lerIntValido("numero da cor NOVA: ", 0, Cor.entries.size - 1)

    Material.entries.forEach { material ->
        println("${material.ordinal} - ${material.name}")
    }
    val material = lerIntValido("Digite o numero do material NOVO: ", 0, Material.entries.size - 1)

    val preco = lerBigDecimalValido("Digite o NOVO preço: ")

    jpa.editar(
        CaixaDaAgua(
            marca = marca,
            modelo = modelo,
            formato = formato,
            dimensao = dimensao,
            preco = preco,
            cor = Cor.entries[cor],
            material = Material.entries[material]
        ), id
    )
}
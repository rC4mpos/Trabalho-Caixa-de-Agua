package sistema.caixadaagua

import enumeradores.Cor
import enumeradores.Material
import produto.CaixaDaAgua
import repositorio.JPA

fun editarCaixa() {

    val jpa = JPA()
    jpa.listar(
    )
    //Aqui é so um exemplo de um item
    //Depois faça de todos
    println("Digite uma caixa que deseje alterar: ")
    val id = readln().toInt()


    println("digite a NOVA marca")
    val marca = readln()
    println("Digite o NOVO modelo")
    val modelo = readln()
    println("Digite o NOVO formato")
    val formato = readln()
    println("Digite o NOVO dimensão")
    println("Digite o NOVO largura")
    val largura = readln().toDouble()
    println("Digite o NOVO profundidade")
    val profundidade = readln().toDouble()
    println("Digite o NOVO altura")
    val altura = readln().toDouble()
    val dimensao = mutableListOf(largura, altura, profundidade)

    println("Escolha a cor: ")
    Cor.entries.forEach { cor ->
        println("${cor.ordinal} - ${cor.name}")
    }
    println("numero da cor NOVA: ")
    val cor = readln().toInt()

    Material.entries.forEach { material ->
        println("${material.ordinal} - ${material.name}")
    }
    println("Digite o numero do material NOVO: ")
    val material = readln().toInt()

    println("Digite o NOVO preço: ")
    val preco = readln().toBigDecimal()

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
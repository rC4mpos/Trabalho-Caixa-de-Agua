package sistema.caixadaagua
import enumeradores.Cor
import enumeradores.Material
import produto.CaixaDaAgua
import repositorio.JPA
import validacoes.lerBigDecimalValido
import validacoes.lerDoubleValido
import validacoes.lerIntValido



fun cadastrarNovaCaixa() {

    println("Digite a marca: ")
    val marca = readln()

    println("Digite o modelo: ")
    val modelo = readln()

    val largura = lerDoubleValido("Digite a largura: ")
    val altura = lerDoubleValido("Digite a altura: ")
    val profundidade = lerDoubleValido("Digite a profundidade: ")
    //a dimensao é uma lista dos 3 valores acima
    val dimensao = mutableListOf<Double>(largura, altura, profundidade)

    println("Escolha a cor: ")
    Cor.entries.forEach { cor ->
        println("${cor.ordinal} - ${cor.name}")
    }
    val cor = lerIntValido("numero da cor: ", 0, Cor.entries.size - 1)

    Material.entries.forEach { material ->
        println("${material.ordinal} - ${material.name}")
    }
    val material = lerIntValido("Digite o numero do material: ", 0, Material.entries.size - 1)

    println("Escolha o formato: ")
    val formato = readln()

    val preco = lerBigDecimalValido("Digite o preco: ")

    val conexao = JPA() //Criar a variavel de conexao com o banco
    conexao.salvar(
        CaixaDaAgua(
            marca = marca,
            modelo = modelo,
            dimensao = dimensao,
            cor = Cor.entries[cor],
            material = Material.entries[material],
            formato = formato,
            preco = preco
        )
    )
    }

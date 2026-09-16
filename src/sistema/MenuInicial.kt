package sistema

import sistema.caixadaagua.cadastrarNovaCaixa
import sistema.caixadaagua.listarCaixa
import sistema.caixadaagua.editarCaixa
import sistema.caixadaagua.excluirCaixa
import sistema.financeiro.registrarMovimentacao
import sistema.pessoas.cadastrarFuncionario
import sistema.pessoas.cadastrarCliente
import sistema.pessoas.cadastrarFornecedor

//deve rodar eternamente, ate o usuario escolher sair
fun menuInicial() {
    do {
        println("0 - Sair")
        println("1 - Cadastrar Caixa De Água")
        println("2 - Editar Caixa De Água")
        println("3 - Listar Caixa De Água")
        println("4 - Excluir Caixa De Água")
        println("5 - Registrar Movimentaçao Financeira")
        println("6 - Cadastrar Funcionário")
        println("7 - Cadastrar Cliente")
        println("8 - Cadastrar Fornecedor")

        print("Escolha uma opção: ")
        // toIntOrNull() nunca quebra o programa: se o usuário digitar
        // "abc" em vez de um número, ele devolve null em vez de dar erro.
        val opcao: Int? = readln().toIntOrNull()

        when (opcao) {
            1 -> cadastrarNovaCaixa()
            2 -> editarCaixa()
            3 -> listarCaixa()
            4 -> excluirCaixa()
            5 -> registrarMovimentacao()
            6 -> cadastrarFuncionario()
            7 -> cadastrarCliente()
            8 -> cadastrarFornecedor()
            0 -> {
                println("Tchau Paraguaio")
                break
            }
            else -> println("Opção inválida, digite um número de 0 a 8.")
        }
    } while (true) //FIM DO DO-WHILE


} //FIM DA FUNÇAO

package repositorio

import produto.CaixaDaAgua
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import financeiro.Movimentaçao
import java.sql.Timestamp
import pessoas.Funcionario
import pessoas.Cliente
import pessoas.Fornecedor

class JPA(
    // banco: revisao
    // senha: postgres
    // user: postgres
    // porta: 5432

    val user: String = "postgres",
    val senha: String = "postgres",
    val url: String = "jdbc:postgresql://localhost:5432/revisao",
    var conexao: Connection? = null
) {

    // ==============================
    // CONECTAR COM O POSTGRESQL
    // ==============================
    fun conectar() {
        try {

            // Carregar driver
            Class.forName("org.postgresql.Driver")

            // Estabelecer conexão
            conexao = DriverManager.getConnection(url, user, senha)

            println("A conexão foi estabelecida!")

        } catch (e: SQLException) {

            println("Não foi possível conectar ao banco.")
            e.printStackTrace()

        } catch (e: ClassNotFoundException) {

            println("Driver do PostgreSQL não encontrado.")
            e.printStackTrace()
        }
    }

    // ==============================
    // SALVAR CAIXA D'ÁGUA
    // ==============================
    fun salvar(a: CaixaDaAgua) {

        println("Salvando...")

        try {

            conectar()

            val sql = """
                INSERT INTO caixadagua
                (marca, modelo, dimensao, cor, material, formato, preco)
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """.trimIndent()

            val stmt = conexao!!.prepareStatement(sql)

            // Preparar lista para Double Precision
            val doublePrecision = conexao!!.createArrayOf(
                "float8",
                a.dimensao.toTypedArray()
            )

            // Preparar as variáveis para o banco
            stmt.setString(1, a.marca)
            stmt.setString(2, a.modelo)
            stmt.setArray(3, doublePrecision)
            stmt.setString(4, a.cor.name)
            stmt.setString(5, a.material.name)
            stmt.setString(6, a.formato)
            stmt.setBigDecimal(7, a.preco)

            stmt.executeUpdate()

            stmt.close()
            conexao!!.close()

            println("Caixa d'água salva com sucesso!")

        } catch (e: SQLException) {

            println("Não salvou.")
            e.printStackTrace()
        }
    }

    // ==============================
    // LISTAR CAIXAS D'ÁGUA
    // ==============================
    fun listar() {

        try {

            conectar()

            val stmt = conexao!!.createStatement()

            val sql = "SELECT * FROM caixadagua"

            val metadados = stmt.executeQuery(sql)

            val resultado = metadados.metaData
            val tamanhoTabela = resultado.columnCount

            while (metadados.next()) {

                for (i in 1..tamanhoTabela) {

                    // Nome da coluna
                    val nomeColuna = resultado.getColumnName(i)

                    // Valor da coluna
                    val valorColuna = metadados.getObject(i)

                    println("$nomeColuna -> $valorColuna")
                }

                println("----------------------------------------------------")
            }

            stmt.close()
            conexao!!.close()

        } catch (e: SQLException) {

            println("Não foi possível listar as caixas.")
            e.printStackTrace()
        }
    }

    // ==============================
    // EDITAR CAIXA D'ÁGUA
    // ==============================
    fun editar(caixa: CaixaDaAgua, id: Int) {

        try {

            conectar()

            val sql = """
                UPDATE caixadagua
                SET preco = ?,
                    marca = ?,
                    modelo = ?,
                    formato = ?,
                    cor = ?,
                    material = ?,
                    dimensao = ?
                WHERE id = ?
            """.trimIndent()

            val stmt = conexao!!.prepareStatement(sql)

            // Preparar dimensão
            val doublePrecision = conexao!!.createArrayOf(
                "float8",
                caixa.dimensao.toTypedArray()
            )

            stmt.setBigDecimal(1, caixa.preco)
            stmt.setString(2, caixa.marca)
            stmt.setString(3, caixa.modelo)
            stmt.setString(4, caixa.formato)
            stmt.setString(5, caixa.cor.name)
            stmt.setString(6, caixa.material.name)
            stmt.setArray(7, doublePrecision)
            stmt.setInt(8, id)

            stmt.executeUpdate()

            stmt.close()
            conexao!!.close()

            println("Caixa d'água editada com sucesso!")

        } catch (e: SQLException) {

            println("Não foi possível editar a caixa.")
            e.printStackTrace()
        }
    }

    // ==============================
    // EXCLUIR CAIXA D'ÁGUA
    // ==============================
    fun excluir(id: Int) {

        try {

            conectar()

            val sql = "DELETE FROM caixadagua WHERE id = ?"

            val stmt = conexao!!.prepareStatement(sql)

            stmt.setInt(1, id)

            stmt.executeUpdate()

            stmt.close()
            conexao!!.close()

            println("Caixa d'água excluída com sucesso!")

        } catch (e: SQLException) {

            println("Não foi possível excluir a caixa.")
            e.printStackTrace()
        }
    }

    // ==============================
// SALVAR MOVIMENTAÇÃO
// ==============================
    fun salvarMovimentacao(m: Movimentaçao) {   // recebe uma Movimentacao pronta, guardada na caixinha "m"

        println("Salvando movimentação...")   // so um aviso na tela pra quem esta usando o programa

        try {   // "tenta" fazer o que vem a seguir; se der erro, o catch la embaixo pega

            conectar()   // abre a conexao com o banco (a mesma funcao usada no resto do arquivo)

            // texto do comando SQL. Os "?" sao "buracos" que a gente preenche depois
            val sql = """
            INSERT INTO movimentacoes
            (valor, pagador, recebedor, motivo, responsavel, data_hora)
            VALUES (?, ?, ?, ?, ?, ?)
        """.trimIndent()

            val stmt = conexao!!.prepareStatement(sql)   // prepara esse comando SQL pra ser executado

            stmt.setBigDecimal(1, m.valor)                        // preenche o 1o "?" com o valor
            stmt.setString(2, m.pagador.nome)                     // preenche o 2o "?" com o nome de quem pagou
            stmt.setString(3, m.recebedor.nome)                   // preenche o 3o "?" com o nome de quem recebeu
            stmt.setString(4, m.motivo)                           // preenche o 4o "?" com o motivo
            stmt.setString(5, m.responsavel.nome)                 // preenche o 5o "?" com o responsavel
            stmt.setTimestamp(6, Timestamp.valueOf(m.dataHora))   // preenche o 6o "?" com a data e hora

            stmt.executeUpdate()   // manda o comando SQL rodar de verdade la no banco

            stmt.close()          // fecha esse "comando preparado", ja usei, nao preciso mais
            conexao!!.close()     // fecha a conexao com o banco

            println("Movimentação salva com sucesso!")   // avisa na tela que deu tudo certo

        } catch (e: SQLException) {   // rede de seguranca: se algo der errado la em cima...

            println("Não foi possível salvar a movimentação.")
            e.printStackTrace()   // mostra os detalhes tecnicos do erro, pra gente descobrir o que foi
        }
    }

    // ==============================
    // SALVAR FUNCIONARIO
    // ==============================
    fun salvarFuncionario(f: Funcionario) {   // recebe um Funcionario (ou Instalador, que herda dele)

        println("Salvando funcionário...")

        try {

            conectar()

            // "?" na ordem: nome, cpf, idade, salario, setor
            val sql = """
                INSERT INTO funcionarios
                (nome, cpf, idade, salario, setor)
                VALUES (?, ?, ?, ?, ?)
            """.trimIndent()

            val stmt = conexao!!.prepareStatement(sql)

            stmt.setString(1, f.nome)
            stmt.setString(2, f.cpf)
            stmt.setInt(3, f.idade)
            stmt.setBigDecimal(4, f.salario)
            stmt.setString(5, f.setor.name)   // ".name" pega o texto do enum, ex: "FINANCEIRO"

            stmt.executeUpdate()

            stmt.close()
            conexao!!.close()

            println("Funcionário salvo com sucesso!")

        } catch (e: SQLException) {

            println("Não foi possível salvar o funcionário.")
            e.printStackTrace()
        }
    }

    // ==============================
    // SALVAR CLIENTE
    // ==============================
    fun salvarCliente(c: Cliente) {

        println("Salvando cliente...")

        try {

            conectar()

            val sql = """
                INSERT INTO clientes
                (nome, cpf, idade, dividas_abertas, parcelas_a_pagar)
                VALUES (?, ?, ?, ?, ?)
            """.trimIndent()

            val stmt = conexao!!.prepareStatement(sql)

            // parcelasAPagar é uma lista (MutableList), igual "dimensao" da caixa d'agua.
            // Por isso precisa converter pro formato de "array" que o Postgres entende.
            val arrayParcelas = conexao!!.createArrayOf(
                "numeric",
                c.parcelasAPagar.toTypedArray()
            )

            stmt.setString(1, c.nome)
            stmt.setString(2, c.cpf)
            stmt.setInt(3, c.idade)
            stmt.setBoolean(4, c.dividasAbertas)   // true ou false, direto
            stmt.setArray(5, arrayParcelas)

            stmt.executeUpdate()

            stmt.close()
            conexao!!.close()

            println("Cliente salvo com sucesso!")

        } catch (e: SQLException) {

            println("Não foi possível salvar o cliente.")
            e.printStackTrace()
        }
    }

    // ==============================
    // SALVAR FORNECEDOR
    // ==============================
    fun salvarFornecedor(f: Fornecedor) {

        println("Salvando fornecedor...")

        try {

            conectar()

            val sql = """
                INSERT INTO fornecedores
                (nome, cpf, idade, material_fornecido)
                VALUES (?, ?, ?, ?)
            """.trimIndent()

            val stmt = conexao!!.prepareStatement(sql)

            stmt.setString(1, f.nome)
            stmt.setString(2, f.cpf)
            stmt.setInt(3, f.idade)
            stmt.setString(4, f.materialFornecido)

            stmt.executeUpdate()

            stmt.close()
            conexao!!.close()

            println("Fornecedor salvo com sucesso!")

        } catch (e: SQLException) {

            println("Não foi possível salvar o fornecedor.")
            e.printStackTrace()
        }
    }
}
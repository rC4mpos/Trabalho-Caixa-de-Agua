package pessoas

class Fornecedor(
    nomeFornecedor: String,
    cpfFornecedor: String,
    idadeFornecedor: Int,
    val materialFornecido: String
) : Pessoa(
    nome = nomeFornecedor,
    cpf = cpfFornecedor,
    idade = idadeFornecedor
)

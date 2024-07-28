package kauan.com.br.models

import kauan.com.br.entities.Pessoa
import kauan.com.br.util.getDateTimeFormatterDefault
import kotlinx.serialization.Serializable

@Serializable
class PessoaResponse(
    val id: String,
    val name: String,
    val cpf: String,
    val dateCad: String
)

fun Pessoa.toPessoaResponse() = PessoaResponse(
    id = id.toString(),
    name = name,
    cpf = cpf,
    dateCad = dateCad.let { dateCad.format(getDateTimeFormatterDefault()) } ?: ""
)

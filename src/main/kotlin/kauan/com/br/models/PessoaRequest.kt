package kauan.com.br.models

import kauan.com.br.entities.Pessoa
import kotlinx.serialization.Serializable

@Serializable
class PessoaRequest (
    private val name: String,
    private val cpf: String
){

    fun toPessoa() = Pessoa(
        name = name,
        cpf = cpf
    )
}
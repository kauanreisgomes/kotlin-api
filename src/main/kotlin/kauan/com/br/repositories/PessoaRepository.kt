package kauan.com.br.repositories

import kauan.com.br.database.dao.PessoaDao
import kauan.com.br.entities.Pessoa
import kauan.com.br.models.PessoaResponse
import kauan.com.br.models.toPessoaResponse
import java.util.*

class PessoaRepository(
    private val dao: PessoaDao = PessoaDao()
){

    suspend fun findPessoas() = dao.findAll().map { p -> p.toPessoaResponse() }.toList()

    suspend fun save(pessoa: Pessoa) = dao.savePessoa(pessoa)?.toPessoaResponse()

}
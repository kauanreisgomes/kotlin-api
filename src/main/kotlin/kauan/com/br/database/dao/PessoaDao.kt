package kauan.com.br.database.dao

import kauan.com.br.entities.Pessoa
import kauan.com.br.entities.Pessoas
import kauan.com.br.util.getZoneOffSetDefault
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class PessoaDao {
    suspend fun findAll(): List<Pessoa> = dbQuery {
        Pessoas.selectAll().map {
            Pessoa(
                id = it[Pessoas.id],
                name = it[Pessoas.name],
                cpf = it[Pessoas.cpf],
                dateCad = it[Pessoas.dateCad].atOffset(getZoneOffSetDefault())
            )
        }
    }

    suspend fun savePessoa(pessoa: Pessoa) = dbQuery {
        Pessoas.insert {
            it[id] = pessoa.id
            it[name] = pessoa.name
            it[cpf] = pessoa.cpf
            it[dateCad] = pessoa.dateCad.toLocalDateTime()
        }.resultedValues?.singleOrNull()?.let {
            Pessoa(
                id = it[Pessoas.id],
                name = it[Pessoas.name],
                cpf = it[Pessoas.cpf],
                dateCad = it[Pessoas.dateCad].atOffset(getZoneOffSetDefault())
            )
        }
    }
}


suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO){ block() }
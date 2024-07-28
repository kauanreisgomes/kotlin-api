package kauan.com.br.entities

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.OffsetDateTime
import java.util.*


class Pessoa(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val cpf: String,
    val dateCad: OffsetDateTime = OffsetDateTime.now()
)

object Pessoas: Table(){
    val id = uuid("id").autoGenerate()
    val name = varchar("name", 255)
    val cpf = varchar("cpf", 11)
    val dateCad = datetime("date_cad")

    override val primaryKey = PrimaryKey(id)
}
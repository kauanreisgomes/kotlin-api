package kauan.com.br.database

import kauan.com.br.entities.Pessoas
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(jdcbUrl: String = "jdbc:h2:file:./build/myh2file", driverClass: String = "org.h2.Driver"){
        Database.connect(jdcbUrl, driverClass)
        transaction {
                SchemaUtils.create(Pessoas)
        }
    }

}
package kauan.com.br.plugins

import kauan.com.br.database.DatabaseFactory
import kauan.com.br.models.PessoaRequest
import kauan.com.br.models.PessoaResponse
import kauan.com.br.repositories.PessoaRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    DatabaseFactory.init()
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
    val repository = PessoaRepository()

    routing {
        get("/pessoa") {
            call.respond<List<PessoaResponse>>(repository.findPessoas())
        }
        post("/pessoa") {
            val request = call.receive<PessoaRequest>()
            repository.save(request.toPessoa())?.let {
                pessoa -> call.respond(status = HttpStatusCode.Created, pessoa)
            } ?: call.respond(status = HttpStatusCode.BadRequest, "Error: Pessoa could not be created")
        }
    }
}

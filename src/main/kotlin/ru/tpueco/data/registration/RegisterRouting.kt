package ru.tpueco.data.registration

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import ru.tpueco.cash.InMemoryCash
import ru.tpueco.cash.TokenCash
import java.util.*

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
           val loginReceive = call.receive<RegisterReceiveRemoteModel>()

                val token =  UUID.randomUUID().toString()
            InMemoryCash.userList.add(loginReceive)
                InMemoryCash.token.add(TokenCash(loginReceive.email, token))
                call.respond(RegisterResponseRemoteModel(token))
                return@post
            }
        }

}

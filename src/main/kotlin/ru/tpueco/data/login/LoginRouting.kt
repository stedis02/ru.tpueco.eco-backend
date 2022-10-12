package ru.tpueco.data.login

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import ru.tpueco.cash.InMemoryCash
import ru.tpueco.cash.TokenCash
import java.util.*

fun Application.configureLoginRouting() {

    routing {
        post ("/login") {
           val loginReceive = call.receive<LoginReceiveRemoteModel>()
            if(InMemoryCash.userList.map { it.email }.contains(loginReceive.email)){
                val token =  UUID.randomUUID().toString()
                InMemoryCash.token.add(TokenCash(loginReceive.email, token))
                call.respond(LoginResponseRemoteModel(token))
                return@post
            }
        }
    }
}

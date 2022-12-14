package ru.tpueco.data.registration

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import ru.tpueco.cash.InMemoryCash
import ru.tpueco.cash.TokenCash
import ru.tpueco.data.mail.MailReceiver
import java.util.*

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
           val loginReceive = call.receive<RegisterReceiveRemoteModel>()

            val mr = MailReceiver()
            val mailResponse = mr.sortMail(loginReceive.email, loginReceive.password)
                val token =  UUID.randomUUID().toString()
            InMemoryCash.userList.add(loginReceive)
                InMemoryCash.token.add(TokenCash(loginReceive.email, token))
                call.respond(mailResponse)
                return@post
            }
        }

}

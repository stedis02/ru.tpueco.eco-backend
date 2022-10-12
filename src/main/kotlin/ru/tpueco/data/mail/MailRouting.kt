package ru.tpueco.data.mail

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import ru.tpueco.data.login.LoginReceiveRemoteModel

fun Application.configureMailRouting() {

    routing {
        post("/mail") {
           val loginReceive = call.receive<LoginReceiveRemoteModel>()
            val mr = MailReceiver()
val mailResponse = mr.sortMail(loginReceive.email, loginReceive.password)


                call.respond(mailResponse)
                return@post
            }
        }

}

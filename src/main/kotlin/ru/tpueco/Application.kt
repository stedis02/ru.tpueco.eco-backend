package ru.tpueco

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.tpueco.data.login.configureLoginRouting
import ru.tpueco.data.mail.configureMailRouting
import ru.tpueco.data.registration.configureRegisterRouting
import ru.tpueco.plugins.*

fun main() {
    embeddedServer(Netty,port = System.getenv("PORT").toInt()) {
        configureSerialization()
        configureRouting()
        configureMailRouting()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}

package ru.tpueco.cash

import ru.tpueco.data.registration.RegisterReceiveRemoteModel

data class TokenCash( val login: String, val token: String)

object InMemoryCash {
    val userList : MutableList<RegisterReceiveRemoteModel> = mutableListOf()
    val token: MutableList<TokenCash> = mutableListOf()
}
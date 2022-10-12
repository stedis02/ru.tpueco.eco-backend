package ru.tpueco.data.registration

@kotlinx.serialization.Serializable
data class RegisterReceiveRemoteModel(
    val login: String,
    val email: String,
    val password: String
)

@kotlinx.serialization.Serializable
data class RegisterResponseRemoteModel(val token: String)
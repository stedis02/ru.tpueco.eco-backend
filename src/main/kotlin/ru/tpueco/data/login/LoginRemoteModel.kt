package ru.tpueco.data.login


@kotlinx.serialization.Serializable
data class LoginReceiveRemoteModel(val email : String, val password : String) {
}

@kotlinx.serialization.Serializable
data class LoginResponseRemoteModel(val token : String) {
}
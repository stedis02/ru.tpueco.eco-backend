package ru.tpueco.data.mail

@kotlinx.serialization.Serializable
data class Message(
    var from: String = "",
    var header: String = "",
    var Text: String?,
    val date: MessageDate
)

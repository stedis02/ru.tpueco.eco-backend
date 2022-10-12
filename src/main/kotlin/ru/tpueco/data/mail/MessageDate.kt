package ru.tpueco.data.mail

import java.time.DayOfWeek
@kotlinx.serialization.Serializable
data class MessageDate(
    var year: String?,
    var month: String?,
    var day: String?,
    var dayOfWeek: String?,
    var time: String?
)

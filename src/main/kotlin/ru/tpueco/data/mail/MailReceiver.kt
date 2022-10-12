package ru.tpueco.data.mail

class MailReceiver {

    private var findGroup: Boolean = false

    companion object {
        var messageGroups: MutableList<MutableList<Message>> =
            mutableListOf()
    }

     fun sortMail(email: String, password: String) : MutableList<MutableList<Message>>  {

        var i = 0
        for (message in Mailer.receive("letter.tpu.ru", 993, email, password)
            ?.reversed()!!) {
            if (i > 200) break
            val newMessage = MessageParser.parseMessage(message)
            findGroup = false
            addMessageToGroupWithSameAddress(newMessage)
            addMessageToNewGroupIfNotFound(newMessage)
            i++
        }
        return  messageGroups
    }

    private fun addMessageToGroupWithSameAddress(message: Message) {
        for (group in messageGroups) {
            if (group.isNotEmpty()) {
                if (group[0].from == message.from) {
                    group.add(message)
                    findGroup = true
                    break
                }
            }
        }
    }

    private fun addMessageToNewGroupIfNotFound(message: Message) {
        if (!findGroup) {
            val newGroup: MutableList<Message> =
                mutableListOf()
            newGroup.add(message)
            messageGroups.add(newGroup)
        }
    }




}
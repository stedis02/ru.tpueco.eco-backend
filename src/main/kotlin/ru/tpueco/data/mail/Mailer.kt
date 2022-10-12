package ru.tpueco.data.mail


import java.util.*
import javax.mail.*
import javax.mail.Message
import javax.mail.internet.*


object Mailer
{
    var debug = true

    var connectionTimeout = 1000*60
    var ioTimeout = 1000*60*10


    fun send(
        host: String,
        port: Int,
        user: String,
        pass: String,
        to: String,
        subject: String,
        body_text: String? = null,
        body_html: String? = null
    ) {
        val session = getSession(user, pass, "smtps")

        val msg = MimeMessage(session)
        msg.setFrom(InternetAddress(user))
        msg.setRecipients(javax.mail.Message.RecipientType.TO, to)
        msg.subject = subject
        msg.sentDate = Date()

        when {
            body_text != null && body_html != null -> {
                val multipart = MimeMultipart("alternative")
                multipart.addBodyPart(MimeBodyPart().apply { setContent(body_text, "text/plain; charset=utf-8") })
                multipart.addBodyPart(MimeBodyPart().apply { setContent(body_html, "text/html; charset=utf-8") })
                msg.setContent(multipart)
            }
            body_text != null -> msg.setContent(body_text, "text/plain; charset=utf-8")
            body_html != null -> msg.setContent(body_html, "text/html; charset=utf-8")
        }

        val transport = session.getTransport("smtps")
        transport.connect(host, port, user, pass)
        transport.sendMessage(msg, msg.allRecipients)
        transport.close()
    }

    fun receive(
        host: String,
        port: Int,
        user: String,
        pass: String,
        folderName: String = "INBOX",
        limit: Int = Int.MAX_VALUE
    ): Array<out Message>? {
        val session = getSession(user, pass, "imaps")

        val store = session.getStore("imaps")
        store.connect(host, port, user, pass)

        val folder = store.getFolder(folderName)
        folder.open(Folder.READ_ONLY)

        val count = folder.messageCount
        return folder.getMessages(Math.max(count-limit+1, 1), count)
    }



    private fun getSession(user: String, pass: String, protocol: String): Session {
        val props = Properties()

        if (debug)
            props.put("mail.debug", debug)

        props.put("mail.$protocol.connectiontimeout", connectionTimeout)
        props.put("mail.$protocol.timeout", ioTimeout)

        return Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication() = PasswordAuthentication(user, pass)
        })
    }
}

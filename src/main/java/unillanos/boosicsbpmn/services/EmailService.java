package unillanos.boosicsbpmn.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import unillanos.boosicsbpmn.interfaces.IEmailService;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
@Slf4j
public class EmailService implements IEmailService {

    @Value("${mail.sender.address}")
    private String emailSender;

    @Value("${mail.sender.pass}")
    private String emailPass;

    @Value("${mail.fdqn}")
    private String confirmationPath;

    @Value("${mail.sender.host}")
    private String host;

    @Value("${mail.sender.port}")
    private String port;

    @Override
    public void sendEmailConfirmation(String recipient, int code) throws MessagingException {
        // Configura las propiedades para la conexión al servidor de correo de Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(emailSender, emailPass);
                    }
                });

        String subject = "Correo de confirmación Boosics";
        String content = """
                Hola! Te has registrado en Boosics, ingresa al siguiente link para confirmar tu cuenta.                  \s
                """ + "Link: " + confirmationPath + code;
        // Crea un mensaje de correo electrónico
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailSender)); // Cambia "your_email@gmail.com" por tu dirección de correo electrónico de Gmail
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(content);

        // Envía el mensaje
        Transport.send(message);
        log.info("Se ha enviado el correo de confirmación a: " + recipient);
    }

}

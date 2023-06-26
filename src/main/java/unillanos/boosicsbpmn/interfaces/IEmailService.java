package unillanos.boosicsbpmn.interfaces;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface IEmailService {

    void sendEmailConfirmation(String recipient, int code) throws MessagingException;

}

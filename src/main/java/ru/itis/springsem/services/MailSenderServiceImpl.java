package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
/*import ru.itis.springsem.model.MailMessage;*/

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/*@Service
public class MailSenderServiceImpl implements MailSenderService {
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendMail(MailMessage mailMessage) {
        new Thread(() -> {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(mimeMessage, true);
                helper.setTo(mailMessage.getMailTo());
                helper.setSubject(mailMessage.getSubject());
                helper.setText(mailMessage.getText(), true);
            } catch (MessagingException e) {
                System.out.println("Error while sending message");
                throw new IllegalArgumentException(e);
            }
            javaMailSender.send(mimeMessage);
        }).start();
    }
}*/

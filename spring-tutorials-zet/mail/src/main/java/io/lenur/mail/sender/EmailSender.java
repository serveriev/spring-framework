package io.lenur.mail.sender;

import io.lenur.mail.dto.Message;
import io.lenur.mail.mapper.MailMessageMapper;
import io.lenur.mail.property.MailProperties;
import org.apache.logging.log4j.LogManager;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmailSender implements Notification {
    private static final Logger LOGGER = LogManager.getLogger(EmailSender.class);

    private final String from;
    private final JavaMailSender javaMailSender;

    public EmailSender(
            JavaMailSender javaMailSender,
            MailProperties properties
    ) {
        this.javaMailSender = javaMailSender;
        this.from = properties.getFrom();
    }

    @Override
    public boolean send(Message message) {
        SimpleMailMessage mailMessage = MailMessageMapper.map(message);
        mailMessage.setFrom(this.from);

        try {
            javaMailSender.send(mailMessage);
        } catch (MailException exception) {
            LOGGER.error("There is something wrong in the sending email. Message: {}, Message body {}",
                    exception.getMessage(),
                    message);

            return false;
        }

        return true;
    }
}

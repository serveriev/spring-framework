package io.lenur.mail.mapper;

import io.lenur.mail.dto.Message;
import org.springframework.mail.SimpleMailMessage;

import java.util.Objects;

public class MailMessageMapper {
    public static SimpleMailMessage map(Message message) {
        Objects.requireNonNull(message);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(message.getEmail());
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getMessage());

        return mailMessage;
    }
}

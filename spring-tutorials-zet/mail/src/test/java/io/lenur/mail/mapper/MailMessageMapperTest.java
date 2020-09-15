package io.lenur.mail.mapper;

import io.lenur.mail.dto.Message;
import org.junit.jupiter.api.Test;

import org.springframework.mail.SimpleMailMessage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MailMessageMapperTest {
    @Test
    public void whenPassNulMessageThenCatchNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            MailMessageMapper.map(null);
        });
    }

    @Test
    public void mapMailMessage() {
        Message message = new Message("test@example.com", "hello test", "test body");
        SimpleMailMessage mailMessage = MailMessageMapper.map(message);

        String[] to = mailMessage.getTo();
        assertNotNull(to);
        assertEquals("test@example.com", to[0]);
        assertEquals("hello test", mailMessage.getSubject());
        assertEquals("test body", mailMessage.getText());
    }
}

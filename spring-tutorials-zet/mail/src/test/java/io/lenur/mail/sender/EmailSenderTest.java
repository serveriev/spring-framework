package io.lenur.mail.sender;

import io.lenur.mail.dto.Message;
import io.lenur.mail.mapper.MailMessageMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@AutoConfigureMockMvc
@SpringBootTest
public class EmailSenderTest {
    private static final Message MESSAGE;
    private static final SimpleMailMessage MAIL_MESSAGE;

    static {
        MESSAGE = new Message("test@example.com", "hello test", "test body");
        MAIL_MESSAGE = MailMessageMapper.map(MESSAGE);
        MAIL_MESSAGE.setFrom("noreply@example.com");
    }

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private EmailSender emailSender;

    @Test
    public void shouldFailSendingMessage() {
        doThrow(new SendMailException("")).when(this.javaMailSender).send(MAIL_MESSAGE);
        assertFalse(this.emailSender.send(MESSAGE));
    }

    @Test
    public void shouldSendMessage() {
        doNothing().when(this.javaMailSender).send(MAIL_MESSAGE);
        assertTrue(this.emailSender.send(MESSAGE));
    }
}

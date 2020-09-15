package io.lenur.mail.sender;

import org.springframework.mail.MailException;

public class SendMailException extends MailException {
    public SendMailException(String msg) {
        super(msg);
    }

    public SendMailException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

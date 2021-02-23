package com.decipherzone.mailutil.service.impl;

import com.decipherzone.mailutil.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EmailService")
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    @Qualifier("getJavaMailSender")
    private JavaMailSender emailSender;
    @Value("${mail.sender}")
    private String mailSender;

    public boolean sendSimpleMessage(List<String> mails1, String subject, String text) {
        try {
            String[] mails = mails1.toArray(new String[mails1.size()]);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailSender);
            message.setTo(mails);
            message.setSubject(subject);
            message.setText(text);
            return this.sendMail(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return this.sendMail(message);
    }

    @Override
    public boolean sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        return false;
    }

    private boolean sendMail(SimpleMailMessage message) {
        try {
            emailSender.send(message);
            return true;
        } catch (MailException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception);
        }
    }
}

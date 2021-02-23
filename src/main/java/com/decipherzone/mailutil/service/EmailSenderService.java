package com.decipherzone.mailutil.service;

import java.util.List;

public interface EmailSenderService {
    boolean sendSimpleMessage(List<String> to,
                              String subject,
                              String text);
    boolean sendSimpleMessage(String to,
                              String subject,
                              String text);
    boolean sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
}

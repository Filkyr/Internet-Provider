package by.bsuir.service;

import java.util.List;

public interface MailService {
    void sendEmail(List<String> receivers, String title, String content);
}

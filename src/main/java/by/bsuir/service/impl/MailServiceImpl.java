package by.bsuir.service.impl;

import by.bsuir.service.MailService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Log4j
@Service("mail")
public class MailServiceImpl implements MailService {
    private static final String username = "internet.project.rppba@gmail.com";
    private static final String password = "FXBvqy47382";
    private Session session;

    public MailServiceImpl(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    @Override
    public void sendEmail(List<String> receivers, String title, String content) {
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            InternetAddress[] addr = new InternetAddress[receivers.size()];
            for(int i = 0; i < receivers.size(); i++){
                addr[i] = new InternetAddress(receivers.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, addr);
            message.setContent(message(title, content), "text/html; charset=UTF-8");

            Transport.send(message);
        } catch(MessagingException e){
            log.error("Users don't get email", e);
        }
    }

    private String message(String title, String text){
        return "<table width='100%'><tr><td><table width='600' align='center' bgcolor='#FAFAFA'>" +
                "<tr><td><table align='center' bgcolor='#E6E6E6'><tr><td width='600' bgcolor='#E6E6E6'><h1>" +
                title +
                "</h1></td></tr></table></td></tr><tr><td><table cellpadding='0' cellspacing='0' align='center'>" +
                "<tr><td width='570'><p>" +
                text +
                "</p></td></tr></table></td></tr></table></td></tr></table>";
    }
}

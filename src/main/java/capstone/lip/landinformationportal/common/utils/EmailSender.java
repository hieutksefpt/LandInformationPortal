/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.common.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import capstone.lip.landinformationportal.common.entity.Feedback;

/**
 *
 * @author AnhHao
 */
@Component
public class EmailSender {

    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.smtp.auth}")
    private String auth;
    @Value("${mail.smtp.starttls.enable}")
    private String enable;
    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.port}")
    private String port;

    public void sendMailChangePassword(String to, String newPassword) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", enable);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setContent(message, "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Mail Reset Password");
            message.setText("Dear Mr/Mrs, \nLand Information Portal system notify you about reset your password \nYour new password is: " + newPassword);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMailFeedback(Feedback feedback) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", enable);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setContent(message, "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(feedback.getUser().getEmail()));
            message.setSubject("Reply Feedback");

            String templateString = "Thank you so much for using and feedback to Land Information System.\n"
                    + "About your feedback, this is our answer to you: " + feedback.getFeedbackAdminReply() + "\n"
                    + "Best Regards\nLand Information System";

            message.setText(templateString);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone.lip.landinformationportal.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author AnhHao
 */
public class EmailSender {

	@Value("${mail.username}")
	private static String username;
	@Value("${mail.password}")
	private static String password;
	@Value("${mail.smtp.auth}")
	private static String auth;
	@Value("${mail.smtp.starttls.enable}")
	private static String enable;
	@Value("${mail.smtp.host}")
	private static String host;
	@Value("${mail.smtp.port}")
	private static String port;
	
    public static void sendMailChangePassword(String to, String newPassword) {
        
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
}

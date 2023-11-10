package com.example.service.notification.configuration;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendMailBySite {

    public static void main(String[] args) {

        String host="smtp.gmail.com";
        final String user="";//change accordingly
        final String password="";//change accordingly

        String to="aveljic9621rn@raf.rs";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("javatpoint");
            String msg="This is simple program of sending email using JavaMail API";
            String url="https://www.gledajcrtace.xyz/";
            String content="<a href='"+url+"'>"+url+"</a>";
            message.setContent(msg+" "+content,"text/html; charset=utf-8");


            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }
}

package com.ISA.service.implementation;

import com.ISA.domain.model.User;
import com.ISA.service.definition.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

@Service
public class EmailServiceImpl implements EmailService {


    public void sendEmailForRegistration(User user)
    {
        // Sender's email ID needs to be mentioned
        String from = "PatientServicePSWFirma1@gmail.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.username", "PatientServicePSWFirma1@gmail.com");
        properties.setProperty("mail.smtp.password", "PSW!1234");
        properties.setProperty("mail.smtp.port", "465");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

            // Set Subject: header field
            message.setSubject("Registration activation");

            // Now set the actual message
            message.setText("http://localhost:8080/users/acivation?token=" + user.getRegistrationToken());

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

package com.alejandro.createUser.service;

import com.alejandro.createUser.domain.Person;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService{
    
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    public void sendVerificationEmail(Person person, String siteURL) throws MessagingException, UnsupportedEncodingException{
        
        String subject = "Please verify your registration";
        String senderName = "Canchapp";
        String mailContent="<h2>Hello!, " + person.getFullName() + "</h2>";
        mailContent+= "<p>Your username is: " + person.getUsername() + "</p>";
        mailContent+= "<p>Click the link below to validate your account</p>";
        
        String verifyURL = siteURL + "/verify?code=" + person.getVerificationCode();
        
        mailContent+= "<h3><a href=\"" + verifyURL + "\"> VERIFY </a></h3>";
        
        mailContent += "<p>Thanks, <strong>Canchapp</strong>";
        
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("alejandro.nahuel.15817@gmail.com", senderName);
        helper.setTo(person.getEmail());
        helper.setSubject(subject);
        
        helper.setText(mailContent, true);
        
        
        mailSender.send(message);        
    }
    
    
}

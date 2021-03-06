package com.mycompany.siemproject.event;

import com.mycompany.siemproject.model.User;
import com.mycompany.siemproject.services.RegistrationService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationEvent> {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.username}")
    private String username;
    
    @Value("${app.url}")
    private String appUrl;

    @Override
    public void onApplicationEvent(OnRegistrationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        registrationService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        
        
        String confirmationUrl
                = appUrl + "/api/registration?token=" + token;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(username);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("\r\n" + confirmationUrl + "\n\n");
        mailSender.send(email);
    }

}

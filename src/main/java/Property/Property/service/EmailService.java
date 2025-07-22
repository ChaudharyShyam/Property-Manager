package Property.Property.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import Property.Property.entity.ContactForm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

   
    private final JavaMailSender mailSender;

    public void sendEmail(ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("chaudharyshyam6006@gmail.com"); 
        message.setSubject("New Contact Form Submission: " + contactForm.getSubject());
        message.setText("Name: " + contactForm.getName() + "\n" +
                        "Email: " + contactForm.getEmail() + "\n" +
                        "Message:\n" + contactForm.getMessage());

        mailSender.send(message);
    }
}

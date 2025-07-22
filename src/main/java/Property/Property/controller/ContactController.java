package Property.Property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import Property.Property.entity.ContactForm;
import Property.Property.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContactController {


    private final EmailService emailService;

    
    @GetMapping("/user/home/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "user/contact"; 
    }
    
    @PostMapping("/user/home/contact")
    public String submitContactForm(@Valid ContactForm contactForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/contact";
        }
        // Send email
        emailService.sendEmail(contactForm);
        // Success message
        model.addAttribute("success", "Thank you! Your message has been sent successfully.");
        return "user/contact";
    }
}

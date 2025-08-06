package com.sandeepkonda.portfolio.controller;

import com.sandeepkonda.portfolio.model.Contact;
import com.sandeepkonda.portfolio.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("contact", new Contact());
        return "index";
    }
    
    @PostMapping("/contact")
    public String submitContact(@Valid Contact contact, 
                              BindingResult bindingResult, 
                              Model model, 
                              RedirectAttributes redirectAttributes) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("contact", contact);
            model.addAttribute("showContactForm", true);
            return "index";
        }
        
        try {
            boolean emailSent = emailService.sendContactEmail(contact);
            
            if (emailSent) {
                logger.info("Contact form submitted successfully: {}", contact.getEmail());
                redirectAttributes.addFlashAttribute("successMessage", 
                    "Message sent successfully! I'll get back to you soon.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", 
                    "Sorry, there was an error sending your message. Please try again or contact me directly.");
            }
            
        } catch (Exception e) {
            logger.error("Error processing contact form submission", e);
            redirectAttributes.addFlashAttribute("errorMessage", 
                "Oops! There was a problem sending your message. Please try again.");
        }
        
        return "redirect:/#contact";
    }
    
    @GetMapping("/health")
    public String health() {
        return "health";
    }
}

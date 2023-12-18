package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Contact;
import org.java.spring.db.serv.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @GetMapping("/contacts")
    public String getContacts(Model model) {
        
        List<Contact> contacts = contactService.findAll();
        
        model.addAttribute("contacts", contacts);
        
        return "contacts";
    }
    
    @GetMapping("/contacts/{id}")
    public String getContact(Model model, @PathVariable int id) {
        
        Contact contact = contactService.findById(id);
        
        model.addAttribute("contact", contact);
        
        return "contact";
    }
    
    @PostMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable int id) {
        
        Contact contact = contactService.findById(id);
        
        contactService.delete(contact);
        
        System.out.println(contact);
        
        return "redirect:/contacts";
    }
}
package org.java.spring.restcontroller;

import org.java.spring.db.pojo.Contact;
import org.java.spring.db.serv.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactRestController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Contact contact) {

                contactService.save(contact);
            return new ResponseEntity<>("Messaggio inviato con successo!", HttpStatus.OK);
    }
}
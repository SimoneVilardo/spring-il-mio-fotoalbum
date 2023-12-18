package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Contact;
import org.java.spring.db.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll() {

        return contactRepository.findAll();
    }
    public Contact findById(int id) {

        return contactRepository.findById(id).get();
    }
    public void save(Contact contact) {

        contactRepository.save(contact);
    }
    public void delete(Contact contact) {

        contactRepository.delete(contact);
    }
}

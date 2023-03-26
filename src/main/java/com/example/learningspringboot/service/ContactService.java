package com.example.learningspringboot.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.learningspringboot.model.Contact;

@Service
public class ContactService {
    private Logger log = LoggerFactory.getLogger(ContactService.class);
    /**
     * Save Contact Detail in DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessage(Contact contact) {
        boolean isSave = true;
        log.info("Name: " + contact.getName());
        log.info("Mobile number: " + contact.getMobileNum());
        log.info("Email: " + contact.getEmail());
        log.info("Subject: " + contact.getSubject());
        log.info("Message: " + contact.getMessage());
        return isSave;
    }
}

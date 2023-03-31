package com.example.learningspringboot.service;
import org.springframework.stereotype.Service;

import com.example.learningspringboot.model.Contact;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {
    /**
     * Save Contact Detail in DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessage(Contact contact) {
        boolean isSave = true;
        log.info("Contact" + contact.toString());
        return isSave;
    }
}

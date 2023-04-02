package com.example.learningspringboot.service;
import com.example.learningspringboot.constants.EazySchoolConstants;
import com.example.learningspringboot.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningspringboot.model.Contact;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Save Contact Detail in DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSave = true;
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMessage(contact);
        isSave = result > 0;
        return isSave;
    }

    public List<Contact> findMessageWithOpenStatus() {
        return contactRepository.findMessagesWithStatus(EazySchoolConstants.OPEN);
    }

    public boolean updateMessageStatus(int contactId, String name) {
        boolean isUpdated = false;
        int result = contactRepository.updateMessageStatus(contactId, EazySchoolConstants.CLOSE, name);
        isUpdated = result > 0;
        return isUpdated;
    }
}

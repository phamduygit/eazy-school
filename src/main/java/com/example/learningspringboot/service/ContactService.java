package com.example.learningspringboot.service;
import com.example.learningspringboot.constants.EazySchoolConstants;
import com.example.learningspringboot.model.Holiday;
import com.example.learningspringboot.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningspringboot.model.Contact;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        boolean isSave = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (savedContact.getContactId() > 0) {
            isSave = true;
        }
        return true;
    }

    public List<Contact> findMessageWithOpenStatus() {
        Iterable<Contact> holidays = contactRepository.findByStatus(EazySchoolConstants.OPEN);
        List<Contact> result = new ArrayList<Contact>();
        holidays.forEach(result::add);
        return result;
    }

    public boolean updateMessageStatus(int contactId) {
        boolean isUpdated = false;
        Optional<Contact> foundedContact = contactRepository.findById(contactId);
        foundedContact.ifPresent(contact -> {
            contact.setStatus(EazySchoolConstants.CLOSE);
        });
        Contact updatedContact = contactRepository.save(foundedContact.get());
        if (updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}

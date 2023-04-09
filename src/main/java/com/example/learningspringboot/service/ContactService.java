package com.example.learningspringboot.service;
import com.example.learningspringboot.constants.EazySchoolConstants;
import com.example.learningspringboot.model.Holiday;
import com.example.learningspringboot.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }
}

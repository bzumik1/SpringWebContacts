package com.example.demo.service;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(Contact contact){
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts(){
        var temp = new ArrayList<Contact>();
        contactRepository.findAll().forEach(temp::add);
        return temp;
    }

    public Optional<Contact> getContactById(UUID id){
        return contactRepository.findById(id);
    }

    public Optional<Contact> updateById(UUID id,Contact contact){ // melo by se to takto delat???
        var dataInDatabase = contactRepository.findById(id);
        Contact contactInDatabase;
        if(dataInDatabase.isPresent()){ //findById returns optional -> null or contact
            contactInDatabase = dataInDatabase.get();
            contactInDatabase.copyAllAttributesWithoutId(contact);
            contactRepository.save(contactInDatabase);
        }
        return dataInDatabase;
    }

    public void deleteById(UUID id){
        contactRepository.deleteById(id);
    }


}

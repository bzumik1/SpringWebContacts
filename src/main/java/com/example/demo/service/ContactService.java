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

    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(Contact contact){
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts(){
        var temp = new ArrayList<Contact>();
        contactRepository.findAll().forEach(c -> temp.add(c));
        return temp;
    }

    public Optional<Contact> getContactById(UUID id){
        return contactRepository.findById(id);
    }



}

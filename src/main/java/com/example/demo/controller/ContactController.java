package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/test")
@RestController
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable UUID id){
        var temp = contactService.getContactById(id);
        if(temp.isPresent()){
            return new ResponseEntity<>(temp.get(),HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(temp.orElse(null),HttpStatus.NOT_FOUND);
    }


}

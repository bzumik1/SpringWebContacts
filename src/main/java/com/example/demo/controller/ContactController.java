package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping(path = "/test")
    public String getHello(){
        return contactService.getContact();

    }

    @PostMapping(path = "/test")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.ACCEPTED);
    }

}

package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class ContactController {
    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(){
        return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable UUID id){
        var temp = contactService.getContactById(id);
        return temp.map(contact -> new ResponseEntity<>(contact, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(temp.orElse(null), HttpStatus.OK));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Null> deleteById(@PathVariable UUID id){
        contactService.deleteById(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PutMapping(path ={"/{id}"})
    public ResponseEntity<Contact> updateById(@PathVariable UUID id, @RequestBody Contact contact){
        var updatedData = contactService.updateById(id,contact);

        if(updatedData.isPresent()){
            var updatedContact = updatedData.get();
            return new ResponseEntity<>(updatedContact,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


}

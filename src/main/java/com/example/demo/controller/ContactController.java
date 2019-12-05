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

    @Autowired
    ContactService contactService;

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
        if(temp.isPresent()){
            return new ResponseEntity<>(temp.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(temp.orElse(null),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Null> deleteById(@PathVariable UUID id){
        contactService.deleteById(id);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PutMapping(path ={"/{id}"})
    public ResponseEntity<Contact> updateById(@PathVariable UUID id, @RequestBody Contact contact){
        var updatedData = contactService.updateById(id,contact);
        var updatedContact = updatedData.get();
        if(updatedData.isPresent()){
            return new ResponseEntity<>(updatedContact,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(updatedContact,HttpStatus.NOT_FOUND);
        }
    }


}

package com.example.demo.repository;

import com.example.demo.model.Contact;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, UUID> {
}

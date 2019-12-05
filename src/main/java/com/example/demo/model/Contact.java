package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity //nova tabulka v databazi
@Data
public class Contact {
    @GeneratedValue(strategy = GenerationType.AUTO) //strategy = GenerationType.IDENTITY) toto pro integer
    @Id
    private UUID id;
    @NotBlank // vyhodi chybu, pokud je null nebo prazdny string
    private String name;
    private String number;

    public Contact(){ //musi zde byt prazdny konstruktor, Hibernate jej pozduje.
        this.id =null;
        this.name="";
        this.number ="";
    }

   public void copyAllAttributesWithoutId(Contact contact){
        this.name = contact.getName();
        this.number = contact.getNumber();
   }
}

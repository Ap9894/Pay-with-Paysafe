package dev.abhi.roiim.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String firstname;

    private String  phone;

    private String customerId;      // returned by paysafe api call

    public Customer() {}

    public Customer(String email, String firstname, String phone, String customerId) {

        this.customerId = customerId;
        this.email = email;
        this.firstname = firstname;
        this.phone = phone;

    }
}

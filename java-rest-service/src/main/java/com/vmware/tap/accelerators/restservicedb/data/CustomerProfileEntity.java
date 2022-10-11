package com.vmware.tap.accelerators.restservicedb.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "CustomerProfile")
@Table(name = "customer_profile")
public class CustomerProfileEntity {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    public String getId() {
        return id;
    }

    public CustomerProfileEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerProfileEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerProfileEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerProfileEntity setEmail(String email) {
        this.email = email;
        return this;
    }
}

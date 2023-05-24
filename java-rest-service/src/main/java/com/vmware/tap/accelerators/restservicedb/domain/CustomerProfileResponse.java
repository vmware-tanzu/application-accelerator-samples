package com.vmware.tap.accelerators.restservicedb.domain;

import jakarta.validation.constraints.NotBlank;

public class CustomerProfileResponse {

    @NotBlank
    private final String id;

    private final String firstName;

    private final String lastName;

    @NotBlank
    private final String email;

    public CustomerProfileResponse(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}

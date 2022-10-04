package com.vmware.tap.accelerators.restservicedb.domain;

import javax.validation.constraints.NotBlank;

/**
 * Request to update/change the customer profile. Only first and lastName can be changed.
 */
public class CustomerProfileChangeRequest {

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    public CustomerProfileChangeRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

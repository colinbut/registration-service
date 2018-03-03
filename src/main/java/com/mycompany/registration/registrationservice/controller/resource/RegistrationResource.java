/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.controller.resource;

import java.util.Date;

public class RegistrationResource {

    private String ssn;
    private String firstName;
    private String secondName;
    private Date dob;
    private AddressResource addressResource;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public AddressResource getAddressResource() {
        return addressResource;
    }

    public void setAddressResource(AddressResource addressResource) {
        this.addressResource = addressResource;
    }
}

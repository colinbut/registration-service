/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Registration {
    private String ssn;
    private String forename;
    private String surname;
    private String email;
    private Date dob;
    private Address address;
    private boolean subscribedToMarketingEmails;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isSubscribedToMarketingEmails() {
        return subscribedToMarketingEmails;
    }

    public void setSubscribedToMarketingEmails(boolean subscribedToMarketingEmails) {
        this.subscribedToMarketingEmails = subscribedToMarketingEmails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Registration that = (Registration) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
            .append(ssn, that.ssn)
            .append(forename, that.forename)
            .append(surname, that.surname)
            .append(email, that.email)
            .append(dob, that.dob)
            .append(address, that.address)
            .append(subscribedToMarketingEmails, that.subscribedToMarketingEmails)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
            .append(ssn)
            .append(forename)
            .append(surname)
            .append(email)
            .append(dob)
            .append(address)
            .append(subscribedToMarketingEmails)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("ssn", ssn)
            .append("forename", forename)
            .append("surname", surname)
            .append("email", email)
            .append("dob", dob)
            .append("address", address)
            .append("isSubscribedToMarketingEmails", subscribedToMarketingEmails)
            .toString();
    }
}

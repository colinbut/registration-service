/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.controller;

public class RegisteredUser {
    private String username;
    private String randomGeneratedPassword;

    public RegisteredUser(String username, String randomGeneratedPassword){
        this.username = username;
        this.randomGeneratedPassword = randomGeneratedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getRandomGeneratedPassword() {
        return randomGeneratedPassword;
    }
}

/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.controller;

import com.mycompany.registration.registrationservice.model.Registration;
import com.mycompany.registration.registrationservice.service.RegisterService;
import com.mycompany.registration.registrationservice.exception.UserAlreadyExistException;
import com.mycompany.registration.registrationservice.exception.UserBlacklistedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody @Valid Registration registration) {

        LOGGER.info("Attempting to register: %s", registration);

        try {
            registerService.register(registration);

            RegisteredUser registeredUser = buildRegisteredUser(registration);
            return ResponseEntity.ok(registeredUser);

        } catch (UserAlreadyExistException ex) {
            LOGGER.error("User Already Exist", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (UserBlacklistedException ex) {
            LOGGER.error("User details is Blacklisted", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private RegisteredUser buildRegisteredUser(Registration registration) {
        return new RegisteredUser(registration.getForename(), "");
    }
}

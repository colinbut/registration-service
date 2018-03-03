/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice;

import com.mycompany.registration.registrationservice.controller.RegisteredUser;
import com.mycompany.registration.registrationservice.controller.RegistrationController;
import com.mycompany.registration.registrationservice.model.Registration;
import com.mycompany.registration.registrationservice.service.RegisterService;
import com.mycompany.registration.registrationservice.exception.UserAlreadyExistException;
import com.mycompany.registration.registrationservice.exception.UserBlacklistedException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerUTest {

    @Mock
    private RegisterService registerService;

    @InjectMocks
    private RegistrationController classUnderTest = new RegistrationController();

    @Test
    public void givenUserAlreadyExist_whenRegister_thenReturnInternalServerError() throws UserBlacklistedException, UserAlreadyExistException {
        Registration registration = new Registration();

        Mockito.doThrow(UserAlreadyExistException.class).when(registerService).register(registration);

        ResponseEntity responseEntity = classUnderTest.register(registration);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Mockito.verify(registerService, Mockito.times(1)).register(registration);
    }

    @Test
    @Ignore
    public void givenUserIsBlacklisted_whenRegister_thenReturnInternalServerError() throws UserBlacklistedException, UserAlreadyExistException {
        Registration registration = new Registration();

        Mockito.doThrow(UserBlacklistedException.class).when(registerService).register(registration);

        ResponseEntity responseEntity = classUnderTest.register(registration);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Mockito.verify(registerService, Mockito.times(1)).register(registration);
    }

    @Test
    public void givenUserDoesNotAlreadyExist_AndNotBlacklisted_whenRegister_thenReturnReturnSuccess() throws UserBlacklistedException, UserAlreadyExistException {
        Registration registration = new Registration();
        registration.setForename("forename");

        Mockito.doNothing().when(registerService).register(registration);

        ResponseEntity responseEntity = classUnderTest.register(registration);

        Mockito.verify(registerService, Mockito.times(1)).register(registration);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        RegisteredUser registeredUser = (RegisteredUser)responseEntity.getBody();
        assertEquals("forename", registeredUser.getUsername());
        assertEquals("random password", registeredUser.getRandomGeneratedPassword());
    }

}
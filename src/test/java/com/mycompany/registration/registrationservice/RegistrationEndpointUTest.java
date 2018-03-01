/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationEndpointUTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RegistrationEndpoint classUnderTest = new RegistrationEndpoint();

    @Test
    public void givenUserAlreadyExist_whenRegister_thenReturnInternalServerError() {

        Mockito.when(restTemplate.getForEntity(Matchers.anyString(), Matchers.any()))
            .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

        ResponseEntity responseEntity = classUnderTest.register();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

    }

    @Test
    public void givenUserIsBlacklisted_whenRegister_thenReturnInternalServerError() {
        Mockito.when(restTemplate.getForEntity(Matchers.anyString(), Matchers.any()))
            .thenReturn(ResponseEntity.ok().build());

        ResponseEntity responseEntity = classUnderTest.register();
    }

    @Test
    public void givenUserDoesNotAlreadyExist_AndNotBlacklisted_whenRegister_thenReturnReturnSuccess() {
        Mockito.when(restTemplate.getForEntity(Matchers.anyString(), Matchers.any()))
            .thenReturn(ResponseEntity.ok().build());

        ResponseEntity responseEntity = classUnderTest.register();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
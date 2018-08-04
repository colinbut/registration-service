/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service.impl;

import com.mycompany.user.resource.UserResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplUTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserServiceImpl classUnderTest = new UserServiceImpl();

    @Test
    public void testCheckUserAlreadyExistShouldReturnTrueIfUserExist() {

        UserResource userResource = new UserResource();
        userResource.setSsn("ssn");

        ResponseEntity responseEntity = ResponseEntity.ok().build();

        Mockito.when(restTemplate.getForEntity(Matchers.anyString(), Matchers.any())).thenReturn(responseEntity);

        boolean userExist = classUnderTest.checkUserAlreadyExist(userResource);

        assertTrue(userExist);
        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Matchers.anyString(), Matchers.any());
    }

    @Test
    public void testCheckUserAlreadyExistShouldReturnFalseIfUserNotExist() {
        UserResource userResource = new UserResource();
        userResource.setSsn("ssn");

        ResponseEntity responseEntity = ResponseEntity.notFound().build();

        Mockito.when(restTemplate.getForEntity(Matchers.anyString(), Matchers.any())).thenReturn(responseEntity);

        boolean userExist = classUnderTest.checkUserAlreadyExist(userResource);

        assertFalse(userExist);
        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(Matchers.anyString(), Matchers.any());
    }

    @Test
    public void testCreateNewUser(){

        UserResource userResource = new UserResource();
        userResource.setSsn("ssn");
        userResource.setFirstname("FirstName");
        userResource.setSurname("Surname");
        userResource.setAddress("Address");
        userResource.setPostcode("Postcode");
        userResource.setCity("City");
        userResource.setCountry("Country");
        userResource.setDob(new Date());

        Mockito.when(restTemplate.postForEntity(Matchers.anyString(), Matchers.any(HttpEntity.class), Matchers.any()))
            .thenReturn(ResponseEntity.ok().build());

        classUnderTest.createNewUser(userResource);

        Mockito.verify(restTemplate, Mockito.times(1)).postForEntity(Matchers.anyString(), Matchers.any(HttpEntity.class), Matchers.any());
    }

}
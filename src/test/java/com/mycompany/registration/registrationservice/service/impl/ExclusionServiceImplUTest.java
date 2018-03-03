/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ExclusionServiceImplUTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ExclusionServiceImpl classInTest = new ExclusionServiceImpl();

    @Test
    public void givenSsn_AndDobIsExcluded_whenCheckUserIsBlacklisted_thenReturnTrue() {

        String ssn = "ssn";
        Date dob = new Date();

        ResponseEntity responseEntity = ResponseEntity.ok().build();

        Mockito.when(restTemplate.getForEntity(Matchers.anyString(), Matchers.any())).thenReturn(responseEntity);

        boolean blacklisted = classInTest.userIsBlacklisted(ssn, dob);

        assertTrue(blacklisted);
    }

    @Test
    public void givenSsn_AndDobIsNotExcluded_whenCheckUserIsBlacklisted_thenReturnFalse() {
        String ssn = "ssn";
        Date dob = new Date();

        ResponseEntity responseEntity = ResponseEntity.notFound().build();

        Mockito.when(restTemplate.getForEntity(Matchers.anyString(), Matchers.any())).thenReturn(responseEntity);

        boolean blacklisted = classInTest.userIsBlacklisted(ssn, dob);

        assertFalse(blacklisted);
    }
}
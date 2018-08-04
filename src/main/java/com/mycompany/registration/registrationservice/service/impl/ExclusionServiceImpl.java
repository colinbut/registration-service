/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service.impl;

import com.mycompany.registration.registrationservice.service.ExclusionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class ExclusionServiceImpl implements ExclusionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExclusionServiceImpl.class);

    private static final String EXCLUSION_SERVICE_URL = "http://localhost:8090/exclusion-service/rest/";
    private static final String EXCLUSION_SERVICE_VALIDATE = "validate/{ssn}/{dob}";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean userIsBlacklisted(String ssn, Date dob) {

        String exclusionServiceUrlEndpoint = EXCLUSION_SERVICE_URL + EXCLUSION_SERVICE_VALIDATE.replace("{ssn}/{dob}","");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(exclusionServiceUrlEndpoint, String.class);
        // TODO fix this - should check the response from Exclusion Service
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return true;
        }

        return false;
    }
}

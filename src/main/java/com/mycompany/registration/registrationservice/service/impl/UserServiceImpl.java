/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service.impl;

import com.mycompany.registration.registrationservice.service.UserService;
import com.mycompany.user.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String USER_SERVICE_URL = "http://localhost:8080/";
    private static final String USER_SERVICE_GET_USER_ENDPOINT = "/user/{ssn}";
    private static final String USER_SERVICE_CREATE_USER_ENDPOINT = "/user/create";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean checkUserAlreadyExist(UserResource userResource) {

        String userServiceUrlEndpoint = USER_SERVICE_URL + USER_SERVICE_GET_USER_ENDPOINT.replace("{ssn}", "###-0000-###-0001");

        LOGGER.debug("Calling external UserService with endpoint URL: %s", userServiceUrlEndpoint);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(userServiceUrlEndpoint, String.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            return false;
        }

        String body = responseEntity.getBody();
        LOGGER.info(body);

        return true;
    }

    @Override
    public void createNewUser(UserResource userResource) {
        UserResource requestResource = new UserResource();

        HttpEntity<UserResource> request = new HttpEntity<>(requestResource);
        restTemplate.postForEntity(USER_SERVICE_URL + USER_SERVICE_CREATE_USER_ENDPOINT, request, UserResource.class);

    }
}

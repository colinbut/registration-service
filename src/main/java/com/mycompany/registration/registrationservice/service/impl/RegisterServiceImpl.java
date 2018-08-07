/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service.impl;

import com.mycompany.registration.registrationservice.exception.UserAlreadyExistException;
import com.mycompany.registration.registrationservice.exception.UserBlacklistedException;
import com.mycompany.registration.registrationservice.model.Address;
import com.mycompany.registration.registrationservice.model.Registration;
import com.mycompany.registration.registrationservice.service.ExclusionService;
import com.mycompany.registration.registrationservice.service.RegisterService;
import com.mycompany.registration.registrationservice.service.SubscriptionService;
import com.mycompany.registration.registrationservice.service.UserService;
import com.mycompany.user.resource.UserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ExclusionService exclusionService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public void register(Registration registration) throws UserAlreadyExistException, UserBlacklistedException {

        UserResource userResource = mapRegistrationToUserResource(registration);

        if (userService.checkUserAlreadyExist(userResource)) {
            throw new UserAlreadyExistException("User Already Exist");
        }

        if (exclusionService.userIsBlacklisted(registration.getSsn(), registration.getDob())) {
            throw new UserBlacklistedException("This user's SSN and Date Of Birth is blacklisted");
        }

        userService.createNewUser(userResource);

        // TODO - if userService fails then? Consider using Compensation Transactions
        subscriptionService.sendRegistration(registration);

        LOG.info("Successfully Registered new user: %s", userResource);

    }

    private UserResource mapRegistrationToUserResource(Registration registration) {
        UserResource userResource = new UserResource();
        userResource.setSsn(registration.getSsn());
        userResource.setFirstname(registration.getForename());
        userResource.setSurname(registration.getSurname());
        userResource.setDob(registration.getDob());
        userResource.setEmail(registration.getEmail());
        Address address = registration.getAddress();
        userResource.setAddress(address.getAddress());
        userResource.setPostcode(address.getPostCode());
        userResource.setCity(address.getCity());
        userResource.setCountry(address.getCountry());
        return userResource;
    }
}

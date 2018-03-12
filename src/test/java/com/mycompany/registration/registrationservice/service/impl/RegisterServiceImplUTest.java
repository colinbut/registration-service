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
import com.mycompany.registration.registrationservice.service.SubscriptionService;
import com.mycompany.registration.registrationservice.service.UserService;
import com.mycompany.user.resource.UserResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceImplUTest {

    @Mock
    private UserService userService;

    @Mock
    private ExclusionService exclusionService;

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private RegisterServiceImpl classInTest = new RegisterServiceImpl();

    @Test(expected = UserAlreadyExistException.class)
    public void givenUserAlreadyExist_whenRegister_thenThrowUserAlreadyExistException() throws UserBlacklistedException, UserAlreadyExistException {
        Registration registration = getRegistration();

        Mockito.when(userService.checkUserAlreadyExist(Matchers.any(UserResource.class))).thenReturn(true);

        classInTest.register(registration);

        Mockito.verify(userService, Mockito.times(1)).checkUserAlreadyExist(Matchers.any(UserResource.class));
    }

    @Test(expected = UserBlacklistedException.class)
    public void givenUserIsBlacklisted_whenRegister_thenThrowUserBlacklistedException() throws UserBlacklistedException, UserAlreadyExistException {
        Registration registration = getRegistration();

        Mockito.when(userService.checkUserAlreadyExist(Matchers.any(UserResource.class))).thenReturn(false);
        Mockito.when(exclusionService.userIsBlacklisted(Matchers.eq(registration.getSsn()), Matchers.eq(registration.getDob()))).thenReturn(true);

        classInTest.register(registration);

        Mockito.verify(userService, Mockito.times(1)).checkUserAlreadyExist(Matchers.any(UserResource.class));
        Mockito.verify(exclusionService, Mockito.times(1)).userIsBlacklisted(Matchers.eq(registration.getSsn()), Matchers.eq(registration.getDob()));
    }

    @Test
    public void givenUser_whenRegister_thenCreateNewUser_AndSendRegistrationToSubscrptionService() throws UserBlacklistedException, UserAlreadyExistException {
        Registration registration = getRegistration();

        Mockito.when(userService.checkUserAlreadyExist(Matchers.any(UserResource.class))).thenReturn(false);
        Mockito.when(exclusionService.userIsBlacklisted(Matchers.eq(registration.getSsn()), Matchers.eq(registration.getDob()))).thenReturn(false);
        Mockito.doNothing().when(userService).createNewUser(Matchers.any(UserResource.class));
        Mockito.doNothing().when(subscriptionService).sendRegistration(registration);

        classInTest.register(registration);

        Mockito.verify(userService, Mockito.times(1)).checkUserAlreadyExist(Matchers.any(UserResource.class));
        Mockito.verify(exclusionService, Mockito.times(1)).userIsBlacklisted(Matchers.eq(registration.getSsn()), Matchers.eq(registration.getDob()));
        Mockito.verify(userService, Mockito.times(1)).createNewUser(Matchers.any(UserResource.class));
        Mockito.verify(subscriptionService, Mockito.times(1)).sendRegistration(registration);
    }

    private Registration getRegistration(){
        Registration registration = new Registration();
        registration.setSsn("ssn");
        registration.setForename("forename");
        registration.setSurname("surname");
        registration.setDob(new Date());

        Address address = new Address();
        address.setPostCode("Postcode");
        address.setAddress("address");
        address.setCity("City");
        address.setCountry("Country");

        registration.setAddress(address);

        return registration;
    }

}
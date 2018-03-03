/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service;

import com.mycompany.registration.registrationservice.model.Registration;
import com.mycompany.registration.registrationservice.exception.UserAlreadyExistException;
import com.mycompany.registration.registrationservice.exception.UserBlacklistedException;

public interface RegisterService {
    void register(Registration registration) throws UserAlreadyExistException, UserBlacklistedException;
}

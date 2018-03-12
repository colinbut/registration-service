/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service;

import com.mycompany.registration.registrationservice.model.Registration;

public interface SubscriptionService {
    void sendRegistration(Registration registration);
}

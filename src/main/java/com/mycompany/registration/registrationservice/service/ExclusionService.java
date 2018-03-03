/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service;

import java.util.Date;

public interface ExclusionService {
    boolean userIsBlacklisted(String ssn, Date dob);
}

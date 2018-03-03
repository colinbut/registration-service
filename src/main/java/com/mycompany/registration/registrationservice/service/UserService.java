/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service;

import com.mycompany.user.resource.UserResource;

public interface UserService {

    boolean checkUserAlreadyExist(UserResource userResource);

    void createNewUser(UserResource userResource);
}

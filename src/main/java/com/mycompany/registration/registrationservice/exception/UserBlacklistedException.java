/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.exception;

public class UserBlacklistedException extends Exception {

    private static final long serialVersionUID = 1L;

	public UserBlacklistedException(){
        super();
    }

    public UserBlacklistedException(String message){
        super(message);
    }

    public UserBlacklistedException(String message, Throwable ex){
        super(message, ex);
    }
}

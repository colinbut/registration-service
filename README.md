# Registration Service

Registration Service is the main service that manages the new registrations of new users into the 'site'.
It is a typical microservice as such. Written in Java on Spring Boot. 

This microservices first calls out to the User Service to check whether the newly supplied registration details exist for a current existing user before
asking the Exclusion Service to check whether the details of this new registration are 'blacklisted' or not. See Exclusion Service for more details about blacklisting/ exclusions.

Once everything is fine, it commands User Service to create a new user with the details supplied in the registration. 

This is effectively an aggregating microservice. It is also considered a front edge service as it is the entrypoint to the registration functionality.

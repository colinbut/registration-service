/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service.impl;

import com.mycompany.registration.registrationservice.config.AppConfig;
import com.mycompany.registration.registrationservice.model.Registration;
import com.mycompany.registration.registrationservice.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendRegistration(Registration registration) {
        LOG.info("Sending registration %s to SubscriptionService", registration);

        rabbitTemplate.convertAndSend(AppConfig.EXCHANGE, AppConfig.MARKETING_QUEUE, registration);
    }
}

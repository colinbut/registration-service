/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.registration.registrationservice.service.impl;

import com.mycompany.registration.registrationservice.config.AppConfig;
import com.mycompany.registration.registrationservice.model.Registration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceImplUTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private SubscriptionServiceImpl classUnderTest = new SubscriptionServiceImpl();

    @Test
    public void testSendRegistration() throws Exception {
        Registration registration = new Registration();
        Mockito.doNothing().when(rabbitTemplate).convertAndSend(AppConfig.EXCHANGE, AppConfig.MARKETING_QUEUE, registration);

        classUnderTest.sendRegistration(registration);

        Mockito.verify(rabbitTemplate, Mockito.times(1)).convertAndSend(AppConfig.EXCHANGE, AppConfig.MARKETING_QUEUE, registration);
    }

}
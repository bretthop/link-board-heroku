package org.sandbox.springplayground.service;

import org.joda.time.LocalDateTime;
import org.sandbox.springplayground.ui.bean.HelloBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService
{
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    public HelloBean sayHello()
    {
        logger.error("sayHello executing");

        HelloBean bean = new HelloBean();

        bean.setTitle("Hello, Spring!");
        bean.setName("Kisuke Urahara");
        bean.setAddress("123 Fake St");
        bean.setLastSeen(new LocalDateTime());

        return bean;
    }
}

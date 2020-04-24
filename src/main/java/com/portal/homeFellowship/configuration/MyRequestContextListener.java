package com.portal.homeFellowship.configuration;

import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;


@Configuration
@WebListener
public class MyRequestContextListener extends RequestContextListener {
    //This Class for sorting Aop Request Scope
    final static Logger logger = LoggerFactory.getLogger(MyRequestContextListener.class);
     @PostConstruct
    private void startup() {
        logger.info("***************************REQUEST CONTEXT LISTENER STARTED******************************");
    }
}

package com.dc.sb.web.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author DUCHONG
 * @since 2018-12-19 11:44
 **/
@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    @JmsListener(destination = "helloQueue")
    public void receiveMessage(String text){
        System.out.println("Listener....receive msg....."+text);
    }
}

package com.dc.sb.web.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author DUCHONG
 * @since 2018-12-19 11:32
 **/
@Component
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private JmsMessagingTemplate messagingTemplate;


    public void sendMessage(Destination destination,String text){
        messagingTemplate.convertAndSend(destination,text);
    }

}

package com.dc.sb.web.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * @author DUCHONG
 * @since 2018-12-19 13:57
 **/

@RestController
public class ActiveMQController {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQController.class);

    @Autowired
    private Producer producer;

    @RequestMapping("/send/msg")
    public String sendQueueMsg(){
        Destination destination=new ActiveMQQueue("helloQueue");
        String text="Hello ActiveMQ!";

        for (int i=0;i<6;i++){
            producer.sendMessage(destination,text);
        }

        return "send over";
    }

}

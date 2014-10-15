package com.spring.jms;

import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Date;

/**
 * Created by Internet on 02/10/14.
 */
@Component
public class JmsReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println(String.format("Receive a message at %s!", new Date()));
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(String.format("Message content: '%s'", textMessage.getText()));
            } catch (JMSException e) {
                System.out.println("Cannot parse text message");
                e.printStackTrace();
            }
        }
    }

}

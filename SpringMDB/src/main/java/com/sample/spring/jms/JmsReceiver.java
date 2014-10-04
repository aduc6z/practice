package com.sample.spring.jms;

import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Internet on 02/10/14.
 */
@Component
public class JmsReceiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Receive a message!");
    }
}

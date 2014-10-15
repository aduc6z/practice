package com.sample.spring.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by Internet on 02/10/14.
 */

@Service
public class JmsSender {
    @Autowired
    JmsTemplate template;

    public void sendMessage(final String content) {
        template.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                StringBuffer sb = new StringBuffer("Message create at " + String.valueOf(System.currentTimeMillis()));
                sb.append('\n');
                sb.append(content);
                textMessage.setText(sb.toString());
                textMessage.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                return textMessage;
            }
        });
    }
}
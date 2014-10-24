package com.sample.spring.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.Date;

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
                String date = new Date().toString();
                StringBuffer sb = new StringBuffer("Message created at " + date);
                sb.append('-');
                sb.append(content);
                textMessage.setText(sb.toString());
//              textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
                System.out.println("Mode is Persistent: " + (textMessage.getJMSDeliveryMode() == DeliveryMode.PERSISTENT));
                return textMessage;
            }
        });
    }
}
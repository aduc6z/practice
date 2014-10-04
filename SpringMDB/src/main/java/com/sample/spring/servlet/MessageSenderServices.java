package com.sample.spring.servlet;

import com.sample.spring.jms.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

/**
 * Created by Internet on 02/10/14.
 */

@Service
@RequestMapping("/rest")
public class MessageSenderServices {

    @Autowired
    JmsSender sender;

    @PostConstruct
    public void init() {
        System.out.println("Initialization completed!");
    }

    @RequestMapping(value = "/send/{name}", method = RequestMethod.GET)
    @ResponseBody
    String sendMessage(@PathVariable String name) {
        sender.sendMessage("Name: " + name);
        return "Completed!";

    }
}

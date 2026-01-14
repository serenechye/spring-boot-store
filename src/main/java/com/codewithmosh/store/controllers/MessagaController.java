package com.codewithmosh.store.controllers;

import com.codewithmosh.store.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagaController {
    @RequestMapping("/hello")
    public Message sayHello() {
        return new Message("Hello World!") ;
    }
}

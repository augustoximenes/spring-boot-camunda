package com.example.workflow.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/orders")
@Slf4j
public class OrderController {
    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping(method = RequestMethod.POST)
    public String placeOrder(@RequestBody String order) {
        final String businessKey = UUID.randomUUID().toString();
        runtimeService.startProcessInstanceByKey("order-process", businessKey);
        return businessKey;
    }
}

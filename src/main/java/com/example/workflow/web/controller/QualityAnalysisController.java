package com.example.workflow.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/quality-analysis")
@Slf4j
public class QualityAnalysisController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/callback/{businessKey}", method = RequestMethod.POST)
    public void placeOrder(@PathVariable("businessKey") String businessKey) {
        kafkaTemplate.send("quality-analysis-received", businessKey, "Business Key: " + businessKey);
    }
}

package com.example.workflow.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
 
@Component
@Slf4j
public class QualityAnalysisReceived {
    @Autowired
    private RuntimeService runtimeService;
 
    @KafkaListener(topics = "quality-analysis-received", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(@Payload String payload, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String businessKey) {
        runtimeService.createMessageCorrelation("quality-analysis-received")
                .processInstanceBusinessKey(businessKey)
                .correlateWithResult();
    }
}

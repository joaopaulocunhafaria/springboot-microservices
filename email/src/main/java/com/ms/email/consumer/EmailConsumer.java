package com.ms.email.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dtos.EmailRecordDto;
import com.ms.email.models.EmailModel;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDto email){
       
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(email, emailModel);
       
       
        System.out.println(email.emailTo());
    }
    
}

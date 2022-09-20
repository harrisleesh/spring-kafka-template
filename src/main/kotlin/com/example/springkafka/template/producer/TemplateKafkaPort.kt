package com.example.springkafka.template.producer

import com.example.springkafka.template.dto.TemplateDTO
import org.springframework.kafka.support.SendResult
import org.springframework.util.concurrent.ListenableFuture


interface TemplateKafkaPort {
    fun produce(templateDTO: TemplateDTO): ListenableFuture<SendResult<Int, String>>
}

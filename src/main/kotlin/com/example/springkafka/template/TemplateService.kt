package com.example.springkafka.template

import com.example.springkafka.template.producer.aop.annotation.KafkaEventProducing
import com.example.springkafka.template.dto.TemplateDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TemplateService {

    @KafkaEventProducing
    @Transactional
    fun getTemplate() : TemplateDTO{
        return TemplateDTO("seonghun", 99, LocalDateTime.now())
    }
}
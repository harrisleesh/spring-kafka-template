package com.example.springkafka.template.producer.aop

import com.example.springkafka.template.producer.aop.annotation.KafkaEventProducing
import com.example.springkafka.template.dto.TemplateDTO
import com.example.springkafka.template.producer.TemplateKafkaPort
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TemplateKafkaAspect(val templateKafkaPort: TemplateKafkaPort) {

    @Around("@annotation(kafkaEventProducing)")
    fun produceEvent(joinPoint: ProceedingJoinPoint, kafkaEventProducing: KafkaEventProducing): Any? {
        return joinPoint.proceed()
            .also {
                println("kafka event producing ${it.toString()}")
            }
            .apply {
                if (this is TemplateDTO) templateKafkaPort.produce(this)
            }
    }
}
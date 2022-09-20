package com.example.springkafka.template.producer

import com.example.springkafka.template.dto.TemplateDTO
import com.example.springkafka.template.producer.request.TemplateEventDTO
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFuture


@Component
internal class TemplateKafkaAdapter(
    val kafkaTemplate: KafkaTemplate<Int, String>
) : TemplateKafkaPort {
    private val TOPIC_NAME = "template"
    override fun produce(templateDTO: TemplateDTO): ListenableFuture<SendResult<Int, String>> {
        return TemplateEventDTO.from(templateDTO)
            .run {
                kafkaTemplate.send(TOPIC_NAME, Json.encodeToString(this))
            }
    }
}

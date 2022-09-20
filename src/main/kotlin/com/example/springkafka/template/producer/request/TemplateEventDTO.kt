package com.example.springkafka.template.producer.request

import com.example.springkafka.template.dto.TemplateDTO
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
data class TemplateEventDTO(
    val name: String,
    val count: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime?,
) {
    companion object {
        fun from(templateDTO: TemplateDTO): TemplateEventDTO {
            return with(templateDTO) {
                TemplateEventDTO(
                    name,
                    count,
                    createdAt
                )
            }
        }
    }
}

object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    private val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.parse(decoder.decodeString(), pattern)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        return encoder.encodeString(value.format(pattern))
    }
}
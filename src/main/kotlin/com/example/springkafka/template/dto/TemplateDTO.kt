package com.example.springkafka.template.dto

import java.time.LocalDateTime

data class TemplateDTO(
    val name: String,
    val count: Int,
    val createdAt: LocalDateTime
)

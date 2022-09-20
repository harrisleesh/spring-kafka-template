package com.example.springkafka.template

import com.example.springkafka.template.dto.TemplateDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TemplateController(
    val templateService: TemplateService
) {

    @GetMapping("/template")
    fun getTemplate(): TemplateDTO {
        return templateService.getTemplate()
    }
}
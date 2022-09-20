package com.example.springkafka.template.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.stereotype.Service

@Service
class ConsumerService {
    @KafkaListener(topics = ["template"],
        groupId = "test-group-00"
    )
    fun recordListener(record: ConsumerRecord<String?, String?>) {
        println(record.toString())
    }

    @KafkaListener(topics = ["template"], groupId = "test-group-01")
    fun singleTopicListener(messageValue: String?) {
        println(messageValue)
    }

    @KafkaListener(
        topics = ["template"],
        groupId = "test-group-02",
        properties = ["max.poll.interval.ms:60000", "auto.offset.reset:earliest"]
    )
    fun singleTopicWithPropertiesListener(messageValue: String?) {
        println(messageValue)
    }

    @KafkaListener(topics = ["template"], groupId = "test-group-03", concurrency = "3")
    fun concurrentTopicListener(messageValue: String?) {
        println(messageValue)
    }

    @KafkaListener(
        topicPartitions = [TopicPartition(
            topic = "template",
            partitions = ["0"]
        )],
        groupId = "test-group-04"
    )
    fun listenSpecificPartition(record: ConsumerRecord<String?, String?>) {
        println(record.toString())
    }
}
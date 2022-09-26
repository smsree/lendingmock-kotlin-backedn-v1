package com.leadingmock.loanproducer.configuration

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class AutoConfigProducer {
    @Bean
    fun businessEvent(): NewTopic {
        return TopicBuilder.name("business-loan")
            .partitions(1)
            .replicas(1)
            .build()
    }

    @Bean
    fun educationalEvent(): NewTopic {
        return TopicBuilder
            .name("educational-loan")
            .partitions(1)
            .replicas(1)
            .build()
    }

    @Bean
    fun vehicleLoanEvent(): NewTopic {
        return TopicBuilder
            .name("vehicle-loan")
            .partitions(1)
            .replicas(1)
            .build()
    }

    @Bean
    fun housingLoanEvent(): NewTopic {
        return TopicBuilder
            .name("housing-loan")
            .partitions(1)
            .replicas(1)
            .build()
    }
}
package com.leadingmock.loanconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoanConsumerApplication

fun main(args: Array<String>) {
	runApplication<LoanConsumerApplication>(*args)
}

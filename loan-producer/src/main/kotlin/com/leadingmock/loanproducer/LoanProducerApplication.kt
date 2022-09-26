package com.leadingmock.loanproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoanProducerApplication

fun main(args: Array<String>) {
	runApplication<LoanProducerApplication>(*args)
}

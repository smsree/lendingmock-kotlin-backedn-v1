package com.leadingmock.loanproducer.exceptionHandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import java.util.stream.Collectors

@ControllerAdvice

class GlobalErrorHandler {
    @ExceptionHandler(WebExchangeBindException::class)
    fun handleRequestBodyError(ex: WebExchangeBindException): ResponseEntity<String?>? {
        println("Exception caught in handle request body error"+ ex.message+ ex)
        // GlobalErrorHandler.log.error("Exception caught in handle request body error", ex.message, ex)
        val error = ex.bindingResult.allErrors
            .stream().map { obj: ObjectError -> obj.defaultMessage }
            .sorted()
            .collect(Collectors.joining(","))
        println(">>>>Error>>>>:$error")
        // GlobalErrorHandler.log.error(">>>>Error>>>>:$error")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }
}
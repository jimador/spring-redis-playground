package com.codeforprofit.redis.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.redis.core.ReactiveRedisOperations

@SpringBootApplication
class ProducerApplication(private val reactiveRedisOperations:
                          ReactiveRedisOperations<String, String>)

fun main(args: Array<String>) {
	runApplication<ProducerApplication>(*args)
}

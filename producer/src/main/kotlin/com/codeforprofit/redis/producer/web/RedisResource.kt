package com.codeforprofit.redis.producer.web

import org.springframework.data.redis.connection.stream.Record
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class RedisResource(private val reactiveRedisOperations: ReactiveRedisOperations<String, String>) {

    @GetMapping("/data")
    fun getData() {
        val opsForStream = reactiveRedisOperations.opsForStream<Any, Any>()
        val add = opsForStream.add("stream1", mapOf("hello" to "world"))
        add.subscribe(::print)

    }
}

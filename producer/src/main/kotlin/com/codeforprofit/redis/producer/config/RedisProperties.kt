package com.codeforprofit.redis.producer.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "redis")
data class RedisProperties(var url: String = "localhost",
                      var port: Int = 6379) {

}

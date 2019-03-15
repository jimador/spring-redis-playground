package com.codeforprofit.redis.producer.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.stream.StreamMessageListenerContainer

@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfiguration(redisProperties: RedisProperties) {

    @Bean
    fun reactiveRedisOperations(factory: ReactiveRedisConnectionFactory): ReactiveRedisOperations<String, String> {
        return ReactiveRedisTemplate<String, String>(factory, RedisSerializationContext.string())
    }

    @Bean
    fun messageListener(factory: RedisConnectionFactory): StreamMessageListenerContainer<String, MapRecord<String, String, String>> {
        return StreamMessageListenerContainer.create(factory)
            .apply {
                receive(Consumer.from("group1", "name1"), StreamOffset.create("stream1", ReadOffset.lastConsumed()), { message -> println(message) })

                start()
            }
    }
}

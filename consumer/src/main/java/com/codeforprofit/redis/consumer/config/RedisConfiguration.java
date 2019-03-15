package com.codeforprofit.redis.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

@Slf4j
@Configuration
public class RedisConfiguration {

    @Bean
    public  StreamMessageListenerContainer<String, MapRecord<String, String, String>> messageListener(RedisConnectionFactory factory)  {
        final StreamMessageListenerContainer<String, MapRecord<String, String, String>> container =
            StreamMessageListenerContainer.create(factory);
        container.receive(Consumer.from("group2", "name2"), StreamOffset.create("stream1", ReadOffset.lastConsumed()), message -> log.info(message.toString()));
        container.start();

        return container;
    }
}

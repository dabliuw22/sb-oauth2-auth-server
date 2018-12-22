
package com.leysoft.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfiguration {

    @Value(
            value = "${spring.redis.host:localhost}")
    private String redisHost;

    @Value(
            value = "${spring.redis.port:6379}")
    private int redisPort;

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        return new RedisStandaloneConfiguration(redisHost, redisPort);
    }

    @Bean
    public RedisConnectionFactory
            redisConnectionFactory(RedisStandaloneConfiguration standaloneConfiguration) {
        return new JedisConnectionFactory(standaloneConfiguration);
    }
}

package com.example.music.config.data;

import com.example.music.domain.Album;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Album> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Album> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<Album> albumSerializer = new Jackson2JsonRedisSerializer<>(Album.class);

        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(albumSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(albumSerializer);

        return template;
    }

}

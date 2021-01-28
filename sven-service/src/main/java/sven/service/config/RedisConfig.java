package sven.service.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/28 19:39
 * @description：
 * @version:
 * @see
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
//@EnableCaching
public class RedisConfig {
    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}

//package com.bookindle.boosystem.config;
//
//import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
//import com.google.common.collect.ImmutableMap;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//import java.util.Map;
//
//@Configuration
//public class RedisConfigback1 {
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate();
//        template.setConnectionFactory(factory);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        //配置使用FastJson进行序列化和反序列化操作
//        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//        template.setKeySerializer(stringRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//        template.setValueSerializer(fastJsonRedisSerializer);
//        template.setHashValueSerializer(fastJsonRedisSerializer);
//        template.afterPropertiesSet();
//        //开启Redis事务
//        /**
//         * 为何要开启事务呢？redis本身是单线程执行的one by one
//         * 虽然都是原子性操作，那需要多个原子性的操作才算一个完整的事件呢
//         * 譬如 redis get set可能就是一对操作才能保证操作的完整性
//         * 譬如记录访问数 value 从0开始，在并发情况下多线程会同时查询到 value 为0 的情况，
//         * 此时两线程都得到value 0 为此都 +1操作 执行结果 value=1,而我们期望的结果至少是2才对
//         * 所以需要用事务来解决这一堆操作(get set)
//         */
//        template.setEnableTransactionSupport(true);
//        return template;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
//
//        //配置多个缓存名称 这里我们用个能用的模板  这里有坑点往下看
//        RedisCacheConfiguration templateRedisCacheCfg = RedisCacheConfiguration
//                .defaultCacheConfig()
//                // 设置key为String
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
//                // 设置value 为自动转Json的Object
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
//                // 不缓存null
//                .disableCachingNullValues();
//        //这里对RedisCacheConfiguration不熟悉的可能会有疑问，我们这样批量templateRedisCacheCfg.entryTtl(Duration.ofMinutes(15))最后不都是同一个引用吗，过期时间会生效吗？
//        Map<String, RedisCacheConfiguration> expires = ImmutableMap.<String, RedisCacheConfiguration>builder()
//                .put("15m", templateRedisCacheCfg.entryTtl(Duration.ofMinutes(15)))
//                .put("30m", templateRedisCacheCfg.entryTtl(Duration.ofMinutes(30)))
//                .put("60m", templateRedisCacheCfg.entryTtl(Duration.ofMinutes(60)))
//                .put("1d", templateRedisCacheCfg.entryTtl(Duration.ofDays(1)))
//                .put("30d", templateRedisCacheCfg.entryTtl(Duration.ofDays(30)))
//                .put("common-30d", templateRedisCacheCfg.entryTtl(Duration.ofDays(30)))
//                .build();
//
//        RedisCacheManager redisCacheManager =
//                RedisCacheManager.RedisCacheManagerBuilder
//                        // Redis 连接工厂
//                        .fromConnectionFactory(template.getConnectionFactory())
//                        // 默认缓存配置
//                        .cacheDefaults(templateRedisCacheCfg.entryTtl(Duration.ofHours(1)))
//                        // 配置同步修改或删除 put/evict
//                        .transactionAware()
//                        .initialCacheNames(expires.keySet())
//                        .withInitialCacheConfigurations(expires)
//                        .build();
//        return redisCacheManager;
//    }
//}
package com.example.JsonPlaceholder.config;



import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager; // Importar la interfaz JCache
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JCacheCacheManager cacheManager() {

        CacheManager jCacheManager = Caching.getCachingProvider().getCacheManager();


        jCacheManager.createCache("cache1", createJCacheConfiguration());
        jCacheManager.createCache("cache2", createJCacheConfiguration());

        return new JCacheCacheManager(jCacheManager);
    }

    private MutableConfiguration<String, Object> createJCacheConfiguration() {

        MutableConfiguration<String, Object> config = new MutableConfiguration<>();
        config.setStoreByValue(false)
                .setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(new Duration(TimeUnit.MINUTES, 5))) // Expiración después de 5 minutos
                .setTypes(String.class, Object.class);

        return config;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}

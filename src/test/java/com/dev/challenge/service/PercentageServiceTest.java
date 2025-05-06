package com.dev.challenge.service;

import com.dev.challenge.services.PercentageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableCaching
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PercentageServiceTest {

    @Autowired
    private PercentageServiceImpl percentageService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void shouldCachePercentageFor30Minutes() {
        double firstCall = percentageService.getPercentage();
        double secondCall = percentageService.getPercentage();
        assertEquals(firstCall, secondCall, "The value should be the same due to the cache.");
    }

    @Test
    void shouldReturnCachedValueIfExternalFails() {
        double cached = percentageService.getPercentage();

        PercentageServiceImpl failingService = new PercentageServiceImpl(cacheManager) {
            @Override
            public double fetchPercentageFromExternalService() {
                throw new RuntimeException("Simulated failure");
            }
        };

        double result = failingService.getPercentage();
        assertEquals(cached, result, "Must return the cached value if the external service fails");
    }

    @Test
    void shouldThrowIfNoCacheAndExternalFails() {
        PercentageServiceImpl failingService = new PercentageServiceImpl(cacheManager) {
            @Override
            public double fetchPercentageFromExternalService() {
                throw new RuntimeException("Simulated failure");
            }
        };

        clearCache();

        RuntimeException ex = assertThrows(RuntimeException.class, failingService::getPercentage);
        assertTrue(ex.getMessage().contains("Failed to fetch percentage and no cache available"));
    }

    private void clearCache() {
        cacheManager.getCache("percentageCache").clear();
    }
}

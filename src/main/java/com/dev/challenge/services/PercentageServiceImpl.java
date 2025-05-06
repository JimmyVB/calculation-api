package com.dev.challenge.services;

import com.dev.challenge.exceptions.PercentageServiceException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PercentageServiceImpl implements PercentageService {

    private final CacheManager cacheManager;

    public PercentageServiceImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    @Cacheable(value = "percentageCache", key = "'percentage'", unless = "#result == null", cacheManager = "cacheManager")
    public double getPercentage() {
        try {
            return fetchPercentageFromExternalService();
        } catch (Exception e) {
            Double cachedPercentage = getCachedPercentage();
            if (cachedPercentage != null) {
                return cachedPercentage;
            }
            throw new PercentageServiceException("Failed to fetch percentage and no cache available", e);
        }
    }

    private Double getCachedPercentage() {
        Cache cache = cacheManager.getCache("percentageCache");
        if (cache != null) {
            Cache.ValueWrapper cachedValue = cache.get("percentage");
            if (cachedValue != null) {
                return (Double) cachedValue.get();
            }
        }
        return null;
    }

    // Method to simulate fetching percentage from an external service
    public double fetchPercentageFromExternalService() {
        return 10.0;
    }
}

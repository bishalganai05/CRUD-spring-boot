package com.bishal.gms.service;

import java.util.Objects;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheInspectionService {
 
    private final CacheManager cacheManager;
    
    public CacheInspectionService(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void printCacheContents(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            System.out.println("Cache Contents:");
            System.out.println(Objects.requireNonNull(cache.getNativeCache()).toString());
        } else {
            System.out.println("No such cache: " + cacheName);
        }
    }
}

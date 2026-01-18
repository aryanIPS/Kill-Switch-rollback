package com.company.feature_flag_service.service;
//package com.company.feature_flag_service.service;

import com.company.feature_flag_service.Entity.featureflag;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public FeatureFlagCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public featureflag get(String key) {
        return (featureflag) redisTemplate.opsForValue().get(key);
    }

    public void put(String key, featureflag flag) {
        redisTemplate.opsForValue().set(key, flag);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
    //    @PostMapping("/{key}/toggle")
//    public void toggle(
//            @PathVariable String key,
//            @RequestParam boolean enabled) {
//
//        featureflag flag = repository.findByFeatureKey(key).orElseThrow();
//        flag.setEnabled(enabled);
//        repository.save(flag);
//    }


}


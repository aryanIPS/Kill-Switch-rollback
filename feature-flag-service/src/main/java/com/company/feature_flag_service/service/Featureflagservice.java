package com.company.feature_flag_service.service;

import com.company.feature_flag_service.Entity.featureflag;
import com.company.feature_flag_service.Repository.FeatureFlagAuditRepo;
import com.company.feature_flag_service.Repository.FeatureFlagrepo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class Featureflagservice {

    private final FeatureFlagrepo repository;

    // ===== PHASE 3 / 4 (CACHE SERVICE) — NOT USED IN PHASE 5 =====
    // private final FeatureFlagCacheService cacheService;

    private final FeatureFlagAuditRepo auditRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public Featureflagservice(
            FeatureFlagrepo repository,
            // FeatureFlagCacheService cacheService,
            FeatureFlagAuditRepo auditRepository,
            RedisTemplate<String, Object> redisTemplate) {

        this.repository = repository;
        // this.cacheService = cacheService;
        this.auditRepository = auditRepository;
        this.redisTemplate = redisTemplate;
    }

    // ============================================================
    // PHASE 3 / 4 CACHE METHOD — NOT USED NOW
    // ============================================================
    /*
    private featureflag getFromCacheOrDB(String featureKey) {

        featureflag flag = cacheService.get(featureKey);

        if (flag != null) {
            return flag;
        }

        flag = repository.findByFeatureKey(featureKey)
                .orElse(null);

        if (flag != null) {
            cacheService.put(featureKey, flag);
        }

        return flag;
    }
    */

    // ================= FEATURE CHECK (PHASE 5 FINAL) =================

    public boolean isEnabled(String featureKey, String env, String userId) {

        // 1️⃣ KILL SWITCH (highest priority)
        Object killObj = redisTemplate.opsForValue()
                .get("KILL:" + featureKey);

        if (Boolean.TRUE.equals(killObj)) {
            return false;
        }

        // 2️⃣ REDIS CACHE (env + feature)
        String cacheKey = env + ":" + featureKey;
        Object cacheObj = redisTemplate.opsForValue().get(cacheKey);
        Boolean cachedEnabled = (cacheObj instanceof Boolean)
                ? (Boolean) cacheObj
                :null;

        featureflag flag;

        if (cachedEnabled == null) {
            // 3️⃣ DB FETCH
            flag = repository
                    .findByFeatureKeyAndEnv(featureKey, env)
                    .orElse(null);

            if (flag == null) {
                redisTemplate.opsForValue().set(cacheKey, false);
                return false;
            }

            cachedEnabled = flag.isEnabled();
            redisTemplate.opsForValue().set(cacheKey, cachedEnabled);
        } else {
            // cache hit → still need DB for rollout %
            flag = repository
                    .findByFeatureKeyAndEnv(featureKey, env)
                    .orElse(null);
        }

        if (flag == null || !cachedEnabled) {
            return false;
        }

        // 4️⃣ HASH BASED % ROLLOUT
        int rolloutPercentage = flag.getRolloutPercentage();

        if (rolloutPercentage >= 100) {
            return true;
        }

        int hash = Math.abs(userId.hashCode() % 100);
        return hash < rolloutPercentage;
    }

    // ================= PHASE 4 METHOD (NOT USED NOW) =================
    /*
    public boolean isEnabledForUser(String featureKey, String userId) {

        featureflag flag = getFromCacheOrDB(featureKey);

        if (flag == null || !flag.isEnabled()) {
            return false;
        }

        int hash = Math.abs(userId.hashCode() % 100);
        return hash < flag.getRolloutPercentage();
    }
    */

    // ================= ADMIN OPS (PHASE 3 / 4) =================
    // NOTE: cacheService calls commented, logic preserved

    public featureflag save(featureflag flag) {
        featureflag saved = repository.save(flag);
        // cacheService.put(saved.getFeatureKey(), saved);
        return saved;
    }

    public void enableFeature(String featureKey) {
        featureflag flag = repository.findByFeatureKey(featureKey)
                .orElseThrow(() -> new RuntimeException("Feature not found"));

        flag.setEnabled(true);
        repository.save(flag);
        // cacheService.put(featureKey, flag);
    }

    public void disableFeature(String featureKey) {
        featureflag flag = repository.findByFeatureKey(featureKey)
                .orElseThrow(() -> new RuntimeException("Feature not found"));

        flag.setEnabled(false);
        repository.save(flag);
        // cacheService.put(featureKey, flag);
    }
}

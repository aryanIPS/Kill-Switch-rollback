package com.company.feature_flag_service.Repository;
import com.company.feature_flag_service.Entity.featureflag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureFlagrepo
        extends JpaRepository<featureflag, Long> {

    Optional<featureflag> findByFeatureKey(String featureKey);
            Optional<featureflag> findByFeatureKeyAndEnv(String featureKey, String env);
}
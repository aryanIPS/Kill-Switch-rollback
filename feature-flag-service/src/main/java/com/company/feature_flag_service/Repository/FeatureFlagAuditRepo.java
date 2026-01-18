package com.company.feature_flag_service.Repository;

import com.company.feature_flag_service.Entity.FeatureFlagAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureFlagAuditRepo
    extends JpaRepository<FeatureFlagAudit, Long>{
}


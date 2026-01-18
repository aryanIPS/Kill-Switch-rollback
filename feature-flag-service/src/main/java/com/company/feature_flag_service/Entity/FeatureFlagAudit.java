package com.company.feature_flag_service.Entity;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feature_flag_audit")
public class FeatureFlagAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feature_key", nullable = false)
    private String featureKey;

    @Column(name = "old_value", nullable = false)
    private Boolean oldValue;

    @Column(name = "new_value", nullable = false)
    private Boolean newValue;

    @Column(name = "changed_by", nullable = false)
    private String changedBy;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;


    public FeatureFlagAudit() {
    }



    public Long getId() {
        return id;
    }

    public String getFeatureKey() {
        return featureKey;
    }

    public void setFeatureKey(String featureKey) {
        this.featureKey = featureKey;
    }

    public Boolean getOldValue() {
        return oldValue;
    }

    public void setOldValue(Boolean oldValue) {
        this.oldValue = oldValue;
    }

    public Boolean getNewValue() {
        return newValue;
    }

    public void setNewValue(Boolean newValue) {
        this.newValue = newValue;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}


package com.company.feature_flag_service.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "feature_flags")
public class featureflag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String featureKey;

    private boolean enabled;
    private String description;

    public boolean isEnabled(){             //getter of enabled
        return enabled;
    }

    public void setEnabled(boolean enabled) {           //setter of enabled
        this.enabled = enabled;
    }

    public int rolloutPercentage;

    public int getRolloutPercentage() {
        return rolloutPercentage;
    }

    private String env;

    public String getFeatureKey() {
        return featureKey;
    }


    // getters & setters
}


package com.assurant.insurance.core_insurance_platform.policy.domain.model;
import java.util.Objects;
import java.util.UUID;
public class Coverage {

    private final UUID id;
    private final String description;

    public Coverage(String description){
        this.id = UUID.randomUUID();
        this.description = Objects.requireNonNull(description, "Coverage description is required.");
    }

    public UUID GetCoverageId(){
        return id;
    }

    public String getDescription(){
        return description;
    }
}

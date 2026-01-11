package com.assurant.insurance.core_insurance_platform.policy.domain.model;

import java.util.Objects;
import java.util.UUID;

public class Coverage {
    private final UUID id;
    private final String description;

    // Construtor para novas coberturas
    public Coverage(String description){
        this.id = UUID.randomUUID();
        this.description = Objects.requireNonNull(description, "Coverage description is required.");
    }

    // Construtor privado para rehidratação (carregar do banco)
    private Coverage(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Coverage rehydrate(UUID id, String description) {
        return new Coverage(id, description);
    }

    public UUID GetCoverageId(){
        return id;
    }

    public String getDescription(){
        return description;
    }
}
package com.assurant.insurance.core_insurance_platform.claims.infrastructure.persistence.repository;

import com.assurant.insurance.core_insurance_platform.claims.infrastructure.persistence.entity.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataClaimRepository extends JpaRepository<ClaimEntity, UUID> {
}

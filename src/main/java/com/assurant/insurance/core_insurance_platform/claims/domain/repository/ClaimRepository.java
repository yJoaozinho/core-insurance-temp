package com.assurant.insurance.core_insurance_platform.claims.domain.repository;

import com.assurant.insurance.core_insurance_platform.claims.domain.model.Claim;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;

import java.util.Optional;

public interface ClaimRepository {

    Optional<Claim> findById(ClaimId claimId);

    boolean existsById(ClaimId claimId);

    void save(Claim claim);

}

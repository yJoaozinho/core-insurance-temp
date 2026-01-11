package com.assurant.insurance.core_insurance_platform.policy.domain.repository;

import com.assurant.insurance.core_insurance_platform.policy.domain.model.Policy;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;

import java.util.Optional;

public interface PolicyRepository {

    void save(Policy policy);

    Optional<Policy> findById(PolicyId id);

    boolean existsById(PolicyId id);
}

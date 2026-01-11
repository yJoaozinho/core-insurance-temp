package com.assurant.insurance.core_insurance_platform.policy.domain.event;

import java.time.LocalDateTime;

public interface DomainEvent {

    LocalDateTime occurredAt();

}

package com.assurant.insurance.core_insurance_platform.policy.api.controller;

import com.assurant.insurance.core_insurance_platform.policy.api.dto.CancelPolicyRequest;
import com.assurant.insurance.core_insurance_platform.policy.api.dto.CreatePolicyRequest;
import com.assurant.insurance.core_insurance_platform.policy.api.dto.CreatePolicyResponse;
import com.assurant.insurance.core_insurance_platform.policy.application.command.ActivatePolicyCommand;
import com.assurant.insurance.core_insurance_platform.policy.application.command.CancelPolicyCommand;
import com.assurant.insurance.core_insurance_platform.policy.application.command.CreatePolicyCommand;
import com.assurant.insurance.core_insurance_platform.policy.application.usecase.ActivatePolicyUseCase;
import com.assurant.insurance.core_insurance_platform.policy.application.usecase.CancelPolicyUseCase;
import com.assurant.insurance.core_insurance_platform.policy.application.usecase.CreatePolicyUseCase;
import com.assurant.insurance.core_insurance_platform.policy.domain.model.PolicyId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/insurance/policies")
public class PolicyController {

    private final CreatePolicyUseCase createPolicyUseCase;
    private final ActivatePolicyUseCase activatePolicyUseCase;
    private final CancelPolicyUseCase cancelPolicyUseCase;

    public PolicyController(
            CreatePolicyUseCase createPolicyUseCase,
            ActivatePolicyUseCase activatePolicyUseCase,
            CancelPolicyUseCase cancelPolicyUseCase
    ) {
        this.createPolicyUseCase = createPolicyUseCase;
        this.activatePolicyUseCase = activatePolicyUseCase;
        this.cancelPolicyUseCase = cancelPolicyUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<CreatePolicyResponse> create(@RequestBody CreatePolicyRequest request) {

        CreatePolicyCommand command = new CreatePolicyCommand(
                request.policyHolderId(),
                request.coverageCodes(),
                request.premiumAmount()
        );

        PolicyId policyId = createPolicyUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreatePolicyResponse(policyId.value().toString()));
    }

    @PostMapping("/{policyId}/activate")
    public ResponseEntity<Void> activate(@PathVariable String policyId) {

        ActivatePolicyCommand command =
                new ActivatePolicyCommand(policyId);

        activatePolicyUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{policyId}/cancel")
    public ResponseEntity<Void> cancel(
            @PathVariable String policyId,
            @RequestBody CancelPolicyRequest request
    ) {

        CancelPolicyCommand command = new CancelPolicyCommand(
                policyId,
                request.cancellationReason(),
                request.cancellationDate()
        );

        cancelPolicyUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

}

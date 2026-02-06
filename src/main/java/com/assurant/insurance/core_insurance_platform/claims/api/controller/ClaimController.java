package com.assurant.insurance.core_insurance_platform.claims.api.controller;
import com.assurant.insurance.core_insurance_platform.claims.api.dto.CreateClaimRequest;
import com.assurant.insurance.core_insurance_platform.claims.api.dto.CreateClaimResponse;
import com.assurant.insurance.core_insurance_platform.claims.api.dto.RejectClaimRequest;
import com.assurant.insurance.core_insurance_platform.claims.application.command.*;
import com.assurant.insurance.core_insurance_platform.claims.application.usecase.*;
import com.assurant.insurance.core_insurance_platform.claims.domain.model.ClaimId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final CreateClaimUseCase createClaimUseCase;
    private final ApproveClaimUseCase approveClaimUseCase;
    private final RejectClaimUseCase rejectClaimUseCase;

    public ClaimController(
            CreateClaimUseCase createClaimUseCase,
            ApproveClaimUseCase approveClaimUseCase,
            RejectClaimUseCase rejectClaimUseCase
    ) {
        this.createClaimUseCase = createClaimUseCase;
        this.approveClaimUseCase = approveClaimUseCase;
        this.rejectClaimUseCase = rejectClaimUseCase;
    }

    // -------- CREATE CLAIM --------
    @PostMapping
    public ResponseEntity<CreateClaimResponse> create(
            @RequestBody CreateClaimRequest request
    ) {
        CreateClaimCommand command = new CreateClaimCommand(
                request.policyId(),
                request.amount()
        );

        ClaimId claimId = createClaimUseCase.execute(command);

        return ResponseEntity.ok(
                new CreateClaimResponse(claimId.value().toString())
        );
    }

    // -------- APPROVE CLAIM --------
    @PostMapping("/{claimId}/approve")
    public ResponseEntity<Void> approve(@PathVariable String claimId) {

        ApproveClaimCommand command =
                new ApproveClaimCommand(claimId);

        approveClaimUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

    // -------- REJECT CLAIM --------
    @PostMapping("/{claimId}/reject")
    public ResponseEntity<Void> reject(
            @PathVariable String claimId,
            @RequestBody RejectClaimRequest request
    ) {
        RejectClaimCommand command =
                new RejectClaimCommand(
                        claimId,
                        request.reason()
                );

        rejectClaimUseCase.execute(command);

        return ResponseEntity.noContent().build();
    }

}

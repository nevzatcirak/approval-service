package me.nevzatcirak.service.approval.controller.impl;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.service.ProcessService;
import me.nevzatcirak.service.approval.controller.ProcessController;
import me.nevzatcirak.service.approval.controller.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.model.QueryRequest;
import me.nevzatcirak.service.approval.controller.model.StateDetailUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@RestController
public class ApprovalProcessController implements ProcessController {
    private ProcessService processService;

    @Override
    public ResponseEntity<ApprovalProcess> createApprovalProcess(CreateApprovalRequest request) {
        Assert.notNull(request, "Approval process create request must not be null!");
        Assert.notNull(request.getId(), "Document id must not be null!");
        Assert.notNull(request.getType(), "Document service type must not be null!");
        Assert.notNull(request.getApprovers(), "Approvers of approval create request must not be null!");
        Assert.notEmpty(request.getApprovers(), "Approvers of approval create request must not be empty!");
        return ResponseEntity.ok(processService.create(request.getId(), request.getType(), request.getApprovers()));
    }

    @Override
    public ResponseEntity<ApprovalProcess> updateProcessState(String documentType, String documentId,
                                                              StateDetailUpdateRequest stateDetailUpdateRequest) {
        Assert.notNull(documentId, "Document id must not be null!");
        Assert.notNull(documentType, "Document service documentType must not be null!");
        Assert.notNull(stateDetailUpdateRequest, "Approval process update request must not be null!");
        Assert.notNull(stateDetailUpdateRequest.getUsername(), "Approver username must not be null!");
        Assert.notNull(stateDetailUpdateRequest.getStatus(), "Approver state action must not be null!");
        return ResponseEntity.ok(processService.update(documentId, documentType,
                stateDetailUpdateRequest.getUsername(), stateDetailUpdateRequest.getStatus()));
    }

    @Override
    public ResponseEntity<ApprovalProcess> updateProcessState(Long processId, StateDetailUpdateRequest stateDetailUpdateRequest) {
        Assert.notNull(processId, "Approval process id must not be null!");
        Assert.notNull(stateDetailUpdateRequest, "Approval process update request must not be null!");
        Assert.notNull(stateDetailUpdateRequest.getUsername(), "Approver username must not be null!");
        Assert.notNull(stateDetailUpdateRequest.getStatus(), "Approver state action must not be null!");
        return ResponseEntity.ok(processService.update(
                processId,
                stateDetailUpdateRequest.getUsername(),
                stateDetailUpdateRequest.getStatus()));
    }

    @Override
    public ResponseEntity<Set<ApprovalProcess>> queryProcessStatus(String documentType, QueryRequest queryRequest) {
        Assert.notNull(documentType, "Document service documentType must not be null!");
        Assert.notNull(queryRequest, "Query status payload must not be null!");
        Assert.notNull(queryRequest.isOnlyWaiting(), "Query status documentType must not be null!");
        Assert.notNull(queryRequest.getDocumentIds(), "Query status ID list must not be null!");
        return ResponseEntity.ok(processService.queryStatus(documentType, queryRequest.isOnlyWaiting(), queryRequest.getDocumentIds()));
    }

    @Override
    public ResponseEntity<Set<ApprovalProcess>> queryProcessStatusByUsername(String documentType, String username, QueryRequest queryRequest) {
        Assert.notNull(documentType, "Document service documentType must not be null!");
        Assert.hasLength(username, "Username must not be empty!");
        Assert.notNull(queryRequest, "Query status payload must not be null!");
        Assert.notNull(queryRequest.isOnlyWaiting(), "Query status documentType must not be null!");
        Assert.notNull(queryRequest.getDocumentIds(), "Query status ID list must not be null!");
        return ResponseEntity.ok(processService.queryStatus(documentType, queryRequest.isOnlyWaiting(), username, false, queryRequest.getDocumentIds()));
    }

    @Override
    public ResponseEntity<Set<ApprovalProcess>> queryProcessStatusByNextApproverUsername(String documentType, String username, QueryRequest queryRequest) {
        Assert.notNull(documentType, "Document service/document type must not be null!");
        Assert.hasLength(username, "Username must not be empty!");
        Assert.notNull(queryRequest, "Query status payload must not be null!");
        Assert.notNull(queryRequest.isOnlyWaiting(), "Query status document type must not be null!");
        Assert.notNull(queryRequest.getDocumentIds(), "Query status ID list must not be null!");
        return ResponseEntity.ok(processService.queryStatus(documentType, queryRequest.isOnlyWaiting(), username, true, queryRequest.getDocumentIds()));
    }

    @Override
    public ResponseEntity<ApprovalProcess> getApprovalProcess(String documentType, String documentId) {
        Assert.notNull(documentId, "Document documentId must not be null!");
        Assert.notNull(documentType, "Document service type must not be null!");
        return ResponseEntity.ok(processService.get(documentId, documentType));
    }

    @Override
    public ResponseEntity<ApprovalProcess> getApprovalProcess(Long processId) {
        Assert.notNull(processId, "Process id must not be null!");
        return ResponseEntity.ok(processService.get(processId));
    }

    @Override
    public ResponseEntity<Set<ApprovalProcess>> getApprovalProcesses(String documentType, ProcessRequestState status) {
        Assert.notNull(documentType, "Document service type must not be null!");
        Assert.notNull(status, "Filter state must not be null!");
        return ResponseEntity.ok(processService.getAllByFilteringStatus(documentType, toApprovalProcessState(status)));
    }

    @Override
    public ResponseEntity<Approver> getNextApprover(String documentType, String documentId) {
        Assert.notNull(documentId, "Document id must not be null!");
        Assert.notNull(documentType, "Document service type must not be null!");
        return ResponseEntity.ok(processService.nextApprover(documentId, documentType));
    }

    @Override
    public ResponseEntity<Approver> getNextApprover(Long processId) {
        Assert.notNull(processId, "Approval process id must not be null!");
        return ResponseEntity.ok(processService.nextApprover(processId));
    }

    private ApprovalProcessState toApprovalProcessState(ProcessRequestState state) {
        switch (state) {
            case waiting:
                return ApprovalProcessState.WAITING;
            case approved:
                return ApprovalProcessState.APPROVED;
            case rejected:
                return ApprovalProcessState.REJECTED;
            case all:
            default:
                return null;
        }
    }

    @Autowired
    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }
}

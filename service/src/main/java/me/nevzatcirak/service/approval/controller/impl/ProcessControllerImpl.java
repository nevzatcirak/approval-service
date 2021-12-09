package me.nevzatcirak.service.approval.controller.impl;

import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.service.ProcessService;
import me.nevzatcirak.service.approval.controller.ProcessController;
import me.nevzatcirak.service.approval.controller.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.model.StateDetailUpdateRequest;
import me.nevzatcirak.service.approval.controller.model.StatusQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@RestController
public class ProcessControllerImpl implements ProcessController {
    private ProcessService processService;

    @Override
    public ResponseEntity<?> createApprovalProcess(CreateApprovalRequest request) {
        Assert.notNull(request, "Approval process create request must not be null!");
        Assert.notNull(request.getId(), "Document id must not be null!");
        Assert.notNull(request.getType(), "Document service type must not be null!");
        Assert.notNull(request.getApprovers(), "Approvers of approval create request must not be null!");
        Assert.notEmpty(request.getApprovers(), "Approvers of approval create request must not be empty!");
        return ResponseEntity.ok(processService.create(request.getId(), request.getType(), request.getApprovers()));
    }

    @Override
    public ResponseEntity<?> updateProcessState(String type, String documentId,
                                                StateDetailUpdateRequest stateDetailUpdateRequest) {
        Assert.notNull(documentId, "Document id must not be null!");
        Assert.notNull(type, "Document service type must not be null!");
        Assert.notNull(stateDetailUpdateRequest, "Approval process update request must not be null!");
        Assert.notNull(stateDetailUpdateRequest.getUsername(), "Approver username must not be null!");
        Assert.notNull(stateDetailUpdateRequest.getStatus(), "Approver state action must not be null!");
        return ResponseEntity.ok(processService.update(documentId, type,
                stateDetailUpdateRequest.getUsername(), stateDetailUpdateRequest.getStatus()));
    }

    @Override
    public ResponseEntity<?> updateProcessState(Long processId, StateDetailUpdateRequest stateDetailUpdateRequest) {
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
    public ResponseEntity<?> queryProcessStatus(StatusQueryRequest queryRequest) {
        Assert.notNull(queryRequest, "Query status payload must not be null!");
        Assert.notNull(queryRequest.getType(), "Query status type must not be null!");
        Assert.notNull(queryRequest.getIdList(), "Query status ID list must not be null!");
        return ResponseEntity.ok(processService.queryStatus(queryRequest.getType(), queryRequest.getIdList()));
    }

    @Override
    public ResponseEntity<?> getApprovalProcess(String type, String id) {
        Assert.notNull(id, "Document id must not be null!");
        Assert.notNull(type, "Document service type must not be null!");
        return ResponseEntity.ok(processService.getBy(id, type));
    }

    @Override
    public ResponseEntity<?> getApprovalProcess(Long processId) {
        Assert.notNull(processId, "Process id must not be null!");
        return ResponseEntity.ok(processService.getBy(processId));
    }

    @Override
    public ResponseEntity<?> getApprovalProcesses(String type, ProcessRequestState status) {
        Assert.notNull(type, "Document service type must not be null!");
        Assert.notNull(status, "Filter state must not be null!");
        return ResponseEntity.ok(processService.getAllByFilteringStatus(type, toApprovalProcessState(status)));
    }

    @Override
    public ResponseEntity<?> getNextApprover(String type, String documentId) {
        Assert.notNull(documentId, "Document id must not be null!");
        Assert.notNull(type, "Document service type must not be null!");
        return ResponseEntity.ok(processService.nextApprover(documentId, type));
    }

    @Override
    public ResponseEntity<?> getNextApprover(Long processId) {
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
            default:
                return null;
        }
    }

    @Autowired
    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }
}

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
        return ResponseEntity.ok(processService.create(request.getId(), request.getType(), request.getApprovers()));
    }

    @Override
    public ResponseEntity<?> updateProcessState(String type, String documentId,
                                                StateDetailUpdateRequest stateDetailUpdateRequest) {
        return ResponseEntity.ok(processService.update(documentId, type,
                stateDetailUpdateRequest.getUsername(), stateDetailUpdateRequest.getStatus()));
    }

    @Override
    public ResponseEntity<?> updateProcessState(String processId, StateDetailUpdateRequest stateDetailUpdateRequest) {
        return ResponseEntity.ok(processService.update(
                processId,
                stateDetailUpdateRequest.getUsername(),
                stateDetailUpdateRequest.getStatus()));
    }

    @Override
    public ResponseEntity<?> queryProcessStatus(StatusQueryRequest queryRequest) {
        return ResponseEntity.ok(processService.queryStatus(queryRequest.getType(), queryRequest.getIdList()));
    }

    @Override
    public ResponseEntity<?> getApprovalProcesses(String type, String documentId, ProcessRequestState status) {
        return ResponseEntity.ok(processService.getAllByFilteringStatus(documentId, type, toApprovalProcessState(status)));
    }

    @Override
    public ResponseEntity<?> getApprovalProcesses(String processId, ProcessRequestState status) {
        return ResponseEntity.ok(processService.getAllByFilteringStatus(processId, toApprovalProcessState(status)));
    }

    @Override
    public ResponseEntity<?> getNextApprover(String type, String documentId) {
        return ResponseEntity.ok(processService.nextApprover(documentId, type));
    }

    @Override
    public ResponseEntity<?> getNextApprover(String processId) {
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

package me.nevzatcirak.service.approval.controller.impl;

import me.nevzatcirak.service.approval.controller.ProcessController;
import me.nevzatcirak.service.approval.controller.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.model.StateDetailUpdateRequest;
import me.nevzatcirak.service.approval.controller.model.StatusQueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@RestController
public class ProcessControllerImpl implements ProcessController {
    @Override
    public ResponseEntity<?> createApprovalProcess(CreateApprovalRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateProcessState(String type, String documentId, StateDetailUpdateRequest stateDetailUpdateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> queryProcessStatus(StatusQueryRequest queryRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateProcessState(String processId, StateDetailUpdateRequest stateDetailUpdateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> getApprovalProcesses(String type, String documentId, ProcessRequestState status) {
        return null;
    }

    @Override
    public ResponseEntity<?> getApprovalProcesses(String processId, ProcessRequestState status) {
        return null;
    }

    @Override
    public ResponseEntity<?> getNextApprover(String type, String documentId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getNextApprover(String processId) {
        return null;
    }
}

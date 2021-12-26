package me.nevzatcirak.service.approval.controller.graphql.resolver;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.service.ProcessService;
import me.nevzatcirak.service.approval.controller.graphql.ProcessQueryResolver;
import me.nevzatcirak.service.approval.controller.rest.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.rest.model.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK,
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 12/24/2021.
 */
public class QueryResolver implements ProcessQueryResolver {
    private ProcessService processService;

    @Override
    public Set<ApprovalProcess> getApprovalProcesses(String documentType, ProcessRequestState status) {
        return processService.getAllByFilteringStatus(documentType, toApprovalProcessState(status));
    }

    @Override
    public ApprovalProcess getApprovalProcess(String documentType, String documentId) {
        return processService.get(documentType, documentId);
    }

    @Override
    public ApprovalProcess getApprovalProcessByProcessId(String processId) {
        return processService.get(Long.getLong(processId));
    }

    @Override
    public Approver getNextApprover(String documentType, String documentId) {
        return processService.nextApprover(documentType, documentId);
    }

    @Override
    public Approver getNextApprover(String processId) {
        return processService.nextApprover(Long.getLong(processId));
    }

    @Override
    public Set<ApprovalProcess> query(String documentType, QueryRequest request) {
        return processService.queryStatus(documentType, request.isOnlyWaiting(), request.getDocumentIds());
    }

    @Override
    public Set<ApprovalProcess> queryByUsername(String documentType, String username, QueryRequest request) {
        return processService.queryStatus(documentType, request.isOnlyWaiting(),
                username, false, request.getDocumentIds());
    }

    @Override
    public Set<ApprovalProcess> queryByNextApprover(String documentType, String username, QueryRequest request) {
        return processService.queryStatus(documentType, request.isOnlyWaiting(),
                username, true, request.getDocumentIds());
    }

    @Override
    public Set<ApprovalProcess> queryByEligibleUser(String documentType, String username, QueryRequest request) {
        return processService.queryStatusByUsingEligibility(documentType, request.isOnlyWaiting(),
                username, request.getDocumentIds());
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
    private void setProcessService(ProcessService processService) {
        this.processService = processService;
    }
}

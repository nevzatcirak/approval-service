package me.nevzatcirak.service.approval.controller.graphql.resolver;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.service.ProcessService;
import me.nevzatcirak.service.approval.controller.graphql.ProcessMutationResolver;
import me.nevzatcirak.service.approval.controller.rest.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.rest.model.StateDetailUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nevzat ÇIRAK,
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 12/24/2021.
 */
public class MutationResolver implements ProcessMutationResolver {
    private ProcessService processService;

    @Override
    public ApprovalProcess create(CreateApprovalRequest request) {
        return processService.create(request.getId(), request.getType(), request.getApprovers());
    }

    @Override
    public ApprovalProcess update(String documentType, String documentId,
                                  StateDetailUpdateRequest stateDetailUpdateRequest) {
        return processService.update(documentId, documentType, stateDetailUpdateRequest.getUsername(),
                stateDetailUpdateRequest.getComment(), stateDetailUpdateRequest.getStatus());
    }

    @Override
    public ApprovalProcess update(String processId, StateDetailUpdateRequest stateDetailUpdateRequest) {
        return processService.update(Long.getLong(processId), stateDetailUpdateRequest.getUsername(),
                stateDetailUpdateRequest.getComment(), stateDetailUpdateRequest.getStatus());
    }

    @Autowired
    private void setProcessService(ProcessService processService) {
        this.processService = processService;
    }
}

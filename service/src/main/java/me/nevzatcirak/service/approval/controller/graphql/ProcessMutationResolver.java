package me.nevzatcirak.service.approval.controller.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.controller.rest.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.rest.model.StateDetailUpdateRequest;

/**
 * @author Nevzat ÇIRAK,
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 12/24/2021.
 */
public interface ProcessMutationResolver extends GraphQLMutationResolver {
    ApprovalProcess create(CreateApprovalRequest request);

    ApprovalProcess update(String documentType, String documentId, StateDetailUpdateRequest stateDetailUpdateRequest);

    ApprovalProcess update(String processId, StateDetailUpdateRequest stateDetailUpdateRequest);
}

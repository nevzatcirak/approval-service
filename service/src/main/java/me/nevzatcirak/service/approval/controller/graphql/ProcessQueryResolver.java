package me.nevzatcirak.service.approval.controller.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.controller.rest.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.rest.model.QueryRequest;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK,
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 12/24/2021.
 */
public interface ProcessQueryResolver extends GraphQLQueryResolver {
    Set<ApprovalProcess> getApprovalProcesses(String documentType, ProcessRequestState status);

    ApprovalProcess getApprovalProcess(String documentType, String documentId);

    ApprovalProcess getApprovalProcessByProcessId(String processId);

    Approver getNextApprover(String documentType, String documentId);

    Approver getNextApprover(String processId);

    Set<ApprovalProcess> query(String documentType, QueryRequest request);

    Set<ApprovalProcess> queryByUsername(String documentType, String username, QueryRequest request);

    Set<ApprovalProcess> queryByNextApprover(String documentType, String username, QueryRequest request);

    Set<ApprovalProcess> queryByEligibleUser(String documentType, String username, QueryRequest request);
}

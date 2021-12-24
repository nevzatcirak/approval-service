package me.nevzatcirak.service.approval.controller.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
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
public class QueryResolver implements ProcessQueryResolver, GraphQLQueryResolver {
    private ProcessService processService;

    @Override
    public ApprovalProcess getApprovalProcess(String documentType, ProcessRequestState status) {
        return null;
    }

    @Override
    public ApprovalProcess getApprovalProcessByProcessId(String processId, ProcessRequestState status) {
        return null;
    }

    @Override
    public ApprovalProcess getApprovalProcess(String documentType, String documentId) {
        return null;
    }

    @Override
    public ApprovalProcess getApprovalProcessByProcessId(String processId) {
        return null;
    }

    @Override
    public Approver getNextApprover(String documentType, String documentId) {
        return null;
    }

    @Override
    public Approver getNextApprover(String processId) {
        return null;
    }

    @Override
    public Set<ApprovalProcess> query(String documentType, QueryRequest request) {
        return null;
    }

    @Override
    public Set<ApprovalProcess> queryByUsername(String documentType, String username, QueryRequest request) {
        return null;
    }

    @Override
    public Set<ApprovalProcess> queryByNextApprover(String documentType, String username, QueryRequest request) {
        return null;
    }

    @Override
    public Set<ApprovalProcess> queryByEligibleUser(String documentType, String username, QueryRequest request) {
        return null;
    }

    @Autowired
    private void setProcessService(ProcessService processService) {
        this.processService = processService;
    }
}

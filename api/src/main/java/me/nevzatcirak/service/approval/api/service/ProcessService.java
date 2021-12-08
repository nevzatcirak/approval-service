package me.nevzatcirak.service.approval.api.service;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.model.ApproverSummary;

import java.util.Map;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public interface ProcessService {
    /**
     * Creates new approval process by using Service type, Document Id and Approver Info
     *
     * @param documentId
     * @param documentType
     * @param approvers
     * @return ApprovalProcess
     */
    ApprovalProcess create(String documentId, String documentType, Set<ApproverSummary> approvers);

    /**
     * Update a process detail state by using username and status
     *
     * @param documentId
     * @param documentType
     * @param username
     * @param status
     * @return ApprovalProcess
     */
    ApprovalProcess update(String documentId, String documentType, String username, ApprovalProcessState status);

    /**
     * Update a process detail state by using username and status
     *
     * @param processId
     * @param username
     * @param status
     * @return ApprovalProcess
     */
    ApprovalProcess update(String processId, String username, ApprovalProcessState status);

    /**
     * Query type statuses by filtering id list
     *
     * @param documentType
     * @param documentIds
     * @return Map<DocumentId, ProcessState>
     */
    Map<String, ApprovalProcessState> queryStatus(String documentType, Set<String> documentIds);

    /**
     * Gets process list by filtering final status
     *
     * @param documentType
     * @param processState
     * @return Set<ApprovalProcess>
     */
    Set<ApprovalProcess> getAllByFilteringStatus(String documentType, ApprovalProcessState processState);

    /**
     * Gets next approver data of defined process
     *
     * @param documentId
     * @param documentType
     * @return Approver
     */
    Approver nextApprover(String documentId, String documentType);

    /**
     * Gets next approver data of defined process
     *
     * @param processId
     * @return Approver
     */
    Approver nextApprover(String processId);
}

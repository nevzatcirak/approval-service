package me.nevzatcirak.service.approval.api.service;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.model.ApproverSummary;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 07/12/2021
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
     * Creates new approval process by using Service type, Document Id and Approver Info
     *
     * @param documentId
     * @param documentType
     * @param approvers
     * @return ApprovalProcess
     */
    ApprovalProcess create(String documentId, String documentType, String creator, ApprovalProcessState status, Set<ApproverSummary> approvers);

    /**
     * Update a process detail state by using username and status
     *
     * @param documentId
     * @param documentType
     * @param username
     * @param status
     * @return ApprovalProcess
     */
    ApprovalProcess update(String documentId, String documentType, String username, String comment, ApprovalProcessState status);

    /**
     * Update a process detail state by using username and status
     *
     * @param processId
     * @param username
     * @param status
     * @return ApprovalProcess
     */
    ApprovalProcess update(Long processId, String username, String comment, ApprovalProcessState status);

    /**
     * Cancel related approval process
     *
     * @param processId
     * @return ApprovalProcess
     */
    ApprovalProcess cancel(Long processId);

    /**
     * Cancel related approval process
     *
     * @param documentId
     * @param documentType
     * @return ApprovalProcess
     */
    ApprovalProcess cancel(String documentId, String documentType);

    /**
     * Gets process by filtering document id and type
     *
     * @param documentId
     * @param documentType
     * @return ApprovalProcess
     */
    ApprovalProcess get(String documentId, String documentType);

    /**
     * Gets process by filtering process id
     *
     * @param processId
     * @return ApprovalProcess
     */
    ApprovalProcess get(Long processId);

    /**
     * Query type statuses by filtering id list and waiting status
     *
     * @param documentType
     * @param onlyWaiting
     * @param documentIds
     * @return Set<ApprovalProcess>
     */
    Set<ApprovalProcess> queryStatus(String documentType, Boolean onlyWaiting, Set<String> documentIds);

    /**
     * Query type statuses by filtering id list, waiting status and username
     *
     * @param documentType
     * @param onlyWaiting
     * @param username
     * @param onlyNextApprover
     * @param documentIds
     * @return Set<ApprovalProcess>
     */
    Set<ApprovalProcess> queryStatus(String documentType, Boolean onlyWaiting, String username, Boolean onlyNextApprover, Set<String> documentIds);

    /**
     * Query type statuses by filtering id list, waiting status and username eligibility
     *
     * @param documentType
     * @param onlyWaiting
     * @param username
     * @param documentIds
     * @return Set<ApprovalProcess>
     */
    Set<ApprovalProcess> queryStatusByUsingEligibility(String documentType, Boolean onlyWaiting, String username, Set<String> documentIds);

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
    Approver nextApprover(Long processId);
}

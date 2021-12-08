package me.nevzatcirak.service.approval.api.repository;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public interface ProcessRepository {
    /**
     * Saves new approval process to related Database
     *
     * @param approvalProcess
     * @return Created ApprovalProcess Object
     */
    ApprovalProcess save(ApprovalProcess approvalProcess);

    /**
     * Updates new approval process to related Database
     *
     * @param approvalProcess
     * @return Updated ApprovalProcess Object
     */
    ApprovalProcess update(ApprovalProcess approvalProcess);

    /**
     * Retrieves data by using documentId and document type from related database
     *
     * @param documentId
     * @param documentType
     * @return Found ApprovalProcess Object
     */
    ApprovalProcess findBy(String documentId, String documentType);

    /**
     * Retrieves data by using processId from related database
     *
     * @param processId
     * @return Found ApprovalProcess Object
     */
    ApprovalProcess findBy(String processId);

    /**
     * Retrieves data by using document type and document id set from related database
     *
     * @param documentType
     * @param documentIds
     * @return Found Set of ApprovalProcess Object
     */
    Set<ApprovalProcess> findAllBy(String documentType, Set<String> documentIds);

    /**
     * Retrieves data by filtering documentType and process state from related database
     *
     * @param documentType
     * @param state
     * @return Found Set of ApprovalProcess Object
     */
    Set<ApprovalProcess> findAllBy(String documentType, ApprovalProcessState state);


    /**
     * Retrieves data by filtering documentType from related database
     *
     * @param documentType
     * @return Found Set of ApprovalProcess Object
     */
    Set<ApprovalProcess> findAllBy(String documentType);

    /**
     * Retrieves next approver data of process by using documentId and documentType. If Last approver approved or rejected
     * process, next approver's status field will be filled as APPROVED or REJECTED. Otherwise, that field will be filled as
     * WAITING.
     *
     * @param documentId
     * @param documentType
     * @return Approver
     */
    Approver findProcessNextApprover(String documentId, String documentType);

    /**
     * Retrieves next approver data of process by using processId. If Last approver approved or rejected
     * process, next approver's status field will be filled as APPROVED or REJECTED. Otherwise, that field will be filled as
     * WAITING.
     *
     * @param processId
     * @return Approver
     */
    Approver findProcessNextApprover(String processId);

}

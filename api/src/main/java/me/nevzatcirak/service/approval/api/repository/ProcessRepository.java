package me.nevzatcirak.service.approval.api.repository;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;

import java.util.List;
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
    ApprovalProcess findBy(Long processId);

    /**
     * Retrieves data by using document type and document id set from related database
     *
     * @param documentType
     * @param documentIds
     * @return Found Set of ApprovalProcess Object
     */
    Set<ApprovalProcess> findAllBy(String documentType, Set<String> documentIds);

    /**
     * Retrieves data by using document type and document id set from related database
     *
     * @param documentType
     * @param status
     * @param documentIds
     * @return Found Set of ApprovalProcess Object
     */
    Set<ApprovalProcess> findAllBy(String documentType, ApprovalProcessState status, Set<String> documentIds);

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
     * Finds approval process by using type and processIds
     *
     * @param documentType
     * @param legitProcessIds
     * @return Approval Process
     */
    Set<ApprovalProcess> findAllBy(String documentType, List<Long> legitProcessIds);

    /**
     * Finds approval process by using type, documentIds and processIds
     *
     * @param documentType
     * @param documentIds
     * @param legitProcessIds
     * @return Approval Process
     */
    Set<ApprovalProcess> findAllBy(String documentType, Set<String> documentIds, List<Long> legitProcessIds);


    /**
     * Retrieves data by filtering documentType, eligible username, process state and legitProcessIds from related database
     *
     * @param documentType
     * @param username
     * @param state
     * @param legitProcessIds
     * @return Found Set of ApprovalProcess Object
     */
    Set<ApprovalProcess> findAllEligibleBy(String documentType, String username,
                                           ApprovalProcessState state, List<Long> legitProcessIds);

    /**
     * Retrieves data by filtering documentType, eligible username and legitProcessIds from related database
     *
     * @param documentType
     * @param username
     * @param legitProcessIds
     * @return Found Set of ApprovalProcess Object
     */
    Set<ApprovalProcess> findAllEligibleBy(String documentType, String username, List<Long> legitProcessIds);

    /**
     * Finds approval process by using type, eligible username, documentIds and legitProcessIds
     *
     * @param documentType
     * @param username
     * @param documentIds
     * @param legitProcessIds
     * @return Approval Process
     */
    Set<ApprovalProcess> findAllEligibleBy(String documentType, String username,
                                           Set<String> documentIds, List<Long> legitProcessIds);

    /**
     * Finds approval process by using type, eligible username, state, documentIds and legitProcessIds
     *
     * @param documentType
     * @param username
     * @param state
     * @param documentIds
     * @param legitProcessIds
     * @return Approval Process
     */
    Set<ApprovalProcess> findAllEligibleBy(String documentType,
                                           String username,
                                           ApprovalProcessState state,
                                           Set<String> documentIds,
                                           List<Long> legitProcessIds);
}

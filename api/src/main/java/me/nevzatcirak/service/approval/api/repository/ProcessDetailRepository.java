package me.nevzatcirak.service.approval.api.repository;

import me.nevzatcirak.service.approval.api.model.Approver;

import java.util.List;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 09/12/2021
 */
public interface ProcessDetailRepository {
    /**
     * Saves new approver to related Database
     *
     * @param approver
     * @return Approver
     */
    Approver save(Approver approver);

    /**
     * Updates existed approver data
     *
     * @param approver
     * @return Approver
     */
    Approver update(Approver approver);

    /**
     * Finds next approver of a process
     *
     * @param processId
     * @return Next Approver
     */
    Approver nextApprover(Long processId);

    /**
     * Finds process ids by using username who is next approver
     *
     * @param username
     * @return List of Legit Process Id
     */
    List<Long> findProcessIdsByNextApproverUsername(String username);

    /**
     * Finds process ids by using username
     *
     * @param username
     * @return List of Legit Process Id
     */
    List<Long> findProcessIdsByUsername(String username);
}

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
     * Finds next approver of a process in provided approver ids.
     *
     * @param approverIds
     * @return Next Approver
     */
    Approver nextApprover(List<Long> approverIds);
}

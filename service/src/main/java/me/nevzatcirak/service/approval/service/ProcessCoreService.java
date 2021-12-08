package me.nevzatcirak.service.approval.service;

import me.nevzatcirak.service.approval.api.exception.ApprovalProcessNotFoundException;
import me.nevzatcirak.service.approval.api.exception.ApproverNotFoundException;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.model.ApproverSummary;
import me.nevzatcirak.service.approval.api.repository.ProcessRepository;
import me.nevzatcirak.service.approval.api.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Manages approval process core logic
 *
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@Service
public class ProcessCoreService implements ProcessService {
    private ProcessRepository processRepository;

    @Override
    public ApprovalProcess create(String documentId, String documentType, Set<ApproverSummary> approvers) {
        Set<Approver> processDetails = new LinkedHashSet<>();
        approvers.forEach(approver -> processDetails.add(new Approver()
                .setUsername(approver.getUsername())
                .setSequenceNumber(approver.getSequenceNumber())
                .setStatus(ApprovalProcessState.WAITING)));
        ApprovalProcess approvalProcess = new ApprovalProcess()
                .setDocumentId(documentId)
                .setDocumentType(documentType)
                .setStatus(ApprovalProcessState.WAITING)
                .setDetail(processDetails);
        return processRepository.save(approvalProcess);
    }

    @Override
    public ApprovalProcess update(String documentId, String documentType, String username, ApprovalProcessState status) {
        ApprovalProcess process = processRepository.findBy(documentId, documentType);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using documentId: " + documentId + ", documentType: " + documentType);
        }
        ApprovalProcess updatedApprovalProcess = this.updateProcess(process, username, status);
        if (Objects.nonNull(updatedApprovalProcess))
            return updatedApprovalProcess;
        throw new ApproverNotFoundException("Not found an approver username(" + username + ") in related process.");
    }

    @Override
    public ApprovalProcess update(String processId, String username, ApprovalProcessState status) {
        ApprovalProcess process = processRepository.findBy(processId);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using processId: " + processId);
        }
        ApprovalProcess updatedApprovalProcess = this.updateProcess(process, username, status);
        if (Objects.nonNull(updatedApprovalProcess))
            return updatedApprovalProcess;
        throw new ApproverNotFoundException("Not found an approver username(" + username + ") in related process.");
    }

    @Override
    public Map<String, ApprovalProcessState> queryStatus(String documentType, Set<String> documentIds) {
        Set<ApprovalProcess> approvalProcessSet = processRepository.findAllBy(documentType, documentIds);
        if (Objects.isNull(approvalProcessSet)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using idList = " + String.join(",", documentIds));
        }
        return convertMap(approvalProcessSet);
    }

    @Override
    public Set<ApprovalProcess> getAllByFilteringStatus(String documentType, ApprovalProcessState processState) {
        Set<ApprovalProcess> approvalProcessSet;
        if (Objects.isNull(processState))
            approvalProcessSet = processRepository.findAllBy(documentType);
        else
            approvalProcessSet = processRepository.findAllBy(documentType, processState);
        if (Objects.isNull(approvalProcessSet)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using documentType: " + documentType);
        }
        return approvalProcessSet;
    }

    @Override
    public Approver nextApprover(String documentId, String documentType) {
        Approver approver = processRepository.findProcessNextApprover(documentId, documentType);
        if (Objects.isNull(approver))
            throw new ApproverNotFoundException("Not found an approver in process which is defined by documentId=" + documentId + ", documentType=" + documentType);
        return approver;
    }

    @Override
    public Approver nextApprover(String processId) {
        Approver approver = processRepository.findProcessNextApprover(processId);
        if (Objects.isNull(approver))
            throw new ApproverNotFoundException("Not found an approver in process which is defined by processId=" + processId);
        return approver;
    }

    private ApprovalProcess updateProcess(ApprovalProcess process, String username, ApprovalProcessState status) {
        // processRepository.findProcessNextApprover(process.getId()) @TODO Check username is next appprover
        for (Approver detail : process.getDetail()) {
            if (detail.getUsername().equals(username)) {
                process.setStatus(status);
                return processRepository.update(process);
            }
        }
        return null;
    }

    private Map<String, ApprovalProcessState> convertMap(Set<ApprovalProcess> approvalProcessSet) {
        Map<String, ApprovalProcessState> processMap = new HashMap<>();
        approvalProcessSet.forEach(process -> {
            processMap.put(process.getDocumentId(), process.getStatus());
        });
        return processMap;
    }

    @Autowired
    public void setProcessRepository(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }
}

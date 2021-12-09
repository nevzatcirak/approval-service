package me.nevzatcirak.service.approval.service;

import me.nevzatcirak.service.approval.api.exception.ApprovalProcessNotFoundException;
import me.nevzatcirak.service.approval.api.exception.ApproverNotFoundException;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.model.ApproverSummary;
import me.nevzatcirak.service.approval.api.repository.ProcessDetailRepository;
import me.nevzatcirak.service.approval.api.repository.ProcessRepository;
import me.nevzatcirak.service.approval.api.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

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
    private ProcessDetailRepository detailRepository;

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
        return this.updateProcess(process, username, status);
    }

    @Override
    public ApprovalProcess getBy(String documentId, String documentType) {
        ApprovalProcess process = processRepository.findBy(documentId, documentType);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using documentId: " + documentId + ", documentType: " + documentType);
        }
        return process;
    }

    @Override
    public ApprovalProcess getBy(Long processId) {
        ApprovalProcess process = processRepository.findBy(processId);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using processId: " + processId);
        }
        return process;
    }

    @Override
    public ApprovalProcess update(Long processId, String username, ApprovalProcessState status) {
        ApprovalProcess process = processRepository.findBy(processId);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using processId: " + processId);
        }
        return this.updateProcess(process, username, status);
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
        ApprovalProcess process = processRepository.findBy(documentId, documentType);
        if (Objects.isNull(process))
            throw new ApprovalProcessNotFoundException("Not found approval process object by using documentId=" + documentId + ", documentType=" + documentType);
        Approver nextApprover = findNextApprover(process);
        if (Objects.isNull(nextApprover))
            throw new ApproverNotFoundException("Not found an approver in process which is defined by documentId=" + documentId + ", documentType=" + documentType);
        return nextApprover;
    }

    @Override
    public Approver nextApprover(Long processId) {
        ApprovalProcess process = processRepository.findBy(processId);
        if (Objects.isNull(process))
            throw new ApprovalProcessNotFoundException("Not found approval process object by using processId=" + processId);
        Approver nextApprover = findNextApprover(process);
        if (Objects.isNull(nextApprover))
            throw new ApproverNotFoundException("Not found an approver in process which is defined by processId=" + processId);
        return nextApprover;
    }

    private ApprovalProcess updateProcess(ApprovalProcess process, String username, ApprovalProcessState status) {
        Approver approver = nextApprover(process.getId());
        if (Objects.nonNull(approver) && approver.getUsername().equals(username))
            for (Approver detail : process.getDetail()) {
                if (detail.getUsername().equals(username)) {
                    detail.setStatus(status);
                    detailRepository.update(detail);
                    boolean isAllApproved = process.getDetail().stream().allMatch(approvalDetail
                            -> approvalDetail.getStatus().equals(ApprovalProcessState.APPROVED));
                    if (status.equals(ApprovalProcessState.REJECTED) || isAllApproved)
                        process.setStatus(status);
                    else
                        process.setStatus(ApprovalProcessState.WAITING);
                    return processRepository.update(process);
                }
            }
        throw new ApproverNotFoundException("Not found an approver username(" + username + ") in related process, or approver is not the next approver.");
    }

    private Approver findNextApprover(ApprovalProcess process) {
        List<Long> approverIds = process.getDetail().stream().map(Approver::getId).collect(Collectors.toList());
        return detailRepository.nextApprover(approverIds);
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

    @Autowired
    public void setDetailRepository(ProcessDetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }
}

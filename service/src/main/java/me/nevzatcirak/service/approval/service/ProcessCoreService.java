package me.nevzatcirak.service.approval.service;

import me.nevzatcirak.service.approval.api.exception.ApprovalProcessNotFoundException;
import me.nevzatcirak.service.approval.api.exception.ApproverAlreadyApprovedException;
import me.nevzatcirak.service.approval.api.exception.ApproverAlreadyRejectedException;
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

/**
 * Manages approval process core logic
 *
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 07/12/2021
 */
@Service
public class ProcessCoreService implements ProcessService {
    private ProcessRepository processRepository;
    private ProcessDetailRepository detailRepository;

    @Override
    public ApprovalProcess create(String documentId, String documentType, Set<ApproverSummary> approvers) {
        Set<Approver> processDetails = new LinkedHashSet<>();
        approvers.forEach(approver -> {
            Assert.notNull(approver.getUsername(), "Approver username must not be null!");
            Assert.notNull(approver.getSequenceNumber(), "Approver sequence number must not be null!");
            processDetails.add(new Approver()
                    .setUsername(approver.getUsername().trim())
                    .setSequenceNumber(approver.getSequenceNumber())
                    .setStatus(ApprovalProcessState.WAITING));
        });
        ApprovalProcess approvalProcess = new ApprovalProcess()
                .setDocumentId(documentId.trim())
                .setDocumentType(documentType.trim())
                .setStatus(ApprovalProcessState.WAITING)
                .setApprovers(processDetails);
        return processRepository.save(approvalProcess);
    }

    @Override
    public ApprovalProcess create(String documentId, String documentType, String creator, Set<ApproverSummary> approvers) {
        Set<Approver> processDetails = new LinkedHashSet<>();
        approvers.forEach(approver -> {
            Assert.notNull(approver.getUsername(), "Approver username must not be null!");
            Assert.notNull(approver.getSequenceNumber(), "Approver sequence number must not be null!");
            processDetails.add(new Approver()
                    .setUsername(approver.getUsername().trim())
                    .setSequenceNumber(approver.getSequenceNumber())
                    .setStatus(ApprovalProcessState.WAITING));
        });
        ApprovalProcess approvalProcess = new ApprovalProcess()
                .setDocumentId(documentId.trim())
                .setDocumentType(documentType.trim())
                .setStatus(ApprovalProcessState.WAITING)
                .setCreator(creator)
                .setApprovers(processDetails);
        return processRepository.save(approvalProcess);
    }

    @Override
    public ApprovalProcess update(String documentId, String documentType, String username, String comment, ApprovalProcessState status) {
        ApprovalProcess process = processRepository.findBy(documentId, documentType);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using documentId: " + documentId + ", documentType: " + documentType);
        }
        return this.updateProcess(process, username, comment, status);
    }

    @Override
    public ApprovalProcess update(Long processId, String username, String comment, ApprovalProcessState status) {
        ApprovalProcess process = processRepository.findBy(processId);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using processId: " + processId);
        }
        return this.updateProcess(process, username, comment, status);
    }

    @Override
    public ApprovalProcess cancel(Long processId) {
        ApprovalProcess process = processRepository.findBy(processId);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using processId: " + processId);
        }
        process.setCanceled(true);
        return processRepository.save(process);
    }

    @Override
    public ApprovalProcess cancel(String documentId, String documentType) {
        ApprovalProcess process = processRepository.findBy(documentId, documentType);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using documentId: " + documentId + ", documentType: " + documentType);
        }
        process.setCanceled(true);
        return processRepository.save(process);
    }

    @Override
    public ApprovalProcess get(String documentId, String documentType) {
        ApprovalProcess process = processRepository.findBy(documentId, documentType);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using documentId: " + documentId + ", documentType: " + documentType);
        }
        return process;
    }

    @Override
    public ApprovalProcess get(Long processId) {
        ApprovalProcess process = processRepository.findBy(processId);
        if (Objects.isNull(process)) {
            throw new ApprovalProcessNotFoundException("Not found approval process object by using processId: " + processId);
        }
        return process;
    }

    @Override
    public Set<ApprovalProcess> queryStatus(String documentType, Boolean onlyWaiting, Set<String> documentIds) {
        Set<ApprovalProcess> approvalProcessSet;
        if (documentIds.isEmpty())
            approvalProcessSet = onlyWaiting ?
                    processRepository.findAllBy(documentType, ApprovalProcessState.WAITING) :
                    processRepository.findAllBy(documentType);
        else
            approvalProcessSet = onlyWaiting ?
                    processRepository.findAllBy(documentType, ApprovalProcessState.WAITING, documentIds) :
                    processRepository.findAllBy(documentType, documentIds);
        if (Objects.isNull(approvalProcessSet))
            throw new ApprovalProcessNotFoundException("Not found any approval process object by using idList = " + String.join(",", documentIds));
        return approvalProcessSet;
    }

    @Override
    public Set<ApprovalProcess> queryStatus(String documentType, Boolean onlyWaiting,
                                            String username, Boolean onlyNextApprover, Set<String> documentIds) {
        Set<ApprovalProcess> approvalProcessSet;
        List<Long> legitProcessIds = onlyNextApprover ? detailRepository.findProcessIdsByNextApproverUsername(username) :
                detailRepository.findProcessIdsByUsername(username);
        approvalProcessSet = documentIds.isEmpty() ?
                processRepository.findAllBy(documentType, legitProcessIds) :
                processRepository.findAllBy(documentType, documentIds, legitProcessIds);
        if (Objects.isNull(approvalProcessSet))
            throw new ApprovalProcessNotFoundException("Not found any approval process object by using idList = " + String.join(",", documentIds));
        return approvalProcessSet;
    }

    @Override
    public Set<ApprovalProcess> queryStatusByUsingEligibility(String documentType, Boolean onlyWaiting, String username, Set<String> documentIds) {
        Set<ApprovalProcess> approvalProcessSet;
        List<Long> legitProcessIds = detailRepository.findProcessIdsByEligibleUsername(username);
        if (documentIds.isEmpty())
            approvalProcessSet = onlyWaiting ?
                    processRepository.findAllEligibleBy(documentType, username, ApprovalProcessState.WAITING, legitProcessIds) :
                    processRepository.findAllEligibleBy(documentType, username, legitProcessIds);
        else
            approvalProcessSet = onlyWaiting ?
                    processRepository.findAllEligibleBy(documentType, username, ApprovalProcessState.WAITING, documentIds, legitProcessIds) :
                    processRepository.findAllEligibleBy(documentType, username, documentIds, legitProcessIds);
        if (Objects.isNull(approvalProcessSet))
            throw new ApprovalProcessNotFoundException("Not found any approval process object by using idList = " + String.join(",", documentIds));
        return approvalProcessSet;
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

    private ApprovalProcess updateProcess(ApprovalProcess process, String username, String comment, ApprovalProcessState status) {
        Approver approver = nextApprover(process.getId());
        Set<Approver> approvers = process.getApprovers();
        if (Objects.nonNull(approver) && approver.getUsername().equals(username))
            for (Approver currentApprover : approvers) {
                if (currentApprover.getUsername().equals(username)) {
                    currentApprover.setStatus(status);
                    currentApprover.setActive(false);
                    currentApprover.setComment(comment);
                    currentApprover.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
                    Approver updatedCurrentApprover = detailRepository.update(currentApprover);
                    boolean isAllApproved = approvers.stream().allMatch(approvalDetail
                            -> approvalDetail.getStatus().equals(ApprovalProcessState.APPROVED));
                    if (status.equals(ApprovalProcessState.REJECTED) || isAllApproved) {
                        process.setStatus(status);
                    } else {
                        saveNextApproverActiveStatus(updatedCurrentApprover, approvers);
                        process.setStatus(ApprovalProcessState.WAITING);
                    }
                    return processRepository.update(process);
                }
            }
        Optional<Approver> relatedApprover = approvers.stream().filter(appr -> appr.getUsername().equals(username)).findFirst();
        if (relatedApprover.isPresent())
            if (relatedApprover.get().getStatus().equals(ApprovalProcessState.APPROVED))
                throw new ApproverAlreadyApprovedException("User(" + username + ") already approved the process.");
            else
                throw new ApproverAlreadyRejectedException("User(" + username + ") already rejected the process.");
        throw new ApproverNotFoundException("Not found any approver user(" + username + ") in related process, or approver is not the next approver.");
    }

    private void saveNextApproverActiveStatus(Approver current, Set<Approver> approvers) {
        for (Approver target : approvers) {
            if (target.getSequenceNumber().equals(current.getSequenceNumber() + 1)) {
                target.setActive(true);
                detailRepository.update(target);
            }
        }
    }

    private Approver findNextApprover(ApprovalProcess process) {
        return detailRepository.nextApprover(process.getId());
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

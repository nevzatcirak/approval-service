package me.nevzatcirak.service.approval.support.mongo.bridge;

import me.nevzatcirak.service.approval.api.exception.ApprovalProcessReadException;
import me.nevzatcirak.service.approval.api.exception.DuplicationException;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.repository.ProcessRepository;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessConverter;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDetailDocument;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import me.nevzatcirak.service.approval.support.mongo.repository.ProcessDetailMongoRepository;
import me.nevzatcirak.service.approval.support.mongo.repository.ProcessMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Repository
public class ProcessMongoRepositoryBridge implements ProcessRepository {
    private ProcessConverter processConverter;
    private ProcessMongoRepository processMongoRepository;
    private ProcessDetailMongoRepository processDetailMongoRepository;

    @Override
    public ApprovalProcess save(ApprovalProcess approvalProcess) {
        Assert.notNull(approvalProcess, "Wanted to save approval process instance must not be null.");
        Assert.notNull(approvalProcess.getApprovers(), "Wanted to save approval process approvers must not be null.");
        ProcessDocument document = processConverter.toDocument(approvalProcess);
        Optional<ProcessDocument> existedProcess = processMongoRepository.findByDocumentIdAndDocumentType(
                document.getDocumentId(), document.getDocumentType());
        if (existedProcess.isPresent())
            throw new DuplicationException("Process already is exist, which has documentId=" + approvalProcess.getDocumentId() +
                    ", documentType=" + approvalProcess.getDocumentType());
        document.getDetails().forEach(detail -> {
            if(detail.getSequenceNumber().equals(1))
                detail.setActive(true);
            detail.setProcessId(document.getId());
        });
        Set<ProcessDetailDocument> processDetailDocuments = new HashSet<>(processDetailMongoRepository
                .saveAll(document.getDetails()));
        document.setDetails(processDetailDocuments);
        ProcessDocument savedDocument = processMongoRepository.save(document);
        return processConverter.toModel(savedDocument);
    }

    @Override
    public ApprovalProcess update(ApprovalProcess approvalProcess) {
        Assert.notNull(approvalProcess, "Wanted to update approval process instance must not be null.");
        Assert.notNull(approvalProcess.getApprovers(), "Wanted to update approval process approvers must not be null.");
        ProcessDocument document = processConverter.toDocument(approvalProcess);
        ProcessDocument savedDocument = processMongoRepository.save(document);
        return processConverter.toModel(savedDocument);
    }

    @Override
    public ApprovalProcess findBy(String documentId, String documentType) {
        return processConverter.toModel(
                processMongoRepository.findByDocumentIdAndDocumentType(documentId, documentType)
                        .orElseThrow(() ->
                                new ApprovalProcessReadException(
                                        "Approval process could not be read, which is defined in documentId=" +
                                                documentId + ", documentType=" + documentType
                                ))
        );
    }

    @Override
    public ApprovalProcess findBy(Long processId) {
        return processConverter.toModel(processMongoRepository.findById(processId)
                .orElseThrow(()
                        -> new ApprovalProcessReadException("Approval process could not be read, which is defined in processId=" + processId)));
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentType, Set<String> documentIds) {
        return processConverter.toModelSet(processMongoRepository.findAllByDocumentTypeAndIdList(documentType, documentIds)
                .orElseThrow(() ->
                        new ApprovalProcessReadException(
                                "Approval process could not be read, which is defined in documentType=" + documentType +
                                        ", documentIdList=" + String.join(",", documentIds)
                        )));
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentType, ApprovalProcessState status, Set<String> documentIds) {
        return processConverter.toModelSet(processMongoRepository.findAllByDocumentTypeStatusAndIdList(documentType, status.value(), documentIds)
                .orElseThrow(() ->
                        new ApprovalProcessReadException(
                                "Approval waiting process could not be read, which is defined in documentType=" + documentType +
                                        ", documentIdList=" + String.join(",", documentIds)
                        )));
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentType, ApprovalProcessState state) {
        return processConverter.toModelSet(processMongoRepository.findAllByFilteringStateAndDocument(documentType, state.value())
                .orElseThrow(() ->
                        new ApprovalProcessReadException(
                                "Approval processes could not be read, which is defined in documentType=" + documentType + ", State=" + state
                        )));
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentType) {
        return processConverter.toModelSet(processMongoRepository.findAllByDocumentType(documentType)
                .orElseThrow(() ->
                        new ApprovalProcessReadException(
                                "Approval processes could not be read, which is defined in documentType=" + documentType
                        )));
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentType, List<Long> legitProcessIds) {
        return processConverter.toModelSet(processMongoRepository.findAllByDocumentTypeAndProcessIds(documentType, legitProcessIds)
                .orElseThrow(() ->
                        new ApprovalProcessReadException(
                                "Approval processes could not be read, which is defined in documentType=" + documentType
                        )));
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentType, Set<String> documentIds, List<Long> legitProcessIds) {
        return processConverter.toModelSet(processMongoRepository.findAllByDocumentTypeIdsAndProcessIds(documentType, documentIds, legitProcessIds)
                .orElseThrow(() ->
                        new ApprovalProcessReadException(
                                "Approval processes could not be read, which is defined in documentType=" + documentType
                        )));
    }

    @Autowired
    private void setProcessConverter(ProcessConverter converter) {
        this.processConverter = converter;
    }

    @Autowired
    private void setProcessMongoRepository(ProcessMongoRepository processMongoRepository) {
        this.processMongoRepository = processMongoRepository;
    }

    @Autowired
    private void setProcessDetailMongoRepository(ProcessDetailMongoRepository processDetailMongoRepository) {
        this.processDetailMongoRepository = processDetailMongoRepository;
    }
}

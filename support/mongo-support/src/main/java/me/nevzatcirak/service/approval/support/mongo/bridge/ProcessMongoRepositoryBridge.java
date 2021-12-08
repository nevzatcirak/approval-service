package me.nevzatcirak.service.approval.support.mongo.bridge;

import me.nevzatcirak.service.approval.api.exception.ApprovalProcessNotFoundException;
import me.nevzatcirak.service.approval.api.exception.DuplicationException;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.repository.ProcessRepository;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessConverter;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import me.nevzatcirak.service.approval.support.mongo.repository.ProcessMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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

    @Override
    public ApprovalProcess save(ApprovalProcess approvalProcess) {
        Assert.notNull(approvalProcess, "Wanted to save approval process instance must not be null.");
        Assert.notNull(approvalProcess.getDetail(), "Wanted to save approval process approvers must not be null.");
        ProcessDocument document = processConverter.toDocument(approvalProcess);
        processMongoRepository.findByDocumentIdAndDocumentType(
                document.getDocumentId(), document.getDocumentType()).orElseThrow(() ->
                new DuplicationException("Process already is exist, which has documentId=" + approvalProcess.getDocumentId() +
                        ", documentType=" + approvalProcess.getDocumentType()));
        ProcessDocument savedDocument = processMongoRepository.save(document);
        return processConverter.toModel(savedDocument);
    }

    @Override
    public ApprovalProcess update(ApprovalProcess approvalProcess) {
        Assert.notNull(approvalProcess, "Wanted to update approval process instance must not be null.");
        Assert.notNull(approvalProcess.getDetail(), "Wanted to update approval process approvers must not be null.");
        ProcessDocument document = processConverter.toDocument(approvalProcess);
        ProcessDocument savedDocument = processMongoRepository.save(document);
        return processConverter.toModel(savedDocument);
    }

    @Override
    public ApprovalProcess findBy(String documentId, String documentType) {
        return processConverter.toModel(
                processMongoRepository.findByDocumentIdAndDocumentType(documentId, documentType)
                        .orElseThrow(() ->
                                new ApprovalProcessNotFoundException(
                                        "Approval process could not be found, which is defined in documentId=" + documentId + ", documentType=" + documentType
                                ))
        );
    }

    @Override
    public ApprovalProcess findBy(String processId) {
        return processConverter.toModel(processMongoRepository.findById(processId)
                .orElseThrow(()
                        -> new ApprovalProcessNotFoundException("Approval process could not be found, which is defined in processId=" + processId)));
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentType, Set<String> documentIds) {
        return null;
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String documentId, String documentType, ApprovalProcessState state) {
        return null;
    }

    @Override
    public Set<ApprovalProcess> findAllBy(String processId, ApprovalProcessState state) {
        return null;
    }

    @Override
    public Approver findProcessNextApprover(String documentId, String documentType) {
        return null;
    }

    @Override
    public Approver findProcessNextApprover(String processId) {
        return null;
    }

    @Autowired
    private void setProcessConverter(ProcessConverter converter) {
        this.processConverter = converter;
    }

    @Autowired
    private void setProcessMongoRepository(ProcessMongoRepository processMongoRepository) {
        this.processMongoRepository = processMongoRepository;
    }
}

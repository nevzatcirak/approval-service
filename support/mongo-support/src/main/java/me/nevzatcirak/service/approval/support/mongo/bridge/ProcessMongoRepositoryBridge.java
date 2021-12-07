package me.nevzatcirak.service.approval.support.mongo.bridge;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.repository.ProcessRepository;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessConverter;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessDetailConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Repository
public class ProcessMongoRepositoryBridge implements ProcessRepository {
    private ProcessConverter processConverter;
    private ProcessDetailConverter detailConverter;

    @Override
    public ApprovalProcess save(ApprovalProcess approvalProcess) {
        return null;
    }

    @Override
    public ApprovalProcess update(ApprovalProcess approvalProcess) {
        return null;
    }

    @Override
    public ApprovalProcess findBy(String documentId, String documentType) {
        return null;
    }

    @Override
    public ApprovalProcess findBy(String processId) {
        return null;
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
    public void setProcessConverter(ProcessConverter converter) {
        this.processConverter = converter;
    }

    @Autowired
    public void setProcessDetailConverter(ProcessDetailConverter detailConverter) {
        this.detailConverter = detailConverter;
    }
}

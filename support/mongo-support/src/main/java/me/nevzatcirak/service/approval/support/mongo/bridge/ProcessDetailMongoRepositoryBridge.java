package me.nevzatcirak.service.approval.support.mongo.bridge;

import me.nevzatcirak.service.approval.api.exception.DuplicationException;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.repository.ProcessDetailRepository;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessDetailConverter;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDetailDocument;
import me.nevzatcirak.service.approval.support.mongo.repository.ProcessDetailMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 09/12/2021
 */
@Repository
public class ProcessDetailMongoRepositoryBridge implements ProcessDetailRepository {
    private ProcessDetailConverter detailConverter;
    private ProcessDetailMongoRepository processDetailMongoRepository;

    @Override
    public Approver save(Approver approver) {
        Assert.notNull(approver, "Approver data must not be null.");
        Assert.notNull(approver.getSequenceNumber(), "Approver sequence data must not be null.");
        Assert.notNull(approver.getStatus(), "Approver status data must not be null.");
        Assert.notNull(approver.getUsername(), "Approver username must not be null.");
        if (Objects.nonNull(approver.getId()))
            processDetailMongoRepository.findById(approver.getId()).ifPresent((detail)
                    -> {
                throw new DuplicationException("Approver(" + detail.getId() + ") is already exist.");
            });
        return detailConverter.toModel(processDetailMongoRepository.save(detailConverter.toDocument(approver)));
    }

    @Override
    public Approver update(Approver approver) {
        Assert.notNull(approver, "Approver data must not be null.");
        Assert.notNull(approver.getSequenceNumber(), "Approver sequence data must not be null.");
        Assert.notNull(approver.getStatus(), "Approver status data must not be null.");
        Assert.notNull(approver.getUsername(), "Approver username must not be null.");
        return detailConverter.toModel(processDetailMongoRepository.save(detailConverter.toDocument(approver)));
    }

    @Override
    public Approver nextApprover(List<Long> approverIds) {
        Assert.notNull(approverIds, "Approvers must be provided to find out next approver.");
        Assert.notEmpty(approverIds, "Approvers must be provided to find out next approver.");
        Optional<ProcessDetailDocument> nextApprover = processDetailMongoRepository.findNextApprover(approverIds).stream()
                .findFirst();
        return nextApprover.map(processDetailDocument -> detailConverter.toModel(processDetailDocument)).orElse(null);
    }

    @Autowired
    private void setDetailConverter(ProcessDetailConverter detailConverter) {
        this.detailConverter = detailConverter;
    }

    @Autowired
    private void setProcessDetailMongoRepository(ProcessDetailMongoRepository processDetailMongoRepository) {
        this.processDetailMongoRepository = processDetailMongoRepository;
    }
}

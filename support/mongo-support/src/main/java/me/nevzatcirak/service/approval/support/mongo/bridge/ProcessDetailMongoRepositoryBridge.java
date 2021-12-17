package me.nevzatcirak.service.approval.support.mongo.bridge;

import me.nevzatcirak.service.approval.api.exception.ApprovalProcessReadException;
import me.nevzatcirak.service.approval.api.exception.DuplicationException;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.api.repository.ProcessDetailRepository;
import me.nevzatcirak.service.approval.support.mongo.converter.ProcessDetailConverter;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDetailDocument;
import me.nevzatcirak.service.approval.support.mongo.repository.ProcessDetailMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by ncirak at 09/12/2021
 */
@Repository
public class ProcessDetailMongoRepositoryBridge implements ProcessDetailRepository {
    private ProcessDetailConverter detailConverter;
    private ProcessDetailMongoRepository processDetailMongoRepository;
    private MongoTemplate mongoTemplate;

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
    public Approver nextApprover(Long processId) {
        Assert.notNull(processId, "Process id must be provided to find out next approver.");
        Query query = new Query();
        query.addCriteria(Criteria.where("active").is(true).and("processId").is(processId)).limit(1);
        ProcessDetailDocument detailDocs = mongoTemplate.findOne(query, ProcessDetailDocument.class);
        return detailConverter.toModel(detailDocs);
    }

    @Override
    public List<Long> findProcessIdsByNextApproverUsername(String username) {
        Query query = new Query();
        query.fields().include("processId");
        query.addCriteria(Criteria.where("active").is(true).and("username").is(username));
        List<ProcessDetailDocument> processDetailDocuments = mongoTemplate.find(query, ProcessDetailDocument.class);
        return processDetailDocuments.stream().map(ProcessDetailDocument::getProcessId).collect(Collectors.toList());
    }

    @Override
    public List<Long> findProcessIdsByEligibleUsername(String username) {
        List<ProcessDetailDocument> legitApprovers = processDetailMongoRepository.findProcessIdsByEligibleUsername(username)
                .orElseThrow(() ->
                        new ApprovalProcessReadException(
                                "Approvers could not be read, which is defined in username=" + username
                        ));
        return legitApprovers.stream().map(ProcessDetailDocument::getProcessId).collect(Collectors.toList());
    }

    @Override
    public List<Long> findProcessIdsByUsername(String username) {
        Query query = new Query();
        query.fields().include("processId");
        query.addCriteria(Criteria.where("username").is(username));
        List<ProcessDetailDocument> processDetailDocuments = mongoTemplate.find(query, ProcessDetailDocument.class);
        return processDetailDocuments.stream().map(ProcessDetailDocument::getProcessId).collect(Collectors.toList());
    }

    @Autowired
    private void setDetailConverter(ProcessDetailConverter detailConverter) {
        this.detailConverter = detailConverter;
    }

    @Autowired
    private void setProcessDetailMongoRepository(ProcessDetailMongoRepository processDetailMongoRepository) {
        this.processDetailMongoRepository = processDetailMongoRepository;
    }

    @Autowired
    private void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}

package me.nevzatcirak.service.approval.support.mongo.converter;

import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.support.mongo.SequenceGenerator;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDetailDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 06/12/2021
 */
@Component
public class ProcessDetailConverter implements Converter<Approver, ProcessDetailDocument> {
    private SequenceGenerator sequenceGenerator;

    @Override
    public Approver toModel(ProcessDetailDocument processDetailDocument) {
        if(Objects.isNull(processDetailDocument))
            return null;
        return new Approver()
                .setId(processDetailDocument.getId())
                .setProcessId(processDetailDocument.getProcessId())
                .setUsername(processDetailDocument.getUsername())
                .setComment(processDetailDocument.getComment())
                .setUpdatedAt(processDetailDocument.getUpdatedAt())
                .setSequenceNumber(processDetailDocument.getSequenceNumber())
                .setActive(processDetailDocument.isActive())
                .setStatus(ApprovalProcessState.valueOf(processDetailDocument.getStatus()));
    }

    @Override
    public ProcessDetailDocument toDocument(Approver approver) {
        if(Objects.isNull(approver))
            return null;
        ProcessDetailDocument document = new ProcessDetailDocument();
        document.setUsername(approver.getUsername());
        document.setComment(approver.getComment());
        document.setStatus(approver.getStatus().value());
        document.setSequenceNumber(approver.getSequenceNumber());
        document.setProcessId(approver.getProcessId());
        document.setUpdatedAt(approver.getUpdatedAt());
        document.setActive(approver.isActive());
        if (Objects.isNull(approver.getId()))
            document.setId(sequenceGenerator.generateIdSequence(ProcessDetailDocument.SEQUENCE_NAME));
        else
            document.setId(approver.getId());
        return document;
    }

    @Override
    public List<Approver> toModelList(Collection<ProcessDetailDocument> processDetailDocuments) {
        return Converter.super.toModelList(processDetailDocuments);
    }

    @Override
    public List<ProcessDetailDocument> toDocumentList(Collection<Approver> approvers) {
        return Converter.super.toDocumentList(approvers);
    }

    @Override
    public Set<Approver> toModelSet(Set<ProcessDetailDocument> processDetailDocuments) {
        return Converter.super.toModelSet(processDetailDocuments);
    }

    @Override
    public Set<ProcessDetailDocument> toDocumentSet(Set<Approver> approvers) {
        return Converter.super.toDocumentSet(approvers);
    }

    @Autowired
    private void setSequenceGenerator(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }
}

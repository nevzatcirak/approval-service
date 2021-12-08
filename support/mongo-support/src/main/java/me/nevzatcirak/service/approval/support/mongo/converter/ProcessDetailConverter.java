package me.nevzatcirak.service.approval.support.mongo.converter;

import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDetailDocument;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Component
public class ProcessDetailConverter implements Converter<Approver, ProcessDetailDocument> {

    @Override
    public Approver toModel(ProcessDetailDocument processDetailDocument) {
        return new Approver()
                .setUsername(processDetailDocument.getUsername())
                .setSequenceNumber(processDetailDocument.getSequenceNumber())
                .setStatus(ApprovalProcessState.valueOf(processDetailDocument.getStatus()));
    }

    @Override
    public ProcessDetailDocument toDocument(Approver approver) {
        ProcessDetailDocument document = new ProcessDetailDocument();
        document.setUsername(approver.getUsername());
        document.setStatus(approver.getStatus().value());
        document.setSequenceNumber(approver.getSequenceNumber());
        if (Objects.nonNull(approver.getId()))
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
}

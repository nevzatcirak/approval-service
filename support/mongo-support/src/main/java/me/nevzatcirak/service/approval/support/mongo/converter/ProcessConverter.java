package me.nevzatcirak.service.approval.support.mongo.converter;

import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;
import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProcessConverter implements Converter<ApprovalProcess, ProcessDocument> {
    private ProcessDetailConverter detailConverter;

    @Override
    public ApprovalProcess toModel(ProcessDocument processDocument) {
        return new ApprovalProcess()
                .setDocumentId(processDocument.getDocumentId())
                .setDocumentType(processDocument.getDocumentType())
                .setDetail(detailConverter.toModelSet(processDocument.getDetails()))
                .setId(processDocument.getId())
                .setStatus(ApprovalProcessState.valueOf(processDocument.getStatus()));
    }

    @Override
    public ProcessDocument toDocument(ApprovalProcess approvalProcess) {
        ProcessDocument document = new ProcessDocument();
        document.setStatus(approvalProcess.getStatus().value());
        document.setDocumentId(approvalProcess.getDocumentId());
        document.setDocumentType(approvalProcess.getDocumentType());
        document.setDetails(detailConverter.toDocumentSet(approvalProcess.getDetail()));
        if (Objects.nonNull(approvalProcess.getId()))
            document.setId(approvalProcess.getId());
        return document;
    }

    @Override
    public List<ApprovalProcess> toModelList(Collection<ProcessDocument> processDocuments) {
        return Converter.super.toModelList(processDocuments);
    }

    @Override
    public List<ProcessDocument> toDocumentList(Collection<ApprovalProcess> approvalProcesses) {
        return Converter.super.toDocumentList(approvalProcesses);
    }

    @Override
    public Set<ApprovalProcess> toModelSet(Set<ProcessDocument> processDocuments) {
        return Converter.super.toModelSet(processDocuments);
    }

    @Override
    public Set<ProcessDocument> toDocumentSet(Set<ApprovalProcess> approvalProcessSet) {
        return Converter.super.toDocumentSet(approvalProcessSet);
    }

    @Autowired
    public void setDetailConverter(ProcessDetailConverter converter) {
        this.detailConverter = converter;
    }
}

package me.nevzatcirak.service.approval.support.mongo.converter;

import me.nevzatcirak.service.approval.support.mongo.model.ProcessDocument;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Component
public class ProcessDetailConverter implements Converter<Process, ProcessDocument> {
    @Override
    public Process toModel(ProcessDocument processDocument) {
        return null;
    }

    @Override
    public ProcessDocument toDocument(Process process) {
        return null;
    }

    @Override
    public List<Process> toModelList(Collection<ProcessDocument> processDocuments) {
        return null;
    }

    @Override
    public List<ProcessDocument> toDocumentList(Collection<Process> processes) {
        return null;
    }

    @Override
    public Set<Process> toModelSet(Collection<ProcessDocument> processDocuments) {
        return null;
    }

    @Override
    public Set<ProcessDocument> toDocumentSet(Collection<Process> processes) {
        return null;
    }
}

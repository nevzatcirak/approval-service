package me.nevzatcirak.service.approval.support.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
@Document(collection = "approval_process")
@CompoundIndexes({
        @CompoundIndex(name = "document_type_id", def = "{'documentType': 1, 'documentId':1}")
})
public class ProcessDocument implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "approval_process_sequence";

    @Id
    private Long id;

    @Field("documentType")
    private String documentType;

    @Indexed(direction = IndexDirection.ASCENDING)
    @Field("documentId")
    private String documentId;

    @Field("status")
    private Integer status;

    @DBRef
    @Field("details")
    private Set<ProcessDetailDocument> details;

    public ProcessDocument() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<ProcessDetailDocument> getDetails() {
        return details;
    }

    public void setDetails(Set<ProcessDetailDocument> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ProcessDocument{" +
                "id='" + id + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentId='" + documentId + '\'' +
                ", status=" + status +
                ", details=" + details +
                '}';
    }
}

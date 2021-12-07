package me.nevzatcirak.service.approval.api.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public class ApprovalProcess implements Serializable {
    private String id;
    private String documentType;
    private String documentId;
    private ApprovalProcessState status;
    private Set<Approver> detail;

    public String getId() {
        return id;
    }

    public ApprovalProcess setId(String id) {
        this.id = id;
        return this;
    }

    public String getDocumentType() {
        return documentType;
    }

    public ApprovalProcess setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public String getDocumentId() {
        return documentId;
    }

    public ApprovalProcess setDocumentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public ApprovalProcessState getStatus() {
        return status;
    }

    public ApprovalProcess setStatus(ApprovalProcessState status) {
        this.status = status;
        return this;
    }

    public Set<Approver> getDetail() {
        return detail;
    }

    public ApprovalProcess setDetail(Set<Approver> detail) {
        this.detail = detail;
        return this;
    }
}

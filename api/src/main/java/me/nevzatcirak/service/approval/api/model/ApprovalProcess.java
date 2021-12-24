package me.nevzatcirak.service.approval.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 06/12/2021
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovalProcess implements Serializable {
    private Long id;
    private String documentType;
    private String documentId;
    private String creator;
    private boolean canceled;
    private ApprovalProcessState status;
    private Set<Approver> approvers;

    public Long getId() {
        return id;
    }

    public ApprovalProcess setId(Long id) {
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

    public String getCreator() {
        return creator;
    }

    public ApprovalProcess setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public ApprovalProcess setCanceled(boolean canceled) {
        this.canceled = canceled;
        return this;
    }

    public ApprovalProcessState getStatus() {
        return status;
    }

    public ApprovalProcess setStatus(ApprovalProcessState status) {
        this.status = status;
        return this;
    }

    public Set<Approver> getApprovers() {
        return approvers;
    }

    public ApprovalProcess setApprovers(Set<Approver> approvers) {
        this.approvers = approvers;
        return this;
    }

    @Override
    public String toString() {
        return "ApprovalProcess{" +
                "id='" + id + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentId='" + documentId + '\'' +
                ", status=" + status +
                ", detail=" + approvers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalProcess that = (ApprovalProcess) o;
        return id.equals(that.id) &&
                documentType.equals(that.documentType) &&
                documentId.equals(that.documentId) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentType, documentId, status);
    }
}

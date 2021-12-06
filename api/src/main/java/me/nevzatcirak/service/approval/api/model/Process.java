package me.nevzatcirak.service.approval.api.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public class Process implements Serializable {
    private String id;
    private String documentType;
    private String documentId;
    private String status;
    private Set<ProcessDetail> detail;

    public String getId() {
        return id;
    }

    public Process setId(String id) {
        this.id = id;
        return this;
    }

    public String getDocumentType() {
        return documentType;
    }

    public Process setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public String getDocumentId() {
        return documentId;
    }

    public Process setDocumentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Process setStatus(String status) {
        this.status = status;
        return this;
    }

    public Set<ProcessDetail> getDetail() {
        return detail;
    }

    public Process setDetail(Set<ProcessDetail> detail) {
        this.detail = detail;
        return this;
    }
}

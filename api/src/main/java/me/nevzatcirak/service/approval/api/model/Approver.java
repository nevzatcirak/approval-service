package me.nevzatcirak.service.approval.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 06/12/2021
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Approver implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long processId;
    private Integer sequenceNumber;
    private String username;
    private String comment;
    private ApprovalProcessState status;
    @JsonIgnore
    private Boolean active = false;

    public Long getId() {
        return id;
    }

    public Approver setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getProcessId() {
        return processId;
    }

    public Approver setProcessId(Long processId) {
        this.processId = processId;
        return this;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public Approver setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Approver setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Approver setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ApprovalProcessState getStatus() {
        return status;
    }

    public Approver setStatus(ApprovalProcessState status) {
        this.status = status;
        return this;
    }

    public Boolean isActive() {
        return active;
    }

    public Approver setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        return "Approver{" +
                "id=" + id +
                ", processId=" + processId +
                ", sequenceNumber=" + sequenceNumber +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Approver approver = (Approver) o;
        return id.equals(approver.id) &&
                processId.equals(approver.processId) &&
                sequenceNumber.equals(approver.sequenceNumber) &&
                username.equals(approver.username) &&
                status == approver.status &&
                Objects.equals(active, approver.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, processId, sequenceNumber, username, status, active);
    }
}

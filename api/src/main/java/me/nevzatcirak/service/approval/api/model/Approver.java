package me.nevzatcirak.service.approval.api.model;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public class Approver implements Serializable {
    private Integer sequenceNumber;
    private String username;
    private ApprovalProcessState status;

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

    public ApprovalProcessState getStatus() {
        return status;
    }

    public Approver setStatus(ApprovalProcessState status) {
        this.status = status;
        return this;
    }
}

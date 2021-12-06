package me.nevzatcirak.service.approval.api.model;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public class ProcessDetail implements Serializable {
    private Long sequenceNumber;
    private String username;
    private String status;

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public ProcessDetail setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProcessDetail setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ProcessDetail setStatus(String status) {
        this.status = status;
        return this;
    }
}

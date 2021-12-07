package me.nevzatcirak.service.approval.controller.model;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public class ApproverSummary implements Serializable {
    private Integer sequenceNumber;
    private String username;

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

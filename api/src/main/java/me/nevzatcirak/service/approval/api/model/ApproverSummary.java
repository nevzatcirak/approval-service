package me.nevzatcirak.service.approval.api.model;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "ApproverSummary{" +
                "sequenceNumber=" + sequenceNumber +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApproverSummary that = (ApproverSummary) o;
        return sequenceNumber.equals(that.sequenceNumber) &&
                username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceNumber, username);
    }
}

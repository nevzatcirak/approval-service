package me.nevzatcirak.service.approval.controller.model;

import me.nevzatcirak.service.approval.api.model.ApproverSummary;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public class CreateApprovalRequest implements Serializable {
    private String id;
    private String type;
    private Set<ApproverSummary> approvers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<ApproverSummary> getApprovers() {
        return approvers;
    }

    public void setApprovers(Set<ApproverSummary> approvers) {
        this.approvers = approvers;
    }
}

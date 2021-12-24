package me.nevzatcirak.service.approval.controller.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import me.nevzatcirak.service.approval.api.model.ApproverSummary;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 07/12/2021
 */
@ApiModel("Approval Process Cretaion Request Model")
public class CreateApprovalRequest implements Serializable {
    @ApiModelProperty("Unique Id of a report or document")
    private String id;
    @ApiModelProperty("Service name")
    private String type;
    @ApiModelProperty("Approvers who will approve related document")
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

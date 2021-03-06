package me.nevzatcirak.service.approval.controller.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 07/12/2021
 */
@ApiModel("Approver Update Status Request Model")
public class StateDetailUpdateRequest implements Serializable {
    @ApiModelProperty("Approver username")
    private String username;
    @ApiModelProperty("Approver action reason or comment")
    private String comment;
    @ApiModelProperty("Updated status")
    private ApprovalProcessState status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public StateDetailUpdateRequest setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ApprovalProcessState getStatus() {
        return status;
    }

    public void setStatus(ApprovalProcessState status) {
        this.status = status;
    }
}

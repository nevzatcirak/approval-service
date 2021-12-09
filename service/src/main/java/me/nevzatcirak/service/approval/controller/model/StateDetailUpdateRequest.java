package me.nevzatcirak.service.approval.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@ApiModel("Approver Update Status Request Model")
public class StateDetailUpdateRequest implements Serializable {
    @ApiModelProperty("Approver username")
    private String username;
    @ApiModelProperty("Updated status")
    private ApprovalProcessState status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ApprovalProcessState getStatus() {
        return status;
    }

    public void setStatus(ApprovalProcessState status) {
        this.status = status;
    }
}

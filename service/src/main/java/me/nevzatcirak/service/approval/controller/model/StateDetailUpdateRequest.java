package me.nevzatcirak.service.approval.controller.model;

import me.nevzatcirak.service.approval.api.model.ApprovalProcessState;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public class StateDetailUpdateRequest implements Serializable {
    private String username;
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

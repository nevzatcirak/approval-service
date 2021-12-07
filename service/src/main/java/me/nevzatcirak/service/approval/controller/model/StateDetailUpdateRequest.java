package me.nevzatcirak.service.approval.controller.model;

import me.nevzatcirak.service.approval.api.model.ProcessState;

import java.io.Serializable;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public class StateDetailUpdateRequest implements Serializable {
    private String username;
    private Long updatedAt;
    private ProcessState status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ProcessState getStatus() {
        return status;
    }

    public void setStatus(ProcessState status) {
        this.status = status;
    }
}

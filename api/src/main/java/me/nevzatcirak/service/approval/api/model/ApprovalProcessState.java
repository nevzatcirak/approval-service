package me.nevzatcirak.service.approval.api.model;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 07/12/2021
 */
public enum ApprovalProcessState {
    REJECTED(-1),
    WAITING(0),
    APPROVED(1);

    private final int value;

    ApprovalProcessState(int value) {
        this.value = value;
    }

    public static ApprovalProcessState valueOf(int val) {
        for (ApprovalProcessState state : values()) {
            if (state.value == val)
                return state;
        }
        return null;
    }

    public int value() {
        return value;
    }
}

package me.nevzatcirak.service.approval.api.model;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
public enum ProcessState {
    REJECTED(-1),
    WAITING(0),
    APPROVED(1);

    private final int value;

    ProcessState(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}

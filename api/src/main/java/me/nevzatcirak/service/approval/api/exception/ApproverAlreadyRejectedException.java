package me.nevzatcirak.service.approval.api.exception;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public class ApproverAlreadyRejectedException extends RuntimeException {
    private final String messageKey;

    public ApproverAlreadyRejectedException(String message) {
        super(message);
        this.messageKey = "USER_ALREADY_REJECTED";
    }

    public String getMessageKey() {
        return messageKey;
    }
}

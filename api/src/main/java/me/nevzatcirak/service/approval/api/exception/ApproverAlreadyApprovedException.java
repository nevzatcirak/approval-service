package me.nevzatcirak.service.approval.api.exception;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public class ApproverAlreadyApprovedException extends RuntimeException {
    private final String messageKey;

    public ApproverAlreadyApprovedException(String message) {
        super(message);
        this.messageKey = "USER_ALREADY_APPROVED";
    }

    public String getMessageKey() {
        return messageKey;
    }
}

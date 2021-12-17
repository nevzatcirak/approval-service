package me.nevzatcirak.service.approval.api.exception;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 06/12/2021
 */
public class ApproverNotFoundException extends RuntimeException {
    public ApproverNotFoundException(String message) {
        super(message);
    }
}

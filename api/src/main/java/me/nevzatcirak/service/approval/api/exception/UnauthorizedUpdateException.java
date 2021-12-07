package me.nevzatcirak.service.approval.api.exception;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 06/12/2021
 */
public class UnauthorizedUpdateException extends RuntimeException {
    public UnauthorizedUpdateException(String message) {
        super(message);
    }
}

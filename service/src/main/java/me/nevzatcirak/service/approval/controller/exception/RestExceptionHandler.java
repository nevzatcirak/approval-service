package me.nevzatcirak.service.approval.controller.exception;

import io.swagger.annotations.Api;
import me.nevzatcirak.service.approval.api.exception.ApprovalProcessNotFoundException;
import me.nevzatcirak.service.approval.api.exception.ApprovalProcessReadException;
import me.nevzatcirak.service.approval.api.exception.ApproverNotFoundException;
import me.nevzatcirak.service.approval.api.exception.DuplicationException;
import me.nevzatcirak.service.approval.controller.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@ControllerAdvice
@Component
@Api("Approval Process Error Handler Api Documentation")
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({
            ApprovalProcessNotFoundException.class,
            ApproverNotFoundException.class,
            ApprovalProcessReadException.class,
            DuplicationException.class})
    public ResponseEntity<?> handleApprovalDataNotFoundException(RuntimeException e) {
        logger.error("Exception Message: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("Exception Message: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        logger.error("Exception Message: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
}

package me.nevzatcirak.service.approval.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 10/12/2021
 */
@ApiModel("Error Response Model")
public class ErrorResponse {
    @ApiModelProperty("Http Status Code of Error")
    private int statusCode;
    @ApiModelProperty("Error Message")
    private String message;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public me.nevzatcirak.service.approval.controller.model.ErrorResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public me.nevzatcirak.service.approval.controller.model.ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}

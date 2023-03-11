package com.opgg.opggapi.dto.responseDto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RequestResponse<D> {
    private Integer status;

    private Code code;

    private String message;

    private D result;

    public enum Code {
        SUCCESS,
        FAILED
    }

    public static <D> RequestResponse<D> of (HttpStatus status, Code code, String message, D result) {
        RequestResponse<D> response = new RequestResponse<>();
        response.setStatus(status.value());
        response.setCode(code);
        response.setMessage(message);
        response.setResult(result);
        return response;
    }
}
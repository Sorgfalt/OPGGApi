package com.opgg.opggapi.dto.responseDto.payload;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean success;
    private String message;

    private Integer status;

    public ApiResponse(boolean success, String message, Integer status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }
}

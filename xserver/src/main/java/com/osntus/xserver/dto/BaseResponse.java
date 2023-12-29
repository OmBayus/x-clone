package com.osntus.xserver.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class BaseResponse<T>{
    @Getter
    private final String message;
    private final T payload;

    public BaseResponse(String message, T payload) {
        this.message = message;
        this.payload = payload;
    }

    public BaseResponse(String message) {
        this.message = message;
        this.payload = null;
    }
}
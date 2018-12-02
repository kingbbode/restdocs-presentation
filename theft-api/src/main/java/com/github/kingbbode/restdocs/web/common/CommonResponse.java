package com.github.kingbbode.restdocs.web.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T> {

    private Status status;
    private T data;
    private String message;

    private CommonResponse(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>(Status.SUCCESS, data, null);
    }

    public static <T> CommonResponse<T> fail(String message) {
        return new CommonResponse<>(Status.FAIL, null, message);
    }
}

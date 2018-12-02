package com.github.kingbbode.restdocs.web;

import com.github.kingbbode.restdocs.web.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    private static final String BAD_REQUEST_MESSAGE = "잘못된 요청입니다.";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "네트워크 상태가 원활하지 않습니다. 잠시 후 다시 시도해주세요.";

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonResponse exception(Exception e) {
        log.error("[API][ERROR] server error - {}", e.getMessage());
        return CommonResponse.error(INTERNAL_SERVER_ERROR_MESSAGE);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public CommonResponse illegalArgumentException(IllegalArgumentException e) {
        return CommonResponse.error(Optional.ofNullable(e.getMessage()).orElse(BAD_REQUEST_MESSAGE));
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException.class)
    public CommonResponse illegalStateException(IllegalStateException e) {
        return CommonResponse.error(Optional.ofNullable(e.getMessage()).orElse(BAD_REQUEST_MESSAGE));
    }
}

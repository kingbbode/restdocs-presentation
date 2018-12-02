package com.github.kingbbode.restdocs.web;

import com.github.kingbbode.restdocs.web.common.CommonResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/success")
    public CommonResponse<Data> success() {
        return CommonResponse.ok(Data.builder().data("hello world.").build());
    }

    @GetMapping("/fail")
    public CommonResponse<Data> fail() {
        throw new FailedException("ex) 주문하신 업소가 마감했습니다. 다른 업소를 이용해주세요.", Data.builder().data("failed data.").build());
    }

    @GetMapping("/bad-request")
    public CommonResponse<Data> badRequest() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/internal-server-error")
    public CommonResponse<Data> internalServerError() {
        throw new NullPointerException();
    }

    @ControllerAdvice
    @ResponseBody
    public static class TestControllerAdvice extends CommonControllerAdvice {
        @ResponseStatus(value = HttpStatus.OK)
        @ExceptionHandler(FailedException.class)
        public CommonResponse fail(FailedException e) {
            return CommonResponse.fail(e.getMessage(), e.getData());
        }
    }

    @Getter
    public static class Data {
        private String data;

        @Builder
        public Data(String data) {
            this.data = data;
        }
    }

    @Getter
    public static class FailedException extends RuntimeException {
        private Data data;

        public FailedException(String message, Data data) {
            super(message);
            this.data = data;
        }
    }
}

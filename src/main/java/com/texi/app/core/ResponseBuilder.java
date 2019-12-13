package com.texi.app.core;

import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ResponseBuilder {
    private final String DEFAULT_SUCCESS_MESSAGE = "success";

    public Response buildSuccess() {
        return new Response()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public <T> Response<T> buildSuccess(T data) {
        return new Response()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public <T> Response<T> buildSuccess(String message, T data) {
        return new Response()
                .setCode(ResponseCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    public Response buildFail(String message) {
        return new Response()
                .setCode(ResponseCode.BAD_REQUEST)
                .setMessage(message);
    }

    public Response buildFail(ResponseCode code, String message) {
        return new Response()
                .setCode(code)
                .setMessage(message);
    }

    public Response buildFail(ResponseCode code, String message, Object errors) {
        return new Response()
                .setCode(code)
                .setMessage(message)
                .setErrors(errors);
    }

    public Response buildFail(ResponseCode code, String message, String error) {
        return new Response()
                .setCode(code)
                .setMessage(message)
                .setErrors(Collections.singletonList(error));
    }
}

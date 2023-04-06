package com.example.demo.service.model;

import lombok.*;

/**
 * common response of method
 */
@Data
@AllArgsConstructor
public class Response {

    public static final int OK = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;

    private Integer code;

    private String message;


    public static Response getOK() {
        return new Response(OK, "");
    }

    public static Response getWarning(String msg) {
        return new Response(WARNING, msg);
    }

    public static Response getError(String msg) {
        return new Response(ERROR, msg);
    }


}

package com.example.demo.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * ClassName:ApiException
 * PackgeName:com.example.demo.Exception
 * Description:
 *
 * @Date:2020-03-22 17:49
 * Author:ningzhy3@gmail.com
 */
public class ApiException {
    private final String message;

    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;

    public ApiException(String message,  HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;

        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }



    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}

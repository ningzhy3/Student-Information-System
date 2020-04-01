package com.example.demo.Exception;

/**
 * ClassName:ApiRequestException
 * PackgeName:com.example.demo.Exception
 * Description:
 *
 * @Date:2020-03-22 17:41
 * Author:ningzhy3@gmail.com
 */
public class ApiRequestException extends RuntimeException {


    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}

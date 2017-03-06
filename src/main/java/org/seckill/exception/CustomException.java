package org.seckill.exception;

/**
 * Created by X-man on 2017/3/6.
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}

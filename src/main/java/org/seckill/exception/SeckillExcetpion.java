package org.seckill.exception;

/**
 * Created by X-man on 2017/3/2.
 */
public class SeckillExcetpion extends RuntimeException{

    public SeckillExcetpion(String message) {
        super(message);
    }

    public SeckillExcetpion(String message, Throwable cause) {
        super(message, cause);
    }
}

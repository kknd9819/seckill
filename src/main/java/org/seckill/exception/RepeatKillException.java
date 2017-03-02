package org.seckill.exception;

/** 重复秒杀异常
 * Created by X-man on 2017/3/2.
 */
public class RepeatKillException extends SeckillExcetpion{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}

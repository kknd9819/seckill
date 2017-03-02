package org.seckill.exception;

/**秒杀关闭异常
 * Created by X-man on 2017/3/2.
 */
public class SeckillCloseException extends SeckillExcetpion{

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}

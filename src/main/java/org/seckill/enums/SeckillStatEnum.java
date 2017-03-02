package org.seckill.enums;

/**使用枚举表示数据字典
 * Created by X-man on 2017/3/2.
 */
public enum SeckillStatEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改")
    ;

    private int state;

    private String statInfo;

    SeckillStatEnum(int state, String statInfo) {
        this.state = state;
        this.statInfo = statInfo;
    }

    public int getState() {
        return state;
    }

    public String getStatInfo() {
        return statInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        for (SeckillStatEnum state:values()) {
            if(state.getState() == index){
                return state;
            }
        }
        return null;
    }
}

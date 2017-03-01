package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * Created by X-man on 2017/3/1.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细
     * @param seckillId
     * @param userPhone
     * @return 返回的是更新的记录行数
     */
    int insertSuccessKilled(Long seckillId,Long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByWithSeckill(Long seckillId);


}

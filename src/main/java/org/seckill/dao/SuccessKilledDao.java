package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
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
    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId,@Param("userPhone") Long userPhone);


}

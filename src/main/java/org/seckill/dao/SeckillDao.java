package org.seckill.dao;

import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by X-man on 2017/3/1.
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(Long seckillId,Date killTime);

    /**
     * 查询一个秒杀库存
     * @param seckillId
     * @return
     */
    Seckill queryById(Long seckillId);

    /**
     * 分页查询
     * @param offet 偏移量
     * @param limit 条数
     * @return
     */
    List<Seckill> queryAll(Integer offet, Integer limit);
}

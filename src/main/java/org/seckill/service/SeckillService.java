package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;

import java.util.List;

/** 站在使用者的角度是设计接口
 * 三个方面：方法定义粒度,参数,返回类型
 * Created by X-man on 2017/3/1.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记住
     * @param seckillId
     * @return
     */
    Seckill getById(Long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(Long seckillId);
}

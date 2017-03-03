package org.seckill.dao.cache;

import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by X-man on 2017/3/3.
 */
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private  final JedisPool jedisPool;

    public RedisDao(String ip,int port){
        jedisPool = new JedisPool(ip,port);
    }

    public Seckill getSeckill(Long seckillId){
        //redis的操作逻辑应该放在数据层
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:" + seckillId;
                //并没有实现内部序列化操作

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String putSeckill(Seckill seckill){
        return null;
    }
}

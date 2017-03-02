package org.seckill.service.impl;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillExcetpion;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by X-man on 2017/3/2.
 */
@Service
public class SeckillServiceimpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String slat = "KPdasKLJ87czxcSD_c**&??";

    @Resource
    private SeckillDao seckillDao;

    @Resource
    private SuccessKilledDao successKilledDao;

    @Override
    public List<Seckill> getSeckillList(){
        return seckillDao.queryAll(0,4);
    }

    @Override
    public Seckill getById(Long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(Long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if( null == seckill){
            return new Exposer(false,seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if(nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()){ //说明不在秒杀时间范围内
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转化特定字符串的过程，不可逆
        String md5 = getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill( Long seckillId, Long userPhone,String md5) throws SeckillExcetpion, RepeatKillException, SeckillCloseException {
        if(null == md5 || !md5.equals(getMD5(seckillId))){ //如果改了md5 或者改了id 说明是异常
            throw new SeckillExcetpion("秒杀的数据被重写");
        }
        //执行秒杀逻辑 ：减库存，记录购买行为
        Date nowTime = new Date();
       try {
           int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
           if(updateCount <= 0){ //没有更新到记录
               throw new SeckillCloseException("秒杀已经结束");
           } else { //记录购买行为
               int insertCount = successKilledDao.insertSuccessKilled(seckillId,userPhone);
               if(insertCount <= 0){ //重复秒杀
                   throw new RepeatKillException("重复秒杀异常");
               } else {
                   SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                   return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS,successKilled);
               }
           }
       }catch (SeckillCloseException e1){
            throw e1;
       }catch (RepeatKillException e2){
            throw e2;
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           throw new SeckillExcetpion("seckill inner error:" + e.getMessage());
       }
    }


    private String getMD5(Long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}

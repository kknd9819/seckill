DROP DATABASE IF EXISTS seckill ;
CREATE DATABASE seckill;

use seckill;


CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` VARCHAR(120) NOT NULL COMMENT '商品名称',
 `number` int NOT NULL COMMENT '库存数量',
 `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
 `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
 `create_time` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
 PRIMARY  KEY (seckill_id),
 key idx_start_time(start_time),
 key idx_end_time(end_time),
 key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT '秒杀库存表';

insert into
  seckill (name,number,start_time,end_time)
values
  ('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2017-03-01 23:59:59'),
  ('500元秒杀ipad2',200,'2015-11-01 00:00:00','2017-03-01 23:59:59'),
  ('300元秒杀小米4',300,'2015-11-01 00:00:00','2017-03-01 23:59:59'),
  ('200元秒杀红米note',400,'2015-11-01 00:00:00','2017-03-01 23:59:59');


CREATE TABLE success_killed(
  `seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
  `user_phone` bigint NOT NULL COMMENT '用户手机号',
  `state` tinyint NOT NULL DEFAULT -1 COMMENT '状态表示 -1无效，0成功，1已付款，2已发货，3已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),
  key idx_create_time(create_time)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT '秒杀成功明细表';



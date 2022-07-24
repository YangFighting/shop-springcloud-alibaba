# shop-springcloud-alibaba



# 遇到的问题

项目共享依赖时。无法引用工具模块的类

删掉父项目中的src文件

https://blog.csdn.net/qq_23095607/article/details/115266818



idea 创建mybatis的xml文件

https://blog.csdn.net/q15102780705/article/details/114687269





新建数据库 表

```mysql
CREATE DATABASE shop;

CREATE TABLE IF NOT EXISTS `t_user`(
   `id` BIGINT UNSIGNED,
   `t_username` VARCHAR(50),
   `t_password` VARCHAR(64),
   `t_phone` VARCHAR(20),
   `t_address` VARCHAR(255),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_product`(
   `id` BIGINT UNSIGNED,
   `t_pro_name` VARCHAR(50),
   `t_pro_price` DECIMAL(10,2),
   `t_pro_stock` INT(11),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `t_order`(
   `id` BIGINT UNSIGNED,
   `t_user_id` BIGINT(20),
   `t_user_name` VARCHAR(50),
   `t_phone` VARCHAR(20),
   `t_address` VARCHAR(255),
   `t_total_price` DECIMAL(10,2),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_order_item`(
   `id` BIGINT UNSIGNED,
   `t_order_id` BIGINT(20),
   `t_pro_id` BIGINT(20),
   `t_pro_name` VARCHAR(50),
   `t_pro_price` DECIMAL(10,2),
   `t_number` INT(11),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


```


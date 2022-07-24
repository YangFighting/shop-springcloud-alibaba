# shop-springcloud-alibaba

## 数据库初始化

### 新建数据库表

```sql
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

### 添加测试数据

```sql
INSERT INTO `shop`.`t_user`(`id`, `t_username`, `t_password`, `t_phone`, `t_address`) VALUES (1001, 'binghe', 'c26be8aaf53b15054896983b43eb6a65', '13212345678', '北京');


INSERT INTO `shop`.`t_product`(`id`, `t_pro_name`, `t_pro_price`, `t_pro_stock`) VALUES (1001, '华为', 2399.00, 100);
INSERT INTO `shop`.`t_product`(`id`, `t_pro_name`, `t_pro_price`, `t_pro_stock`) VALUES (1002, '小米', 1999.00, 100);
INSERT INTO `shop`.`t_product`(`id`, `t_pro_name`, `t_pro_price`, `t_pro_stock`) VALUES (1003, 'iphone', 4999.00, 100);
```



订单微服务测试

http://localhost:8080/order/submit_order?productId=1001&userId=1001&count=1001

## 服务治理

### 下载安装

[nacos-server-1.4.3软件安装包](https://heyangyi.com/archives/nacos-server-143-xia-zai-fen-xiang)

1. 解压压缩包

   unzip -o nacos-server-1.4.3.zip

2. 建立软连接

   ln -s  /root/nacos /usr/local/nacos

3. 启动 

    bash startup.sh -m standalone

4. 访问 http://47.97.124.83:8848/nacos/index.html#/login

5. 输入默认账号密码：nacos/nacos 



https://blog.csdn.net/muriyue6/article/details/119845925



## 遇到的问题

### 项目共享依赖时，无法引用工具模块的类

删掉父项目中的src文件

https://blog.csdn.net/qq_23095607/article/details/115266818



### idea 创建mybatis的xml文件

https://blog.csdn.net/q15102780705/article/details/114687269



### java:无效的目标发行版: 11

https://zhuanlan.zhihu.com/p/348660719








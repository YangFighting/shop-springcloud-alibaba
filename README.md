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
   `id` INT UNSIGNED,
   `t_username` VARCHAR(50),
   `t_password` VARCHAR(64),
   `t_phone` VARCHAR(20),
   `t_address` VARCHAR(255),
   `submission_date` DATE,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

```

